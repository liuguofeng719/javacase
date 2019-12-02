package com.exercise.thread.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author guofeng
 * @version 1.0
 * @see jdk 1.8
 **/

class MyData {
    private volatile boolean flag = true;
    private BlockingQueue<String> blockingQueue;


    AtomicInteger atomicInteger = new AtomicInteger();

    public MyData(BlockingQueue<String> synchronousQueue) {
        this.blockingQueue = synchronousQueue;
    }

    public void increment() {

        try {
            boolean retValue;
            String str ;
            while (flag) {
                str = "生成蛋糕" + atomicInteger.incrementAndGet();
                blockingQueue.put(str);
                System.out.println(str);
//                retValue = blockingQueue.offer(str,2L,TimeUnit.SECONDS);
//                if (retValue) {
//                    System.out.println(Thread.currentThread().getName() + str);
//                } else {
//                    System.out.println(Thread.currentThread().getName() + " 生成成功失败");
//                }
                TimeUnit.SECONDS.sleep(1L);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void decrement() {
        try {
            while (flag) {
                System.out.println("消费 = " + blockingQueue.take());
                // 如果2秒钟没有取到数据就返回为空，退出循环
//                final String poll = blockingQueue.poll(2L,TimeUnit.SECONDS);
//                if (poll == null || poll.equals("")) {
//                    System.out.println(Thread.currentThread().getName() + " 消费完成 ");
//                    flag = false;
//                    break;
//                }
//                System.out.println(Thread.currentThread().getName() + " 消费成功 " + poll);
                TimeUnit.SECONDS.sleep(1L);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void stop() {
        flag = false;
    }
}

public class BlockingQueueDemo {

    public static void main(String[] args) {
        MyData myData = new MyData(new SynchronousQueue<>());
        new Thread(() -> {
            myData.increment();
        }, "AAAA").start();
        new Thread(() -> {
            myData.decrement();
        }, "BBBB").start();
    }
}
