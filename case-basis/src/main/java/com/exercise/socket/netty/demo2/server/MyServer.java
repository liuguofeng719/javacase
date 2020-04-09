package com.exercise.socket.netty.demo2.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.DefaultThreadFactory;
import io.netty.util.concurrent.UnorderedThreadPoolEventExecutor;

/**
 * @author guofeng
 * @version 1.0
 * @see jdk 1.8
 **/
public class MyServer {

    public static void main(String[] args) {

        EventLoopGroup bossGroup = new NioEventLoopGroup(1, new DefaultThreadFactory("bossGroup"));

        EventLoopGroup workGroup = new NioEventLoopGroup(0, new DefaultThreadFactory("workGroup"));

        // 业务线程池
        UnorderedThreadPoolEventExecutor unorderedThreadPoolEventExecutor =
                new UnorderedThreadPoolEventExecutor(10, new DefaultThreadFactory("work-bus"));

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            final ChannelPipeline p = ch.pipeline();
                            p.addLast("decode", new MyServerDecoder());
                            p.addLast("encode", new MyServerEncoder());
                            p.addLast(unorderedThreadPoolEventExecutor, "myServerHandler", new MyServerHandler());
                        }
                    });

            final ChannelFuture channelFuture = bootstrap.bind(7000).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
