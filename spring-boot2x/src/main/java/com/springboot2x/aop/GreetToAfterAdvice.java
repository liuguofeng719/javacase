package com.springboot2x.aop;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author guofeng
 * @version 1.0
 * @desc
 * @createtime 2019/2/16 4:04 PM
 * @see jdk 1.7
 **/
public class GreetToAfterAdvice implements AfterReturningAdvice {
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("returnValue=" + returnValue + " args=" + Arrays.toString(args) + " target= " + target);
    }
}
