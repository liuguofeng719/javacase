package com.exercise.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author guofeng
 * @version 1.0
 * @see jdk 1.8
 **/
class MyData implements Runnable {
    private String dataA;
    private String dataB;

    public MyData(String dataA, String dataB) {
        this.dataA = dataA;
        this.dataB = dataB;
    }


    public void getDataB() {
        synchronized (dataB) {
            System.out.println(Thread.currentThread().getName() + " 获取线程 " + dataB);
            try {
                TimeUnit.SECONDS.sleep(5L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (dataA) {
                System.out.println(Thread.currentThread().getName() + "获取线程" + dataA);
            }
        }
    }

    @Override
    public void run() {
        synchronized (dataA) {
            System.out.println(Thread.currentThread().getName() + " 获取线程 " + dataA);
            try {
                TimeUnit.SECONDS.sleep(5L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (dataB) {
                System.out.println(Thread.currentThread().getName() + "获取线程" + dataB);
            }
        }
    }
}

public class DeadLock {

    // jps -l
    // jstack pid 查看是否有死锁
    public static void main(String[] args) {
        MyData myData = new MyData("aaa", "bbb");
        MyData myData1 = new MyData("bbb", "aaa");
        new Thread(myData,"AAA").start();
        new Thread(myData1,"BBB").start();

//        new Thread(()->myData.getDataB(),"AAA").start();
//        new Thread(()->myData1.getDataB(),"BBB").start();


    }
}
