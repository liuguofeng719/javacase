package com.exercise.socket.netty.rpc.v1;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NettyServer {
	private static final Logger logger = LoggerFactory.getLogger(NettyServer.class);

	private Thread thread;

	private final int port;

	public NettyServer(int port) {
		this.port = port;
	}

	public void start(final RpcBuilder rpcBuilder) throws Exception {

		thread = new Thread(new Runnable() {
			@Override
			public void run() {

				EventLoopGroup bossGroup = new NioEventLoopGroup();
				EventLoopGroup workerGroup = new NioEventLoopGroup();
				try {
					ServerBootstrap bootstrap = new ServerBootstrap();
					bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
							.childHandler(new ChannelInitializer<SocketChannel>() {
								@Override
								public void initChannel(SocketChannel channel) throws Exception {
									channel.pipeline()
											.addLast(new RpcDecode(RpcRequest.class, SerializerFactory.getSerializer()))
											.addLast(new RpcEncode(RpcResponse.class, SerializerFactory.getSerializer()))
											.addLast(new NettyServerHandler(rpcBuilder));
								}
							})
							.option(ChannelOption.SO_TIMEOUT, 100)
							.option(ChannelOption.SO_BACKLOG, 128)
							.option(ChannelOption.TCP_NODELAY, true)
							.option(ChannelOption.SO_REUSEADDR, true)
							.childOption(ChannelOption.SO_KEEPALIVE, true);
					ChannelFuture future = bootstrap.bind(port).sync();

					logger.info("接口服务器端启动成功......");

					Channel serviceChannel = future.channel().closeFuture().sync().channel();
				} catch (InterruptedException e) {
					logger.error(e.getMessage(), e);
				} finally {
					workerGroup.shutdownGracefully();
					bossGroup.shutdownGracefully();
				}
			}
		});
		thread.start();

	}

	public Thread getThread() {
		return this.thread;
	}

	public void stop() throws Exception {
		if (thread != null && thread.isAlive()) {
			thread.interrupt();
		}

		logger.info("接口服务器端停止......");
	}

}
