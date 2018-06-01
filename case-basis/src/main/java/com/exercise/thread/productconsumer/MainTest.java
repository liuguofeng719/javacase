package com.exercise.thread.productconsumer;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/3/30 下午3:42
 * @see jdk 1.7
 **/
public class MainTest {

    public static void main(String[] args) {

        Tmall tmall = new Tmall();
        ProductTarget p1 = new ProductTarget(tmall);
        ConsumerTarget c1 = new ConsumerTarget(tmall);

        new Thread(p1).start();
        new Thread(p1).start();
        new Thread(p1).start();
        new Thread(p1).start();
        new Thread(p1).start();

        new Thread(c1).start();
        new Thread(c1).start();
        new Thread(c1).start();
        new Thread(c1).start();
        new Thread(c1).start();
        new Thread(c1).start();
        new Thread(c1).start();
        new Thread(c1).start();

    }
}
