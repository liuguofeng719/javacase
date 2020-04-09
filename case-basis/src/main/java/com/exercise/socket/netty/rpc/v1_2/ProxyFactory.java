package com.exercise.socket.netty.rpc.v1_2;

import com.exercise.socket.netty.rpc.v1_1.NettyClient;

import java.lang.reflect.InvocationHandler;


public final class ProxyFactory {


    public static <T> T getProxy(Class<T> interfaceClass, NettyClient proxyInvoker) {
        InvocationHandler handler = new JDKInvocationHandler(interfaceClass, proxyInvoker);
        ClassLoader classLoader = ProxyFactory.class.getClassLoader();
        T result = (T) java.lang.reflect.Proxy.newProxyInstance(classLoader,
                new Class[]{interfaceClass}, handler);
        return result;
    }

}