package com.exercise.thread.productconsumer;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/3/30 下午3:40
 * @see jdk 1.7
 **/
public class ProductTarget implements Runnable {

    private Tmall tmall;

    public ProductTarget(Tmall tmall) {
        this.tmall = tmall;
    }

    @Override
    public void run() {
        while (true) {
            try {
                tmall.push();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
