package com.exercise.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/3/21 下午5:14
 * @see jdk 1.7
 **/
public class Singleton01 {

    public static volatile Singleton01 singleton;

    private Singleton01() {

    }

    public static Singleton01 getInstance() {
        if (null == singleton) {
            synchronized (Singleton01.class) {
                if (null == singleton) {
                    singleton = new Singleton01();//指令重排序会导致
                }
            }
        }
        return singleton;
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 20; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " " + getInstance());
                }
            });
        }
        executorService.shutdown();
    }
}
