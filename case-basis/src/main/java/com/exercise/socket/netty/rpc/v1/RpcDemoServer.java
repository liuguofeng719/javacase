package com.exercise.socket.netty.rpc.v1;

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
