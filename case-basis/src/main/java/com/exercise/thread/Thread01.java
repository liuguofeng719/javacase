package com.exercise.thread;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc 通过extends Thread 实现线程
 * @createtime 2018/3/21 上午10:47
 * @see jdk 1.7
 **/
public class Thread01 extends Thread {

    @Override
    public void run() {
        while (true)
        System.out.println("我开始了");
    }

    public static void main(String[] args) {
        Thread01 thread01 = new Thread01();
        thread01.start();
    }
}
