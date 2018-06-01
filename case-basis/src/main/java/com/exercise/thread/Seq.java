package com.exercise.thread;

import static java.lang.Thread.sleep;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc 多线程环境下，多线操作共享资源，不是原子性操作，会出现安全问题，解决现在不安全问题，可以synchronized 关键字解决同步问题
 * 1，每个类中都有内置锁，排它锁，
 * 2、在方法上面加synchronized 锁的是当前类的实例
 * 3、在静态方法上面加synchronized 锁的是当前类的，Seq.class
 * 4、在代码块是用synchronized 可以是当前内的实例也可以class，也可以object
 * @createtime 2018/3/21 下午3:47
 * @see jdk 1.7
 **/
public class Seq {

    public Object obj = new Object();
    private int i;
    private static int j;

    public int getNum() {
        //可以当前对象，可以对象的实例可以class，也可以object
//        synchronized (this) {
//        }
//        synchronized (Seq.class) {
//        }
        synchronized (obj) {
            return i++;
        }
    }
    public synchronized int getNext() {
        return i++;
    }

    public static synchronized int getSeq(){
        return j++;
    }

    public static void main(String[] args) {

        Seq sq = new Seq();

        new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {
                    System.out.println(Thread.currentThread().getName() + " " + sq.getNext());
                    try {
                        sleep(1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(Thread.currentThread().getName() + " " + sq.getNext());
                    try {
                        sleep(1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(Thread.currentThread().getName() + " " + sq.getNext());
                    try {
                        sleep(1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
