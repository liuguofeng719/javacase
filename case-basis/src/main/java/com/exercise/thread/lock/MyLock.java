package com.exercise.thread.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc 自定义锁，
 * @createtime 2018/3/23 下午4:42
 * @see jdk 1.7
 **/
public class MyLock implements Lock {

    private boolean isLock = false; //标志锁是否锁定

    @Override
    public synchronized void lock() {
        System.out.println(" lock= "+  Thread.currentThread().getName());
        while (isLock) {//第1个线程进来的时候不等待
            try {
                System.out.println(" wait= "+  Thread.currentThread().getName());
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        isLock = true;
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
        isLock = false;
        notify();
        System.out.println(" unlock= "+  Thread.currentThread().getName());
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
