package com.exercise.socket.netty.demo2.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.concurrent.DefaultThreadFactory;
import io.netty.util.concurrent.UnorderedThreadPoolEventExecutor;

import java.util.concurrent.TimeUnit;

/**
 * @author guofeng
 * @version 1.0
 * @see jdk 1.8
 * <p>
 * ChannelPipeline的入站和出站
 * 事件顺序：客服端到服务端出站，服务端到客户端入站
 * <p>
 * 客户端向服务端（SocketChannel）发送消息出站
 * 服务端（SocketChannel）向客户端发送消息为入站
 * <p>
 * 客户端 和 服务端的操作都是相互反向的
 **/
public class MyClient {

    public static void main(String[] args) {

        EventLoopGroup eventLoopGroup = new NioEventLoopGroup(0, new DefaultThreadFactory("work-thread"));
        UnorderedThreadPoolEventExecutor eventExecutors = new UnorderedThreadPoolEventExecutor(10, new DefaultThreadFactory("buss"));
        try {
            Bootstrap b = new Bootstrap();
            b.group(eventLoopGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            final ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast("decode", new MyClientDecoder());
                            pipeline.addLast("encode", new MyClientEncoder());
                            pipeline.addLast(new LoggingHandler());
                            pipeline.addLast(new IdleStateHandler(3, 5, 7, TimeUnit.SECONDS));
                            pipeline.addLast(eventExecutors, "myClientHandler", new MyClientHandler());
                        }
                    });
            final ChannelFuture channelFuture = b.connect("127.0.0.1", 7000).sync();
            for (long i = 0; i < 20; i++) {
                channelFuture.channel().writeAndFlush(i);
            }
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}
