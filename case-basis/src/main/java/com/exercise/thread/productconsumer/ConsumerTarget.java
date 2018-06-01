package com.exercise.thread.productconsumer;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/3/30 下午3:41
 * @see jdk 1.7
 **/
public class ConsumerTarget implements Runnable {

    private Tmall tmall;

    public ConsumerTarget(Tmall tmall) {
        this.tmall = tmall;
    }

    @Override
    public void run() {
        while (true) {
            try {
                this.tmall.take();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
