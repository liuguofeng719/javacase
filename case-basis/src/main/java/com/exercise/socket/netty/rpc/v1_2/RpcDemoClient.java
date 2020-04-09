package com.exercise.socket.netty.rpc.v1_2;

import com.exercise.socket.netty.rpc.v1.SerializerFactory;
import com.exercise.socket.netty.rpc.v1_1.NettyClient;

public class RpcDemoClient {

    public static void main(String[] args) throws Exception {
        NettyClient client = new NettyClient();
        client.init("127.0.0.1", 8080, SerializerFactory.getSerializer());

        CalculatorService proxy = ProxyFactory.getProxy(CalculatorService.class, client);

        System.out.println(proxy.add(1.0, 2.0));

        client.close();

    }

}
