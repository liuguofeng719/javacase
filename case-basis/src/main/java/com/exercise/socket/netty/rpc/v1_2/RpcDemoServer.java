package com.exercise.socket.netty.rpc.v1_2;


import com.exercise.socket.netty.rpc.v1_1.NettyServer;
import com.exercise.socket.netty.rpc.v1_1.RpcBuilder;

public class RpcDemoServer {

    public static void main(String[] args) {

        RpcBuilder builder = new RpcBuilder();
        builder.publishService(CalculatorService.class, new CalculatorServiceImpl());

        NettyServer server = new NettyServer(8080);
        try {
            server.start(builder);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
