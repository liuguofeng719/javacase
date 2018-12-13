package com.netty.example.nio;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/10/12 上午11:41
 * @see jdk 1.7
 **/
public class NettyServerHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext handlerContext, String s) throws Exception {
        System.out.println("netty server channelRead0 ");
        System.out.println(handlerContext.channel().remoteAddress() + " => msg = " + s);
        handlerContext.writeAndFlush("hello netty client");
    }
}
