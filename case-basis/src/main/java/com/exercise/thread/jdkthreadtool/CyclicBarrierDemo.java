package com.exercise.thread.jdkthreadtool;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc 循环屏障 和 CountDownlatch 的区别，在CountDownLatch 计数器只能使用一次，
 * 然而CyclicBarrier 能使用重复使用，通过调用reset方法，是使用在复杂的业务场景，
 * 例如：计算发生失败，可以重置计数器，重新让线程执行一次
 *
 * 循环屏障，如果设置的3个线程，达到屏障点，如果只开了2个线程，2个线程会一直等待
 * @createtime 2018/4/8 上午10:53
 * @see jdk 1.7
 **/
public class CyclicBarrierDemo {

    public Random random = new Random();

    public void cyclicShow(CyclicBarrier cyclicBarrier) {

        try {
            Thread.sleep(random.nextInt(5000));
            System.out.println(Thread.currentThread().getName() + "= ");
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CyclicBarrierDemo cyclicBarrierDemo = new CyclicBarrierDemo();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new Runnable() {
            @Override
            public void run() {
                System.out.println("执行完成");
            }
        });

        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    cyclicBarrierDemo.cyclicShow(cyclicBarrier);
                }
            }).start();
        }
    }
}
