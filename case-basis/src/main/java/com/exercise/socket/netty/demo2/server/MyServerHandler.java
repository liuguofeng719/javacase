package com.exercise.socket.netty.demo2.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.concurrent.TimeUnit;

/**
 * @author guofeng
 * @version 1.0
 * @see jdk 1.8
 **/
public class MyServerHandler extends SimpleChannelInboundHandler<Long> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Long msg) throws Exception {
        TimeUnit.SECONDS.sleep(3);
        System.out.println(Thread.currentThread().getName() + " MyServerHandler -> channelRead0 = " + msg);
        ctx.channel().writeAndFlush(3455L);
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        cause.printStackTrace();
    }
}
