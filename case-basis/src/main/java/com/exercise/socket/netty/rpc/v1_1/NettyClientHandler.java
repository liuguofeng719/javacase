package com.exercise.socket.netty.rpc.v1_1;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.SynchronousQueue;

public class NettyClientHandler extends SimpleChannelInboundHandler<RpcResponse> {
	private static final Logger logger = LoggerFactory.getLogger(NettyClientHandler.class);

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		logger.error("远程连接异常：", cause);
		ctx.close();
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, RpcResponse rpcResponse) throws Exception {
		String id = rpcResponse.getId();
		SynchronousQueue<RpcResponse> synchronousQueue = NettyClient.getSynchronousQueue(id);
		synchronousQueue.put(rpcResponse);
		NettyClient.removeById(id);

	}

}
