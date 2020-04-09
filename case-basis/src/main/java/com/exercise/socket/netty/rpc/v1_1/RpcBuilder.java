package com.exercise.socket.netty.rpc.v1_1;

import com.exercise.socket.netty.rpc.v1.RpcException;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RpcBuilder {
    Map<String, Object> services = new ConcurrentHashMap<>();
    Map<String, Class<?>> interfaces = new ConcurrentHashMap<>();

    public void publishService(Class<?> serviceInterface, Object service) {
        if (services.containsKey(serviceInterface.getName()) || interfaces.containsKey(serviceInterface.getName())) {
            throw new RpcException("serviceInterface has been already registered......");
        }

        if (!(serviceInterface.isInstance(service))) {
            throw new RpcException("service object must implement the service Interface .......");
        }

        services.put(serviceInterface.getName(), service);
        interfaces.put(serviceInterface.getName(), serviceInterface);
    }

    public RpcResponse invokeService(RpcRequest rpcRequest) {
        String serviceName = rpcRequest.getServiceName();
        String methodName = rpcRequest.getMethodName();
        Class<?> serviceInterface = interfaces.get(serviceName);
        String[] parameterTypeNames = rpcRequest.getParameterTypeNames();

        Method m;
        Object result = null;

        Class<?>[] parameterClasses = new Class<?>[parameterTypeNames.length];
        try {
            for (int i = 0; i < parameterClasses.length; i++) {

                parameterClasses[i] = ClassUtils.getClass(parameterTypeNames[i]);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            m = serviceInterface.getMethod(methodName, parameterClasses);
            result = m.invoke(services.get(serviceName), rpcRequest.getArgs());

        } catch (Exception e) {
            e.printStackTrace();
        }

        RpcResponse response = new RpcResponse();
        response.setId(rpcRequest.getId());
        response.setMethodName(methodName);
        response.setResult((double) result);

        return response;
    }

}
