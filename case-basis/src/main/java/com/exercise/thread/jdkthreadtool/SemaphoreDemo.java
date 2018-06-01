package com.exercise.thread.jdkthreadtool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc 信号量，使用场景，限制资源的访问的大小，
 * @createtime 2018/4/8 上午11:24
 * @see jdk 1.7
 **/
public class SemaphoreDemo {

    private AtomicInteger integer = new AtomicInteger();

    public void semaphoreShow(Semaphore semaphore) {
        try {
            semaphore.acquire();
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + " = run " + integer.incrementAndGet() +" 等待获取凭证的线程数量 = " + semaphore.getQueueLength());
        semaphore.release();
    }

    public static void main(String[] args) {

        final ExecutorService executorService = Executors.newFixedThreadPool(20);
        SemaphoreDemo smDemo = new SemaphoreDemo();
        Semaphore sm = new Semaphore(10);

        for (int i = 0; i < 20; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    smDemo.semaphoreShow(sm);
                }
            });
        }

        executorService.shutdown();
    }
}
