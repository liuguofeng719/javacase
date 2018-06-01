package com.exercise.thread.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc 有界阻塞队列
 * @createtime 2018/4/3 上午11:26
 * @see jdk 1.7
 **/
public class MyBlockQueue<T> {

    private Object[] items;

    private Lock lock = new ReentrantLock();
    private Condition notEmpty;
    private Condition notFull;

    private int addIndex;
    private int removeIndex;
    private int size;

    public MyBlockQueue(int capacity) {
        this.items = new Object[capacity];
        this.notEmpty = lock.newCondition();
        this.notFull = lock.newCondition();
    }

    public void add(T t) {
        lock.lock();
        try {
            while (size == items.length) {
                try {
                    System.out.println(Thread.currentThread().getName() +" = 队列已满，线程已经等待");
                    notFull.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            items[addIndex] = t;
            if (++addIndex == items.length) {
                addIndex = 0;
            }

            ++size;
            notEmpty.signal();

        } finally {
            lock.unlock();
        }
    }

    public T remove() {
        lock.lock();
        try {
            while (0 == size) {
                try {
                    System.out.println(Thread.currentThread().getName() + " = 队列为空");
                    notEmpty.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            final Object item = items[removeIndex];
            if (++removeIndex == items.length)
                removeIndex = 0;
            --size;
            notFull.signal();
            return (T)item;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        MyBlockQueue<String> myBlockQueue = new MyBlockQueue<>(3);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    myBlockQueue.add("1");
                }
            }
        }).start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true)
//                    myBlockQueue.remove();
//            }
//        }).start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true)
//                    myBlockQueue.remove();
//            }
//        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(myBlockQueue.remove());
                }
            }
        }).start();
    }
}
