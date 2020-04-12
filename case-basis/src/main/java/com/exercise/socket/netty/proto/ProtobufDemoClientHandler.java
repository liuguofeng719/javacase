package com.exercise.socket.netty.proto;

import com.exercise.socket.netty.rpc.RPCRequest;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ProtobufDemoClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        for (int i = 0; i < 2; i++) {
            ctx.write(createRpcRequest(i));
        }
        ctx.flush();
    }

    private RPCRequest createRpcRequest(int id) {
        RPCRequest.Builder builder = RPCRequest.newBuilder();
        builder.setId(id);
        builder.setMethodName("sayHello" + id);
        builder.setServiceName("HelloService" + id);
        builder.setVersion(1);
        builder.setParameters("method parameters:" + id);

        return builder.build();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("收到服务器端消息:[" + msg + "]");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
