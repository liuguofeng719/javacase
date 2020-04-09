package com.exercise.socket.netty.demo2.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author guofeng
 * @version 1.0
 * @see jdk 1.8
 **/
public class MyClientEncoder extends MessageToByteEncoder<Long> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Long msg, ByteBuf out) throws Exception {
        System.out.println("MyClientEncoder -> encode = " + msg);
        out.writeLong(msg);
    }
}
