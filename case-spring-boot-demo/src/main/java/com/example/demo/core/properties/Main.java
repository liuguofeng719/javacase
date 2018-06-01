package com.example.demo.core.properties;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/5/31 下午5:16
 * @see jdk 1.7
 **/
public class Main {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ElConfig.class);

        final ElConfig bean = context.getBean(ElConfig.class);

        bean.outPrint();

        context.close();

    }
}
