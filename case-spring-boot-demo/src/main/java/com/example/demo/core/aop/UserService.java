package com.example.demo.core.aop;

import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc 编写拦截的方法
 * @createtime 2018/5/31 下午4:36
 * @see jdk 1.7
 **/
@Service
public class UserService {

    @Action(name = "注解式拦截 add 操作")
    public void add() {
        System.out.println("执行add 操作");
    }
}
