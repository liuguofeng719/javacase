package com.exercise.jvm.init;

/**
 * @author guofeng
 * @version 1.0
 * @see jdk 1.8
 *
 * 常量在编译阶段会存入调用类的常量池中，本质上没有直接引用到定义常量的类，因此不会触发定义常量类的初始化
 **/
public class ConstClass {
    static {
        System.out.println(" ConstClass class");
    }
    public static final String HELLOWOLD = "hello world";
}

class TestConstClass {

    public static void main(String[] args) {
        System.out.println(ConstClass.HELLOWOLD);
    }
}
