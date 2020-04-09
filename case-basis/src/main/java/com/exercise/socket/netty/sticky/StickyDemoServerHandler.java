package com.exercise.socket.netty.sticky;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

public class StickyDemoServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf in = (ByteBuf) msg;
        System.out.println(
                "服务器接收到消息：" + in.toString(CharsetUtil.UTF_8));
        ctx.write(in);
        // ctx.write(Unpooled.copiedBuffer("#", CharsetUtil.UTF_8));
        //compositeBuffer.addComponent(in);
        // ByteBuf buf =  ctx.alloc().directBuffer();
        // buf.writeBytes("#".getBytes());
        // CompositeByteBuf compositeBuffer = ctx.alloc().compositeBuffer();
        //  compositeBuffer.addComponents(true, in, buf);


        // ctx.write(compositeBuffer);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx)
            throws Exception {
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
                .addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,
                                Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
