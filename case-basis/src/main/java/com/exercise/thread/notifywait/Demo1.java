package com.exercise.thread.notifywait;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc wait/notify/notifyall 必须在同步方法或者同步块里面调用，调用的对象必须的同步的对象或者是同步class
 * @createtime 2018/3/30 上午11:47
 * @see jdk 1.7
 **/
public class Demo1 {

    public volatile boolean getDelFlag;

    public boolean isGetDelFlag() {
        return getDelFlag;
    }

    public void setGetDelFlag(boolean getDelFlag) {
        this.getDelFlag = getDelFlag;
    }

    public static void main(String[] args) {

        Demo demo = new Demo();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (demo) {
                    while (!demo.isGetDelFlag()) { //自旋监控状态发生变化。消耗cpu，设置休眠可以减少cpu的消耗，但是不能确定多少合适
                        try {
                            System.out.println(Thread.currentThread().getName() + " 等待。。。。");
                            demo.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(Thread.currentThread().getName() + "状态修改为true");
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (demo) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " 获取。。。。");
                        System.out.println("等待状态修改。。。");
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    demo.setGetDelFlag(true);
                    demo.notify();
                    System.out.println("状态修改成功。。。。。");
                }
            }
        }).start();


    }
}
