package com.exercise.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * lock 锁的优点，确定需要显示的加锁和释放锁
 * 1、不加锁的，实现高并发
 * 2、手动中断锁
 * 3、超时锁
 * @createtime 2018/3/23 上午10:58
 * @see jdk 1.7
 **/
public class Sequence {

    private int i;

    Lock lock = new ReentrantLock();

    public int getNext() {
        int m;
        try {
            lock.lock();
            m = i++;
        } finally {
            lock.unlock();
        }
        return m;
    }

    public static void main(String[] args) {

        Sequence seq = new Sequence();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(Thread.currentThread().getName() + " = " + seq.getNext());
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(Thread.currentThread().getName() + " = " + seq.getNext());
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
