package com.exercise.thread.join;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc join 方法，当前线程调用了join，其他线程就等待，直到调用join的线程执行完成，直到自己调用notifyAll唤醒，通知等待该线程对象上面的线程
 *
 * @createtime 2018/4/18 下午4:07
 * @see jdk 1.7
 **/
public class JoinExample {

    public static void main(String[] args) {

        TargetRun targetRun = new TargetRun();

        Thread t = new Thread(targetRun, "测试1");
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName());
    }
}
