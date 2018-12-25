package com.exercise.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author guofeng
 * @version 1.0
 * @desc
 * @createtime 2018/12/24 6:24 PM
 * @see jdk 1.7
 **/
public class VolatilDemoAtomic {

    private volatile long age = 0;
    AtomicInteger integer = new AtomicInteger();

    public long get() {
        return age;
    }

    public void incrment() {
        age++;
    }

    public long getAtomic() {
        return integer.get();
    }

    public long setAtomic() {
        return integer.incrementAndGet();
    }


    public static void main(String[] args) {
        VolatilDemoAtomic volatilDemoAtomic = new VolatilDemoAtomic();
        new Thread(() -> {
            while (true) {
//                volatilDemoAtomic.setAtomic();
                volatilDemoAtomic.incrment();
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            while (true) {
                try {
//                    volatilDemoAtomic.setAtomic();
                    volatilDemoAtomic.incrment();
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            while (true) {
//                System.out.println(volatilDemoAtomic.getAtomic());
                System.out.println(volatilDemoAtomic.get());
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
