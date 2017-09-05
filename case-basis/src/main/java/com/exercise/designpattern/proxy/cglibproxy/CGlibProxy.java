package com.exercise.designpattern.proxy.cglibproxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2017/6/7 上午10:42
 * @see JDK 1.7
 **/
public class CGlibProxy implements MethodInterceptor {

    public <T> T getProxy(Class<T> cls) {
        return (T) Enhancer.create(cls, this);
    }

    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        beforeReadBook();
        final Object result = methodProxy.invokeSuper(o, objects);
        afterCoffee();
        return result;
    }

    public void beforeReadBook() {
        System.out.println("先读书");
    }

    public void afterCoffee() {
        System.out.println("喝咖啡");
    }
}
