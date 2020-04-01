package com.exercise.socket.netty.echo;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.TimeUnit;

/**
 * 处理服务端 channel.
 */
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
    	System.out.println("客户端[" + ctx.channel().remoteAddress()+"]->服务器端:"+ msg.toString());
        final ChannelFuture future = ctx.write(msg + "\n");
     }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("服务器端读取完成...");
        ctx.flush();
        TimeUnit.MILLISECONDS.sleep(200);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { 
        cause.printStackTrace();
        ctx.close();
    }
}