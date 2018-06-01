package com.exercise.thread.productconsumer;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/3/30 下午3:38
 * @see jdk 1.7
 **/
public class Tmall {

    private int total;//总量
    public final int MAX_TOTAL = 10;

    /**
     * 生产
     */
    public synchronized void push() {
        while (total >= MAX_TOTAL) {
            try {
                System.out.println(Thread.currentThread().getName() + "生产商品已经上限，停止生产");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        total++;
        notifyAll();
        System.out.println(Thread.currentThread().getName() + "生产商品数量 " + total);
    }

    /**
     * 消费
     */
    public synchronized void take() {
        while (total <= 0) {
            try {
                System.out.println(Thread.currentThread().getName() + "商品数量为零,停止消费");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        total--;
        notifyAll();
        System.out.println(Thread.currentThread().getName() + "商品数量消数量，剩余 " + total);
    }
}
