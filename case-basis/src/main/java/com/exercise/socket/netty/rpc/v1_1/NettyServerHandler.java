package com.exercise.socket.netty.rpc.v1_1;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NettyServerHandler extends SimpleChannelInboundHandler<RpcRequest> {

    private static final Logger logger = LoggerFactory.getLogger(NettyServerHandler.class);


    private RpcBuilder rpcBuilder;

    public NettyServerHandler(RpcBuilder rpcBuilder) {
        this.rpcBuilder = rpcBuilder;
    }


    @Override
    public void channelRead0(final ChannelHandlerContext ctx, RpcRequest rpcRequest) throws Exception {

        RpcResponse rpcResponse = rpcBuilder.invokeService(rpcRequest);

        ctx.writeAndFlush(rpcResponse);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
    }
}
