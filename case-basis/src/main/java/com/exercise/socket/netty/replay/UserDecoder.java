package com.exercise.socket.netty.replay;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

public class UserDecoder extends ReplayingDecoder<Void> {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        int length = in.readInt();

        byte[] content = new byte[length];
        in.readBytes(content);

        UserProtocol personProtocol = new UserProtocol();
        personProtocol.setLength(length);
        personProtocol.setContent(content);

        out.add(personProtocol);
    }
}
 