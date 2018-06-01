package com.exercise.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc 自定义重入锁，
 * @createtime 2018/3/23 下午4:42
 * @see jdk 1.7
 **/
public class MyReentrantLock implements Lock {

    private boolean isLock = false; //标志锁是否锁定

    private int count = 0;//重入次数

    private Thread threadBy = null ;//当前线程

    @Override
    public synchronized void lock() {

        Thread currentThread = Thread.currentThread();

        while (isLock && currentThread != threadBy) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        threadBy = currentThread;
        isLock = true;
        count++;
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public synchronized void unlock() {
        if (threadBy != null) {
            count--;
            if (count == 0) {
                isLock = false;
                notify();
            }
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
