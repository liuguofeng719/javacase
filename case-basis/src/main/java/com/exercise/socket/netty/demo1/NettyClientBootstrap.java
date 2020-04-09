package com.exercise.socket.netty.demo1;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author guofeng
 * @version 1.0
 * @see jdk 1.8
 **/
public class NettyClientBootstrap {

    public static void main(String[] args) {
        // 客户端监听selector.select 是否有读写事件
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast("testClientHandler", new TestClientHandler());
                        }
                    });
            // 异步连接服务端
            final ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 8888).sync();
            channelFuture.addListener(future -> {
                if (future.isSuccess()) {
                    System.out.println("连接服务器成功");
                } else {
                    System.out.println("连接服务器失败");
                }
            });
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 优雅关闭，释放连接池资源
            eventLoopGroup.shutdownGracefully();
        }
    }
}
