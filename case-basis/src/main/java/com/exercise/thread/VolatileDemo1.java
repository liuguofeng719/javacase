package com.exercise.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author guofeng
 * @version 1.0
 * @desc volatile 保证线程之间的可见性、禁止指令重排序、不保证原子性
 * 1、代码验证可见性
 * @createtime 2019/10/14 9:32 PM
 * @see jdk 1.8
 **/
public class VolatileDemo1 {

    public static class MyData {

//        int number = 0;
        volatile int number = 0;

        public void add() {
            number = 60;
        }
    }

    public static void main(String[] args) {

        MyData myData = new MyData();
        new Thread(() -> {
            try {
                System.out.println("======come in" + Thread.currentThread().getName());
                // 先休息3秒，让主线程去获取值，线程1在获取修改值
                Thread.sleep(TimeUnit.MILLISECONDS.toMillis(3000));
                myData.add();
                System.out.println("number 修改好的值为=" + myData.number);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, String.valueOf("1")).start();

        // 主线检测线程的值是否发生变化
        while (myData.number == 0) {

        }

        System.out.println("修改好的值" + myData.number + "threadName=" + Thread.currentThread().getName());
    }
}
