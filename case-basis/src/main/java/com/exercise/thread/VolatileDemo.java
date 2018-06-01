package com.exercise.thread;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc 1、volatile 作用 保证了线程之间的可见性,但是不能保证线程的安全性，第一个线程修改了值，第二个线程去获取的的时候就是最新的值
 * 是如何保证线程之间的可见性？
 * 1，第一种方式通过在总线上增加Lock#指令
 * 2、通过缓存一致性协议，嗅探的方式， 保证指令不重排序 例如：
 * @createtime 2018/3/22 下午5:21
 * @see jdk 1.7
 **/
public class VolatileDemo {

    public volatile boolean isStop = false;

    public static void main(String[] args) {

        VolatileDemo volatileDemo = new VolatileDemo();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 10; i++) {
                    try {
                        System.out.println("第" + i + "次");
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                volatileDemo.isStop = true;
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!volatileDemo.isStop) {

                }
                System.out.println("任务结束");
            }
        }).start();
    }
}
