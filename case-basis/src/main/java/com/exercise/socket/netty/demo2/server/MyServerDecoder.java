package com.exercise.socket.netty.demo2.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author guofeng
 * @version 1.0
 * @see jdk 1.8
 **/
public class MyServerDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("MyServerDecoder - > decode ");
        if (in.readableBytes() >= 8) {
            out.add(in.readLong());
        }
    }
}
