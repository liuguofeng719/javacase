package com.springboot2x.aop;

/**
 * @author guofeng
 * @version 1.0
 * @desc
 * @createtime 2019/2/16 3:29 PM
 * @see jdk 1.7
 **/
public class WaiterImpl implements Waiter {

    @Override
    public void greetTo(String name) {
        System.out.println("greet to " + name);
    }

    @Override
    public String serveTo(String name) {
        System.out.println("serving to " + name);
        return "serveTo";
    }
}
