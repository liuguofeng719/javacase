package com.exercise.thread.synchroized;

import java.util.concurrent.TimeUnit;

/**
 * @author guofeng
 * @version 1.0
 * @see jdk 1.8
 **/
class Person {
    // 锁的实例
    public synchronized void show1() {
        try {
            TimeUnit.SECONDS.sleep(4);
            System.out.println("show time 1");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    // 锁的实例
    public synchronized void show2() {
        System.out.println("show time 2");
    }

    // 锁的Class 对象
//    public static synchronized void show2() {
//        System.out.println("show time 2");
//    }

    public void show3() {
        // 锁的实例
//        synchronized (this) {
//            System.out.println("show time 3");
//        }
        // 锁的Class对象
        synchronized (Person.class) {
            System.out.println("show time 3");
        }
    }

}

public class TestSynchroized {

    public static void main(String[] args) throws Exception {
        Person p1 = new Person();
        Person p2 = new Person();

        new Thread(() -> {
            p1.show1();
        }, "A").start();

        TimeUnit.SECONDS.sleep(2);

        new Thread(() -> {
            p1.show2();
        }, "B").start();

        new Thread(() -> {
            p1.show3();
        }, "B").start();
    }
}
