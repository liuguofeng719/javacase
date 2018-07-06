package com.boot.demo;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/6/22 下午6:25
 * @see jdk 1.7
 **/
public class HelloServiceImpl {

    private String msg;

    public String sayHello() {
        return "hello:" + msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
