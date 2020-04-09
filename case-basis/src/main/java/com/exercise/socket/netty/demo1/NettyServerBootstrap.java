package com.exercise.socket.netty.demo1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.DefaultThreadFactory;

/**
 * @author guofeng
 * @version 1.0
 * @see jdk 1.8
 **/
public class NettyServerBootstrap {

    public static void main(String[] args) {
        // 接受Accept事件的线程池，对应nio中的while循环中的select
        // NioEventLoopGroup包含多个NioEventLoop，NioEventLoop中包括一个 Selector
        EventLoopGroup bossLoopGroup = new NioEventLoopGroup(1, new DefaultThreadFactory("bossLoopGroup"));
        // 线程池默认是(Runtime.getRuntime().availableProcessors() * 2),对应nio的while循环中通道处理读写事件的线程
        EventLoopGroup workLoopGroup = new NioEventLoopGroup(8, new DefaultThreadFactory("workLoopGroup"));
        try {
            // 引导类
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossLoopGroup, workLoopGroup)
                    // 反射 + 工厂,指定请求通道绑定Accept事件
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    //                .channel() // 给bossLoopGroup 设置处理handler
                    // 每个客户端都对应一个SocketChannel通道
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast("loggingHandler", new LoggingHandler(LogLevel.DEBUG));
                            // 管道中添加一个处理handler
                            ch.pipeline().addLast("testServerHandler", new TestServerHandler());
                            ch.pipeline().addLast("loggingHandler", new LoggingHandler(LogLevel.INFO));
                        }
                    });

            // 异步绑定端口号
            final ChannelFuture channelFuture = serverBootstrap.bind(8888).sync();
            // 绑定端口处理的事件
            channelFuture.addListener((future) -> {
                if (future.isSuccess()) {
                    System.out.println("绑定端口成功 = " + channelFuture.channel().localAddress().toString());
                } else {
                    System.out.println("绑定端口失败");
                }
            });
            // 异步关闭通道
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 优雅关闭，是否线程池资源
            bossLoopGroup.shutdownGracefully();
            workLoopGroup.shutdownGracefully();
        }
    }
}
