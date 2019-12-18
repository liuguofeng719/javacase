package com.exercise.thread.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author guofeng
 * @version 1.0
 * @see jdk 1.8
 *
 * 参考连接 https://segmentfault.com/a/1190000016056471
 * 1、synchronized 同步普通方法上面，锁的是当前对象的实例
 * 2、synchronized 同步在静态方法上面、锁的是当前类的class对象
 * 3、synchronized 同步块中，锁的块中synchronized(对象)
 * 4、Thread.sleep 和 Thread.currentThread().sleep 方法一样的作用，没有区别，都是作用于调用该方法的线程
 * 5、Thread.sleep 方法会让出cpu使用权，但是不会释放监视器锁
 *      例如：如果方法是同步方法，方法里面调用了Thread.Sleep(1000L)方法，但是其他线程也不能获取该方法的监视器锁，也会被阻塞，在阻塞队列里面
 * 6、Object#wait() 该方法会释放cpu的使用权以及释放监视器锁、如果调用了wait或者设置等待超时wait(100L) 不然必须通过notify()和notifyAll，
 *      3个方法必须在同步关键字修饰的方法和代码块中使用
 * 7、suspend()、resume()和stop() 标记过时，不推荐使用，是因为调用之后，
 *    线程不会释放已经占有的资源(比如锁)，而是占有着资源进入睡眠状态，这样容易引发死锁问题
 *    以suspend()方法为例，在调用后，线程不会释放已经占有的资 源(比如锁)，而是占有着资源进入睡眠状态，这样容易引发死锁问题。
 *    同样，stop()方法在终结 一个线程时不会保证线程的资源正常释放，通常是没有给予线程完成资源释放工作的机会， 因此会导致程序可能工作在不确定状态下
 * 8、Thread.yield() 静态方法，会让出cpu使用权，等cpu调度，但是cpu是否调度由cpu决定，每个厂商实现又不一样、
 *      但是状态在runnable中，ready状态，有可能马上又会获取到锁，参与CPU的竞争.Thread.sleep 让出cpu使用权，不会在参与cpu竞争
 * 9、join()方法，作用于当前调用该方法的线程，main线程调用该代码就是main线程，去检查Thread对象join
 * 10、isAlive()方法,检查join方法的Thread对象线程是否存活
 * 11、一定要分清执行代码的线程和方法所属的线程类所代表的线程
 **/
public class ThreadDemo {

    int count = 0;

    public synchronized void getData1() {
        System.out.println("开始" + Thread.currentThread().getName());
        count++;
        try {
            Thread.sleep(5 * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("结束" + Thread.currentThread().getName());
    }

    public synchronized void getData2() {
        System.out.println("线程进来 --> 线程名字" + Thread.currentThread().getName());
        count++;
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程结束 --> 线程名字" + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
//        testSynchronized();
        printlnThread("主从线程");
        // 一定要分清执行代码的线程和方法所属的线程类所代表的线程
        // thread-01 执行
        final Thread thread = new Thread(() -> {
            printlnThread("我是自定义线程");
            try {
                printlnThread("我现在休息10秒");
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            printlnThread("我休息完了");

        }, "thread-01");

        // main 线程
        thread.start();

        try {
            // main 线程、
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        printlnThread("执行完成");

    }

    private static void printlnThread(String desc){
        System.out.println("["+Thread.currentThread().getName() + "] =  desc = " + desc);
    }

    private static void testSynchronized() {
        ThreadDemo threadDemo = new ThreadDemo();

        for (int i = 0; i < 3; i++) {
            new Thread(() -> threadDemo.getData1(), "sleep-" + i).start();
        }

        for (int i = 0; i < 3; i++) {
            new Thread(() -> threadDemo.getData2(), "wait-" + i).start();
        }
    }
}
