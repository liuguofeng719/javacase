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
public class MyAQSReentrantDemoTest {

    private MyAQSReentrantLock myAQSLock = new MyAQSReentrantLock();


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

        MyAQSReentrantDemoTest m = new MyAQSReentrantDemoTest();
        for (int i = 0; i <3 ; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    m.a();
                }
            }).start();
        }
    }
}
