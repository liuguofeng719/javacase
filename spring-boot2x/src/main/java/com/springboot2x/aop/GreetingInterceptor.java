package com.springboot2x.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.util.Arrays;

/**
 * @author guofeng
 * @version 1.0
 * @desc 方法前后环绕增强
 * @createtime 2019/2/16 4:34 PM
 * @see jdk 1.7
 **/
public class GreetingInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        final Object[] arguments = invocation.getArguments();
        System.out.println("How are you ! Mr. " + Arrays.toString(arguments));//方法前执行
        final Object proceed = invocation.proceed();//通过反射调用目标方法
        System.out.println("Please enjoy yourself!");//方法后执行
        return proceed;
    }
}
