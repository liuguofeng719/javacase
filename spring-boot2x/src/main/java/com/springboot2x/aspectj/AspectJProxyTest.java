package com.springboot2x.aspectj;

import com.springboot2x.aop.Waiter;
import com.springboot2x.aop.WaiterImpl;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

/**
 * @author guofeng
 * @version 1.0
 * @desc
 * @createtime 2019/2/16 8:43 PM
 * @see jdk 1.7
 **/
public class AspectJProxyTest {
    public static void main(String[] args) {

        Waiter waiter = new WaiterImpl();

        AspectJProxyFactory factory = new AspectJProxyFactory();
        factory.setTarget(waiter);
        factory.addAspect(PreGreetingAspect.class);

        final Waiter proxy = factory.getProxy();

        proxy.greetTo("John");
        proxy.serveTo("John");
    }
}
