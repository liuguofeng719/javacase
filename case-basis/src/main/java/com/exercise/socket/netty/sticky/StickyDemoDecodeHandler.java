package com.exercise.socket.netty.sticky;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.ArrayList;
import java.util.List;

public class StickyDemoDecodeHandler extends ChannelInboundHandlerAdapter {

	//存放待拆包数据的缓冲区
	private ByteBuf cache;
	private int frameLength;

	public StickyDemoDecodeHandler(int length) {
		this.frameLength = length;
	}

	static ByteBuf expandCache(ByteBufAllocator alloc, ByteBuf cache, int readable) {
		ByteBuf oldCache = cache;
		cache = alloc.buffer(oldCache.readableBytes() + readable);
		cache.writeBytes(oldCache);
		oldCache.release();
		return cache;
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {

		ByteBuf data = (ByteBuf) msg;
		try {
			//读取每一个消息，创建缓冲区
			if (cache == null) {
				cache = ctx.alloc().buffer(1024);
			} else {
				//如果现有的缓冲区容量太小，无法容纳原有数据+新读入的数据，就扩容（重新创建一个大的，并把数据拷贝过去）
				if (cache.writerIndex() > cache.maxCapacity() - data.readableBytes()) {
					cache = expandCache(ctx.alloc(), cache, data.readableBytes());
				}
			}
			//把新的数据读入缓冲区
			cache.writeBytes(data);

			//每次读取frameLength（定长）的数据，做为一个包，存储起来 
			List<ByteBuf> output = new ArrayList<>();
			while (cache.readableBytes() >= frameLength) {
				output.add(cache.readBytes(frameLength));
			}

			//还有部分数据不够一个包，10， 15， 一个10个，还剩5个
			if (cache.isReadable()) {
				cache.discardReadBytes();
			}

			for (int i = 0; i < output.size(); i++) {
				ctx.fireChannelRead(output.get(i));
			}
		} finally {
			data.release();
		}

	}


	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}
}
