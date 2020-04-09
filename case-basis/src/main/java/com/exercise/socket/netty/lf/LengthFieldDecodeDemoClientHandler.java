package com.exercise.socket.netty.lf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.util.Random;

public class LengthFieldDecodeDemoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private Random rand = new Random();
    private static String[] alphabets = {"A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P"};

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        for (int i = 0; i < 10; i++) {
            StringBuilder builder = new StringBuilder();
            builder.append("这是第");
            builder.append(i);
            builder.append("条消息, 内容是：");
            for (int j = 0; j < rand.nextInt(20); j++) {
                builder.append(alphabets[i]);
            }
            builder.append("......");


            System.out.println(builder.toString().getBytes().length);

            ctx.writeAndFlush(Unpooled.copiedBuffer(builder.toString(),
                    CharsetUtil.UTF_8));
        }
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, ByteBuf in) {
        System.out.println("客户端接收到消息： " + in.toString(CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
