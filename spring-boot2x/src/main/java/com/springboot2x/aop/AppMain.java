package com.springboot2x.aop;

import com.springboot2x.config.AopConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author guofeng
 * @version 1.0
 * @desc
 * @createtime 2019/2/16 4:23 PM
 * @see jdk 1.7
 **/
public class AppMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext asc = new AnnotationConfigApplicationContext(AopConfig.class);
        final Waiter bean = (Waiter) asc.getBean("waiter");
        bean.greetTo("greetTo");
        bean.serveTo("serveTo");

        final Waiter ascBean = (Waiter) asc.getBean("waiter1");
        ascBean.greetTo("test");
        ascBean.serveTo("hello");
    }
}
