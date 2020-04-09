package com.exercise.socket.netty.rpc.v1;

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

        //调用具体的业务方法，调用 CalculatorService.add方法
        RpcResponse rpcResponse = rpcBuilder.invokeService(rpcRequest);
        // 把响应返回给客户端
        ctx.writeAndFlush(rpcResponse);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
    }
}
