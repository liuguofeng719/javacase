package com.boot.demo;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/6/22 下午6:20
 * @see jdk 1.7
 **/
@ConfigurationProperties(prefix = "hello")
public class HelloConfigProperties {

    public static final String DEFAULT_MSG = "hello";

    private String msg = DEFAULT_MSG;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
