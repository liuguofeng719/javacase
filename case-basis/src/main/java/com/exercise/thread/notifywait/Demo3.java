package com.exercise.thread.notifywait;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc 让3个线程按照顺序执行哪里 1，2，3 || 1，2，3
 * @createtime 2018/4/2 上午9:58
 * @see jdk 1.7
 **/
public class Demo3 {

    private volatile int singal;

    public synchronized void a() {
        while (singal != 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("a");
        singal++;
        notify();
    }

    public synchronized void b() {
        while (singal != 1) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("b");
        singal++ ;
        notifyAll();
    }

    public synchronized void c() {
        while (singal != 2) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("c");
        singal = 0;
        notifyAll();
    }

    public static void main(String[] args) {
        Demo3 demo3 = new Demo3();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    demo3.a();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    demo3.b();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    demo3.c();
                }
            }
        }).start();
    }
}
