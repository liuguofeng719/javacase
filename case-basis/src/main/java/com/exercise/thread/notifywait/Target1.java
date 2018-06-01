package com.exercise.thread.notifywait;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/3/30 下午12:05
 * @see jdk 1.7
 **/
public class Target1 implements Runnable {

    private Demo2 demo2;

    public Target1(Demo2 demo2) {
        this.demo2 = demo2;
    }

    @Override
    public void run() {
        demo2.set();
    }
}
