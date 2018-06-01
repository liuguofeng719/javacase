package com.exercise.thread.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc 重入锁
 * @createtime 2018/3/29 上午9:58
 * @see jdk 1.7
 **/
public class MyAQSReentrantLock implements Lock {

    private MyAQSSyn myAQSSyn;

    static class MyAQSSyn extends AbstractQueuedSynchronizer {

        @Override
        protected boolean tryAcquire(int arg) {

            //获取全局state的值，如果等于0，说没有没有加锁，通过cas来设置state的值为1
            //设置独占线程
            int state = getState();
            Thread currentThread = Thread.currentThread();
            if (state == 0) {
                if (compareAndSetState(0, arg)) {
                    setExclusiveOwnerThread(currentThread);
                    return true;
                }
            } else if (getExclusiveOwnerThread() == currentThread) {
                int c = state + arg;
                if (c < 0) {
                    throw new IllegalMonitorStateException();
                }
                setState(c);
                return true;
            }

            return false;
        }

        @Override
        protected boolean tryRelease(int release) {
            int c = getState() - release;
            if (getExclusiveOwnerThread() != Thread.currentThread()) {
                throw new IllegalMonitorStateException();
            }
            boolean free = false;
            if (c == 0) {
                free = true;
                setExclusiveOwnerThread(null);//把独占线程设置为空
            }
            setState(c);//修改状态
            return free;
        }

        protected Condition newCondition() {
            return new ConditionObject();
        }
    }

    public MyAQSReentrantLock() {
        myAQSSyn = new MyAQSSyn();
    }

    @Override
    public void lock() {
        myAQSSyn.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        myAQSSyn.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return myAQSSyn.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return myAQSSyn.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        myAQSSyn.release(1);
    }

    @Override
    public Condition newCondition() {
        return myAQSSyn.newCondition();
    }
}
