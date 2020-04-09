package com.exercise.socket.netty.rpc.v1_1;

import com.exercise.socket.netty.rpc.v1.SerializerFactory;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
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
    public double add(double op1, double op2, double op3) {


        Method method = null;
        try {
            method = CalculatorService.class.getMethod("add", new Class[]{double.class, double.class, double.class});
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        RpcRequest request = buildRequest(CalculatorService.class.getName(), method, new Object[]{op1, op2, op3});
        RpcResponse response = this.sendRPCRequest(request);

        return response.getResult();
    }

    @Override
    public double substract(double op1, double op2) {
        Method method = null;
        try {
            method = CalculatorService.class.getMethod("substract", new Class[]{double.class, double.class});
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        RpcRequest request = buildRequest(CalculatorService.class.getName(), method, new Object[]{op1, op2});
        RpcResponse response = this.sendRPCRequest(request);


        return response.getResult();
    }

    @Override
    public double multiply(double op1, double op2) {
        Method method = null;
        try {
            method = CalculatorService.class.getMethod("multiply", new Class[]{double.class, double.class});
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        RpcRequest request = buildRequest(CalculatorService.class.getName(), method, new Object[]{op1, op2});
        RpcResponse response = this.sendRPCRequest(request);

        return response.getResult();
    }


    private RpcRequest buildRequest(String serviceName, Method method, Object[] args) {

        String id = UUID.randomUUID().toString();

        RpcRequest request = new RpcRequest();
        request.setServiceName(serviceName);

        request.setId(id);
        request.setMethodName(method.getName());
        request.setArgs(args);

        List<String> parameterTypes = new ArrayList<String>();
        for (Class<?> parameterType : method.getParameterTypes()) {
            parameterTypes.add(parameterType.getName());
        }

        request.setParameterTypeNames(parameterTypes.toArray(new String[0]));

        return request;
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
