package com.springboot2x.di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author guofeng
 * @version 1.0
 * @desc
 * @createtime 2019/2/8 5:05 PM
 * @see jdk 1.7
 **/
public class AppMain {
    public static void main(String[] args) {
        ApplicationContext cxt = new AnnotationConfigApplicationContext(AppConfig.class);
        final Person bean = cxt.getBean(PersonImpl.class);
        bean.service();
    }
}
