package com.exercise.thread.lock;

import java.util.concurrent.TimeUnit;

/**
 * @author guofeng
 * @version 1.0
 * @desc
 * @createtime 2019/1/18 3:35 PM
 * @see jdk 1.7
 **/
public class SynchronizedTest {

    public static synchronized void show() {
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
    }

    public synchronized void show1() {
        System.out.println(Thread.currentThread().getName());
    }

}

class Test {
    public static void main(String[] args) {

        SynchronizedTest synchronizedTest = new SynchronizedTest();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronizedTest.show();
            }
        },"aaa").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronizedTest.show1();
            }
        },"b").start();
    }
}
