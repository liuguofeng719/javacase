package com.exercise.socket.netty.proto;

import com.turingschool.demo.netty.rpc.RPCRequest;
import com.turingschool.demo.netty.rpc.RPCResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ProtobufDemoServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        RPCRequest request = (RPCRequest) msg;
        System.out.println("收到客户端请求：[" + request.toString() + "]");
        ctx.writeAndFlush(createResponse(request.getId()));
    }

    private RPCResponse createResponse(long id) {
        RPCResponse.Builder builder = RPCResponse.newBuilder();
        builder.setId(id);
        builder.setMethodName("sayHello" + id);
        builder.setServiceName("HelloService" + id);
        builder.setVersion(1);
        builder.setResult("Hello, world![" + id + "]");

        return builder.build();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
