package com.exercise.thread.notifywait;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc 通过自旋来实现线程之间的通信
 * @createtime 2018/3/30 上午10:37
 * @see jdk 1.7
 **/
public class Demo {

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
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                demo.setGetDelFlag(true);
                System.out.println("状态修改成功");
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!demo.isGetDelFlag()) { //自旋监控状态发生变化。消耗cpu，设置休眠可以减少cpu的消耗，但是不能确定多少合适
                    try {
                        TimeUnit.MILLISECONDS.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("状态修改为true");
            }
        }).start();

    }
}
