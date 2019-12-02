package com.exercise.thread.lock;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/3/23 下午4:49
 * @see jdk 1.7
 **/
public class MyReentrantLockDemo {

    MyReentrantLock myLock = new MyReentrantLock();

    private int i;

    public int lockDemoA() {
        myLock.lock();
        int j = i++;
        lockDemoB();
        myLock.unlock();
        return j;
    }

    public int lockDemoB() {
        myLock.lock();
        int j = i++;
        myLock.unlock();
        return j;
    }

    public static void main(String[] args) {

        MyReentrantLockDemo myLockDemo = new MyReentrantLockDemo();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(Thread.currentThread().getName() + " = " + myLockDemo.lockDemoA());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {

                    }
                }
            }
        },"A-1").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(Thread.currentThread().getName() + " = " + myLockDemo.lockDemoA());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {

                    }
                }
            }
        },"A-2").start();
    }
}
