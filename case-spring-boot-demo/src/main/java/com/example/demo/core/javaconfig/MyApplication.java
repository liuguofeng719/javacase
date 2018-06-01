package com.example.demo.core.javaconfig;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/5/31 下午4:22
 * @see jdk 1.7
 **/
public class MyApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
        final UserFunctionService bean = context.getBean(UserFunctionService.class);
        bean.sayHello();
        context.close();
    }
}
