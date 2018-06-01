package com.example.demo.core.beaninit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/6/1 下午2:51
 * @see jdk 1.7
 **/
public class JSR250WayService {

    public JSR250WayService() {
        System.out.println("JSR250WayService construct");
    }

    @PostConstruct  //在构造函数之后执行
    public void init() {
        System.out.println("jsr250 init");
    }


    @PreDestroy //在销毁bean之前执行
    public void destory() {
        System.out.println("jsr250 destory");
    }
}
