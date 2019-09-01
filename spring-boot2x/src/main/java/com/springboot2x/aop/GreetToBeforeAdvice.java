package com.springboot2x.aop;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author guofeng
 * @version 1.0
 * @desc
 * @createtime 2019/2/16 3:31 PM
 * @see jdk 1.7
 **/
public class GreetToBeforeAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object o) throws Throwable {
        final Object clientName = args[0];
        System.out.println("How are you! Mr." + clientName);
    }
}
