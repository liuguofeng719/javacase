package com.exercise.thread;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc Timer 实现线程
 * @createtime 2018/3/21 上午11:12
 * @see jdk 1.7
 **/
public class Thread04 {
    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("time");
            }
        }, 1000);
    }
}
