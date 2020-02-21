package com.exercise.thread.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author guofeng
 * @version 1.0
 * @see jdk 1.8
 * 1、线程可以中断自己、也可以通过其他线程中断运行的线程，通过标志位来实现，初始化中断值为false，调用interrupt中断方法变成true
 * 2、Thread类 提供了3个方法
 * isInterrupted 检查当前线程是否中断、返回检查的结果
 * interrupted 检查当前线程是否中断，返回检查状态的值并且把当前线程的中断状态设置为false
 * interrupt 中断，设置当前线程的标志位为true
 * 3、如果运行的线程处于挂起状态、休眠状态，其他线程来中断改线程会抛出InterruptedException,并且中断的状态变成false
 * Object#wait
 * Object#wait(long,long)
 * Thread.sleep(1000)
 * Thread.sleep(1000,100)
 * Thread.join()
 * Thread.join(long)
 * Thread.join(long,long)
 * 4、中断是优雅停止线程的一种方式、可以通过全局变量valatile boole isFlag 标志位来优雅停止线程
 **/
@Slf4j
public class InterruptedDemo {


    public static void main(String[] args) throws Exception {
//        checkCurrentThreadInterrupt();
//        System.out.println("=======================");
//        interruptState();
//        System.out.println("=======================");

        interruptStateThrowsException();
    }

    /**
     * 调用Object.wait/Thread/sleep/Thread.join 方法，调用中断会抛出异常
     */
    private static void interruptStateThrowsException() throws Exception {
        Thread t3 = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("[" + Thread.currentThread().getName() + "] = " + Thread.currentThread().isInterrupted());
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    log.error("InterruptedException {} ", e.getMessage());
                    // 标志位已经复位成false
                    System.out.println("[" + Thread.currentThread().getName() + "] = " + Thread.currentThread().isInterrupted());
                    // 中断状态设置为true
                    Thread.currentThread().interrupt();
                    // 也可以通过break 中断跳出当前循环
                }
            }
            log.info("中断出来了");
        }, "t3");

        t3.start();
        TimeUnit.SECONDS.sleep(1);
        t3.interrupt();
        // 打印中断状态
        System.out.println(t3.isInterrupted());
    }

    /**
     * 中断不跑异常
     */
    private static void interruptState() {
        Thread t1 = new Thread(() -> {
            run();
        }, "t1");
        Thread t2 = new Thread(() -> {
            run();
        }, "t2");

        t1.start();
        t2.start();

        // 调用中断
        t1.interrupt();
        t2.interrupt();

        System.out.println("主线程执行完成" + Thread.currentThread().getName());
    }

    private static void run() {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("未中断 = " + Thread.currentThread().getName() + " 中断状态= " + Thread.currentThread().isInterrupted());
            // 让出cpu使用权，但是还是回去竞争cpu使用权
            Thread.yield();
        }
        System.out.println(" 中断 " + Thread.currentThread().getName() + " 中断状态= " + Thread.currentThread().isInterrupted());
    }

    /**
     * 自己检查自己中断状态，并且复位中断状态
     */
    private static void checkCurrentThreadInterrupt() {
        System.out.println(Thread.currentThread().getName());
        // 返回当前线程的中断状态
        System.out.println(Thread.currentThread().isInterrupted());
        // 复位中断状态
        System.out.println(Thread.interrupted());
        // 设置中断状态
        Thread.currentThread().interrupt();
        // 返回当前，线程的中断状态
        System.out.println(Thread.currentThread().isInterrupted());
        // 测试当前线程是否中断状态，并且复位中断状态
        System.out.println(Thread.interrupted());
        // 返回中断状态
        System.out.println(Thread.currentThread().isInterrupted());
    }
}
