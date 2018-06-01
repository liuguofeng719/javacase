package com.exercise.thread.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc 通过Condition 实现线程 按照顺序交替
 * @createtime 2018/4/2 上午10:28
 * @see jdk 1.7
 **/
public class Demo1 {

    private Lock l = new ReentrantLock();
    private Condition a = l.newCondition();
    private Condition b = l.newCondition();
    private Condition c = l.newCondition();

    private volatile int signal;

    public void a() {
        try {
            l.lock();
            while (signal != 0) {
                try {
                    a.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("a");
            signal++;
            b.signal();
        } finally {
            l.unlock();
        }
    }

    public void b() {
        try {
            l.lock();
            while (signal != 1) {
                try {
                    b.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("b");
            signal++;
            c.signal();
        } finally {
            l.unlock();
        }
    }

    public void c() {
        try {
            l.lock();
            while (signal != 2) {
                try {
                    c.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("c");
            signal = 0;
            a.signal();
        } finally {
            l.unlock();
        }
    }

    public static void main(String[] args) {

        Demo1 demo1 = new Demo1();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    demo1.a();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    demo1.b();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    demo1.c();
                }
            }
        }).start();
    }

}
