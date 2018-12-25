package com.exercise.jvm;

import java.io.File;

/**
 * @author guofeng
 * @version 1.0
 * @desc
 * @createtime 2018/12/23 11:49 AM
 * @see jdk 1.7
 **/
public class JVMHelloWorldDemo {

    private final int i = 0;
    private static int k = 0;
    private Object obj = new Object();
    private int sss = 0;
    private long bb = 0l;
    private double dd = 0;
    private volatile JVMHelloWorldDemo demo = new JVMHelloWorldDemo();

    public void methodOne(int i) {
        int j = 0;
        int sum = i + j;
        Object abc = obj;
        long start = System.currentTimeMillis();
        this.methodTwo();
        return;
    }

    public void methodTwo() {
        File file = new File("");
    }

    public static void methodThree() {
        methodThree();
    }

    public static void main(String[] args) {
        methodThree();
    }
}
