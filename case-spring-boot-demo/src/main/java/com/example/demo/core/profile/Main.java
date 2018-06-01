package com.example.demo.core.profile;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/6/1 下午3:07
 * @see jdk 1.7
 **/
public class Main {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        //先设置，后注册Bean 不然会报错，报bean找不到
//        context.getEnvironment().setActiveProfiles("pro");
        context.getEnvironment().setActiveProfiles("dev");
        context.register(DemoBeanConfig.class);
        context.refresh();

        final DemoBean bean = context.getBean(DemoBean.class);
        context.close();

    }
}
