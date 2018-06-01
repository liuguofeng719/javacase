package com.exercise.lock;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/3/23 下午4:49
 * @see jdk 1.7
 **/
public class MyLockDemo {

    private MyLock myLock = new MyLock();

    private int i;

    public int lockDemoA() {
        myLock.lock();
        int j = i++;
        myLock.unlock();
        return j;
    }

    public static void main(String[] args) {

        MyLockDemo myLockDemo = new MyLockDemo();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(Thread.currentThread().getName() + " = " + myLockDemo.lockDemoA());
                }
            }
        },"A-1").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(Thread.currentThread().getName() + " = " + myLockDemo.lockDemoA());
                }
            }
        },"A-2").start();
    }
}
