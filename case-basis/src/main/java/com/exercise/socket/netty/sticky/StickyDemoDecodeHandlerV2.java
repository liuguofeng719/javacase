package com.exercise.socket.netty.sticky;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.ArrayList;
import java.util.List;

public class StickyDemoDecodeHandlerV2 extends ChannelInboundHandlerAdapter {
	private ByteBuf cache;
	private byte delimiter; //包分隔符

	public StickyDemoDecodeHandlerV2(ByteBuf delimiter) {
		if (delimiter == null) {
			throw new NullPointerException("delimiter");
		}
		if (!delimiter.isReadable()) {
			throw new IllegalArgumentException("empty delimiter");
		}

		this.delimiter = delimiter.readByte();
		;
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
			if (cache == null) {
				cache = ctx.alloc().buffer(1024);
			} else {
				if (cache.writerIndex() > cache.maxCapacity() - data.readableBytes()) {
					cache = expandCache(ctx.alloc(), cache, data.readableBytes());
				}
			}
			cache.writeBytes(data);

			List<ByteBuf> output = new ArrayList<>();

			int frameIndex = 0;
			int frameEndIndex = 0;
			int length = cache.readableBytes();
			while (frameIndex <= length) {
				frameEndIndex = cache.indexOf(frameIndex + 1, length, delimiter);

				if (frameEndIndex == -1) {
					cache.discardReadBytes();
					break;
				}

				output.add(cache.readBytes(frameEndIndex - frameIndex));
				cache.skipBytes(1);
				frameIndex = frameEndIndex + 1;

			}

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
