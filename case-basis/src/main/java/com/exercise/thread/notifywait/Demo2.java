package com.exercise.thread.notifywait;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/3/30 下午12:05
 * @see jdk 1.7
 **/
public class Demo2 {

    private volatile int signal;

    public synchronized void set() {
        System.out.println(Thread.currentThread().getName() + " 开始设置。。。。");
        signal = 1;
//        notify();
        notifyAll(); //唤醒同一把锁的所有的线程，但是抢占到CPU使用只有一个线程
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " 设置成功。。。。");
    }

    public synchronized int get() {
        System.out.println(Thread.currentThread().getName() + " 开始获取。。。。");
        while (signal != 1) {
            try {
                wait();//调用等待的时候，会释放当前放的锁
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " 获取成功。。。。");
        return signal;
    }

    public static void main(String[] args) {

        Demo2 dm = new Demo2();

        Target1 t1 = new Target1(dm);

        Target2 t2 = new Target2(dm);

        new Thread(t2).start();
        new Thread(t2).start();
        new Thread(t2).start();
        new Thread(t2).start();
        new Thread(t1).start();
    }
}
