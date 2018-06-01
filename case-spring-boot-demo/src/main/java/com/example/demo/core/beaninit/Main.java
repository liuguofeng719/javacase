package com.example.demo.core.beaninit;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/6/1 下午2:57
 * @see jdk 1.7
 **/
public class Main {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);

        final UserBean userBean = context.getBean(UserBean.class);
        final JSR250WayService wayService = context.getBean(JSR250WayService.class);

        context.close();
    }
}
