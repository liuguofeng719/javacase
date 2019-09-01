package com.springboot2x.aop;

import org.springframework.aop.BeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

/**
 * @author guofeng
 * @version 1.0
 * @desc
 * @createtime 2019/2/16 3:33 PM
 * @see jdk 1.7
 **/
public class BeforeAviceTest {
    public static void main(String[] args) {

        Waiter w = new WaiterImpl();
        BeforeAdvice advice = new GreetToBeforeAdvice();

        ProxyFactory proxyFactory = new ProxyFactory();
        final Class<?>[] interfaces = w.getClass().getInterfaces();
        System.out.println("=====" + interfaces[0].getCanonicalName());
        proxyFactory.setInterfaces(interfaces);//使用jdk动态代理实现
//        proxyFactory.setOptimize(true);//设置了接口也会使用CGLIB做代理
        proxyFactory.setTarget(w);
        proxyFactory.addAdvice(advice);
        proxyFactory.addAdvice(new GreetToAfterAdvice());

        final Waiter proxy = (Waiter) proxyFactory.getProxy();
        proxy.greetTo("Cat");
        proxy.serveTo("Dog");
    }
}
