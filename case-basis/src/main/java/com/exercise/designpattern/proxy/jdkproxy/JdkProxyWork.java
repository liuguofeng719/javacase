package com.exercise.designpattern.proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc jdk 动态代理
 * @createtime 2017/6/7 上午10:27
 * @see JDK 1.7
 **/
public class JdkProxyWork implements InvocationHandler {

    private Object target;

    public JdkProxyWork(Object target) {
        this.target = target;
    }

    public <T> T getInstance() {
        return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(),
                target.getClass().getInterfaces(), this);
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        beforeReadBook();

        final Object invoke = method.invoke(target, args);

        afterCoffee();

        return invoke;
    }


    public void beforeReadBook() {
        System.out.println("先读书");
    }

    public void afterCoffee() {
        System.out.println("喝咖啡");
    }


}
