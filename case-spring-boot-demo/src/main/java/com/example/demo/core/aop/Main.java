package com.example.demo.core.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/5/31 下午4:49
 * @see jdk 1.7
 **/
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AopConfig.class);
        final UserService userService = context.getBean(UserService.class);
        userService.add();
        context.close();
    }
}
