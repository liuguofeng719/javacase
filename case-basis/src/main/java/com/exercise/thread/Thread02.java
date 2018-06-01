package com.exercise.thread;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc 通过runnable 创建线程
 * @createtime 2018/3/21 上午10:49
 * @see jdk 1.7
 **/
public class Thread02 implements Runnable {

    @Override
    public void run() {
        while (true) System.out.println("thread name =" + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        Thread th = new Thread(new Thread02());
        th.start();
        while (true) System.out.println("thread name = " + Thread.currentThread().getName());
    }
}
