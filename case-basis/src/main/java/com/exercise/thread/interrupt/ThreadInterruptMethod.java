package com.exercise.thread.interrupt;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc 线程中断
 * @createtime 2018/4/9 下午4:57
 * @see jdk 1.7
 **/
public class ThreadInterruptMethod {

    public static void main(String[] args) {
//        TargetRun targetRun = new TargetRun();
//        final Thread thread = new Thread(targetRun);
//        thread.start();
//        try {
//            Thread.sleep(200);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        thread.interrupt();

        testStatus();
    }
    public static void testStatus() {
        System.out.println(Thread.currentThread().getContextClassLoader().getResource("").getPath());
        System.out.println("--------------------------");
        Thread.currentThread().interrupt();
        System.out.println(Thread.interrupted());
        System.out.println(Thread.interrupted());
        System.out.println(Thread.currentThread().isInterrupted());
        System.out.println(Thread.currentThread().isInterrupted());
        System.out.println("--------------------------");
        Thread.currentThread().interrupt();
        System.out.println(Thread.currentThread().isInterrupted());
        System.out.println(Thread.currentThread().isInterrupted());
        System.out.println("--------------------------");
        Thread.currentThread().interrupt();
        System.out.println(Thread.interrupted());
        System.out.println(Thread.interrupted());
    }

}
