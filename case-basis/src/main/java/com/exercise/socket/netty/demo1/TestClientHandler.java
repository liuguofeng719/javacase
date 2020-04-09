package com.exercise.socket.netty.demo1;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @author guofeng
 * @version 1.0
 * @see jdk 1.8
 **/
public class TestClientHandler extends ChannelInboundHandlerAdapter {

    public static final String className = TestClientHandler.class.getName() + " = ";

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println(className + "channelRegistered");
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println(className + "channelUnregistered");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(className + "channelActive");
        ctx.writeAndFlush(Unpooled.copiedBuffer("你好啊，我是客服端", CharsetUtil.UTF_8));
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(className + "channelInactive");
    }

    // 读取客户端的请求数据
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(className + "channelRead");
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println(byteBuf.toString(CharsetUtil.UTF_8));
    }

    // 读取完成之后的处理
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println(className + "channelReadComplete");
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        System.out.println(className + "userEventTriggered");
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        System.out.println(className + "channelWritabilityChanged");
    }

    // 通道处理异常的流程
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println(className + "exceptionCaught");
        cause.printStackTrace();
    }
}
