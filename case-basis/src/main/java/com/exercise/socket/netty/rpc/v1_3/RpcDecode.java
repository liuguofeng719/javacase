package com.exercise.socket.netty.rpc.v1_3;

import com.exercise.socket.netty.rpc.v1_3.serialize.RpcSerialize;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author guofeng
 * @version 1.0
 * @see jdk 1.8
 **/
public class RpcDecode extends ByteToMessageDecoder {

    private RpcSerialize rpcSerialize;
    private RpcRequest request;

    protected RpcDecode(RpcSerialize rpcSerialize, RpcRequest request) {
        this.rpcSerialize = rpcSerialize;
        this.request = request;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        if (in.readableBytes() < 4) {
            return;
        }
        in.markReaderIndex();
    }
}
