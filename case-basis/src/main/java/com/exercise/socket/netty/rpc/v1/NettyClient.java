package com.exercise.socket.netty.rpc.v1;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.SynchronousQueue;

public class NettyClient {
    private Channel channel;

    private static ConcurrentHashMap<String, SynchronousQueue<RpcResponse>> mapInfo = new ConcurrentHashMap<>();

    public static void putSunchronousQuee(String id, SynchronousQueue<RpcResponse> queue) {
        mapInfo.put(id, queue);
    }

    public static SynchronousQueue<RpcResponse> getSynchronousQueue(String id) {
        return mapInfo.get(id);
    }

    public static void removeById(String id) {
        mapInfo.remove(id);
    }

    public void sendRpcRequest(RpcRequest rpcRequest) throws Exception {

        try {
            this.channel.writeAndFlush(rpcRequest).sync();
        } catch (Exception e) {
            throw e;
        }
    }

    public void init(String host, int port, final Serializer serializer) throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group).channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel channel) throws Exception {
                channel.pipeline()
                        .addLast(new RpcEncode(RpcRequest.class, serializer))
                        .addLast(new RpcDecode(RpcResponse.class, serializer))
                        .addLast(new NettyClientHandler());
            }
        }).option(ChannelOption.TCP_NODELAY, true).option(ChannelOption.SO_REUSEADDR, true)
                .option(ChannelOption.SO_KEEPALIVE, true);
        this.channel = bootstrap.connect(host, port).sync().channel();
        System.out.println("接口服务端连接成功......");
    }

    public boolean isValidate() {
        if (this.channel != null) {
            return this.channel.isActive();
        }
        return false;
    }

    public void close() {
        if (this.channel != null) {
            if (this.channel.isOpen()) {
                this.channel.close();
            }
        }
    }
}
