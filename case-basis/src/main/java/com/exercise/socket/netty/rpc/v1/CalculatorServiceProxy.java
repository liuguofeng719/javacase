package com.exercise.socket.netty.rpc.v1;

import java.util.UUID;
import java.util.concurrent.SynchronousQueue;

public class CalculatorServiceProxy implements CalculatorService {

    private NettyClient client;

    public CalculatorServiceProxy(String host, int port) {
        client = new NettyClient();
        try {
            client.init(host, port, SerializerFactory.getSerializer());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public double add(double op1, double op2) {
        String id = UUID.randomUUID().toString();

        RpcRequest request = new RpcRequest();
        request.setId(id);
        request.setServiceName(CalculatorService.class.getName());
        request.setMethodName("add");
        request.setParam1(op1);
        request.setParam2(op2);

        RpcResponse response = this.sendRPCRequest(request);

        return response.getResult();
    }

    @Override
    public double substract(double op1, double op2) {
        String id = UUID.randomUUID().toString();

        RpcRequest request = new RpcRequest();
        request.setServiceName(CalculatorService.class.getName());

        request.setId(id);
        request.setMethodName("sub");
        request.setParam1(op1);
        request.setParam2(op2);

        RpcResponse response = this.sendRPCRequest(request);

        return response.getResult();
    }

    @Override
    public double multiply(double op1, double op2) {
        String id = UUID.randomUUID().toString();

        RpcRequest request = new RpcRequest();
        request.setServiceName(CalculatorService.class.getName());

        request.setId(id);
        request.setMethodName("mul");
        request.setParam1(op1);
        request.setParam2(op2);

        RpcResponse response = this.sendRPCRequest(request);

        return response.getResult();
    }

    private RpcResponse sendRPCRequest(RpcRequest request) {
        SynchronousQueue<RpcResponse> queue = new SynchronousQueue();
        NettyClient.putSunchronousQuee(request.getId(), queue);
        RpcResponse response = null;
        try {
            client.sendRpcRequest(request);
            response = queue.take();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return response;
    }

    public void stop() {
        client.close();
    }

}
