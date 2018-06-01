package com.example.demo.core.beaninit;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/6/1 下午2:50
 * @see jdk 1.7
 **/
public class UserBean {

    public UserBean() {
        System.out.println("userbean construct");
    }

    public void init() {
        System.out.println("userBeanInit");
    }

    public void destroy() {
        System.out.println("userBeanDestroy");
    }
}
