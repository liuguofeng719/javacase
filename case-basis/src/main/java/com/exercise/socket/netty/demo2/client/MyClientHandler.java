package com.exercise.socket.netty.demo2.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @author guofeng
 * @version 1.0
 * @see jdk 1.8
 **/
public class MyClientHandler extends SimpleChannelInboundHandler<Long> {
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            final IdleStateEvent stateEvent = (IdleStateEvent) evt;
            String messasge = "";
            switch (stateEvent.state()) {
                case ALL_IDLE:
                    messasge = "ALL_IDLE";
                    break;
                case READER_IDLE:
                    messasge = "READER_IDLE";
                    break;
                case WRITER_IDLE:
                    messasge = "WRITER_IDLE";
                    break;
            }

            System.out.println("message=" + messasge);

        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Long msg) throws Exception {
        System.out.println(Thread.currentThread().getName() + " MyClientHandler -> channelRead0 " + msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(Thread.currentThread().getName() + " MyClientHandler -> channelActive ");
        ctx.writeAndFlush(123456L);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        cause.printStackTrace();
    }
}
