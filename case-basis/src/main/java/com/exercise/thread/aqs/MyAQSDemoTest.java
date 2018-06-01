package com.exercise.thread.aqs;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/3/29 上午10:24
 * @see jdk 1.7
 **/
public class MyAQSDemoTest {

    private MyAQSLock myAQSLock = new MyAQSLock();

    private int value;

    public int getNext() throws Exception {
        try {
            myAQSLock.lock();
            Thread.sleep(200);
            return value++;
        } catch (InterruptedException e) {
            throw new InterruptedException(e.getMessage());
        } finally {
            myAQSLock.unlock();
        }
    }

    public void a(){
        try {
            myAQSLock.lock();
            System.out.println("a");
            b();
        } finally {
            myAQSLock.unlock();
        }
    }

    public void b(){
        try {
            myAQSLock.lock();
            System.out.println("b");
        } finally {
            myAQSLock.unlock();
        }
    }

    public static void main(String[] args) {

        MyAQSDemoTest m = new MyAQSDemoTest();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (; ; ) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " = " + m.getNext());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (; ; ) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " = " + m.getNext());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                m.a();
            }
        }).start();


    }
}
