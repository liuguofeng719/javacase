package com.netty.example.nio;


import java.net.InetSocketAddress;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/10/12 上午11:34
 * @see jdk 1.7
 **/
public class IOServer {
    public static void main(String[] args) {
        start();
    }

    private static void start() {
        try {

            NioEventLoopGroup boss = new NioEventLoopGroup(1);

            NioEventLoopGroup work = new NioEventLoopGroup();

            //引导类
            ServerBootstrap bootstrap = new ServerBootstrap();

            bootstrap
                    .group(boss, work)//设置写线程，以及读取线程
                    .channel(NioServerSocketChannel.class)//指定IO模型,NioServerSocketChannel 和socket 里面的SocketServer 相当
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .option(ChannelOption.SO_KEEPALIVE, false)
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    .localAddress(new InetSocketAddress(8080))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception {
                            final ChannelPipeline pipeline = channel.pipeline();
                            channel.pipeline().addLast("decoder", new StringDecoder());
                            channel.pipeline().addLast("encoder", new StringEncoder());
                            pipeline.addLast(new NettyServerHandler());
                        }
                    });
            final ChannelFuture future = bootstrap.bind().sync();
            final InetSocketAddress inetSocketAddress = (InetSocketAddress) future.channel().localAddress();
            final int port = inetSocketAddress.getPort();
            System.out.println(" netty server start port = " + port);
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
