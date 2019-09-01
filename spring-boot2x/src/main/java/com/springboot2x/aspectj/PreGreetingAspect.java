package com.springboot2x.aspectj;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * @author guofeng
 * @version 1.0
 * @desc
 * @createtime 2019/2/16 8:40 PM
 * @see jdk 1.7
 **/
@Aspect
public class PreGreetingAspect {

    @Before("execution(* greetTo(..))")
    public void beforeGreeting() {
        System.out.println("How are you");
    }
}
