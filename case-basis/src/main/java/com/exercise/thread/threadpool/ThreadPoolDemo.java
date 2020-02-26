package com.exercise.thread.threadpool;

import io.netty.util.concurrent.DefaultThreadFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author guofeng
 * @version 1.0
 * @desc 1、创建线程的3种方式，继承Thread 、实现Runnable 接口、通过线程池
 * @see jdk 1.8
 **/
public class ThreadPoolDemo {

    public static void main(String[] args) throws Exception {

        final int processors = Runtime.getRuntime().availableProcessors();
        System.out.println(processors);

//        schedule() 方法允许在指定的延迟后执行一次任务
//        scheduleAtFixedRate() 方法允许在指定的初始延迟后执行任务，然后以一定的周期重复执行，其中 period 参数用于指定两个任务的开始时间之间的间隔时间，因此任务执行的频率是固定的。
//        scheduleWithFixedDelay() 方法类似于 scheduleAtFixedRate() ，它也重复执行给定的任务，但period 参数用于指定前一个任务的结束和下一个任务的开始之间的间隔时间。也就是指定下一个任务延时多久后才执行。执行频率可能会有所不同，具体取决于执行任何给定任务所需的时间。
        ScheduledExecutorService executorService =
                new ScheduledThreadPoolExecutor(4, new DefaultThreadFactory("scheduled"),new ThreadPoolExecutor.AbortPolicy());

        executorService.schedule(()->{
            System.out.println(Thread.currentThread().getName());

        }, 1L,TimeUnit.SECONDS );
        final ScheduledFuture<Integer> schedule = executorService.schedule(() -> 1024, 1L, TimeUnit.SECONDS);
        while (!schedule.isDone()) {

        }
        System.out.println("schedule = " + schedule.get().intValue());
        // period 一段时间内执行的频率
        executorService.scheduleAtFixedRate(
                ()-> System.out.println(Thread.currentThread().getName()), 1L, 2, TimeUnit.SECONDS);
        // 执行任务完成，下一个任务延迟一定的时间再次执行
        executorService.scheduleWithFixedDelay(()-> System.out.println("===="+Thread.currentThread().getName()),2L , 1L, TimeUnit.SECONDS);
    }

    private static void threadPoolUse() {

        // 0000 0000 0000 0000 0000 0000 0000 0001
        // <<
        // 0010 0000 0000 0000 0000 0000 0000 0000
        // -
        // 0000 0000 0000 0000 0000 0000 0000 0001
        // =
        // 0000 1111 1111 1111 1111 1111 1111 1111

        // 1110 0000 0000 0000 0000 0000 0000 0000
        // 0
        // 0010 0000 0000 0000 0000 0000 0000 0000
        // 0100 0000 0000 0000 0000 0000 0000 0000
        // 0110 0000 0000 0000 0000 0000 0000 0000

        System.out.println(Integer.toBinaryString((1 << 29) - 1));
        System.out.println(Integer.toBinaryString(-1 << 29));
        System.out.println(Integer.toBinaryString(0 << 29));
        System.out.println(Integer.toBinaryString(1 << 29));
        System.out.println(Integer.toBinaryString(2 << 29));
        System.out.println(Integer.toBinaryString(3 << 29));

        System.out.println(Integer.toBinaryString(~29));


        // 下面的方式都不推荐使用，因为底层阻塞队是LinkedBlockingQueue有界队列，但是默认的大小是Integer.MAX_VALUE，这样会导致OOM
//        final ExecutorService executorService = Executors.newFixedThreadPool(2);
//        final ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
//        final ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        // 通过使用线程ThreadPoolExecutor 方式创建线程
        // 7个参数说明
        // corePoolSize 初始工作线程
        // maximumPoolSize 最大工作线程数
        // keepAliveTime 当前线程数大于corePoolSize的时候，这些线程在是处于空闲状态，并且超过这个时间范围，自动回收该线程
        // unit 空闲时间的单位
        // workQueue 当前线程大于maximumPoolSize的时候，所有的请求到阻塞队列中
        // threadPoolFactory 给每个线程起一个名字
        // rejectedExecutionHandle 当阻塞队列和maxnimunPoolSize 都满的时候会启动拒绝策略，策略有4种
        // Abortpolicy(直接抛异常),
        // CallerRunsPolicy(当阻塞队列和工作线程满的时候，直接把多余的请求给调用的线程（谁调用就给谁处理）)
        // DiscardOldestPolicy 丢失比较老,(靠前的任务)的请求
        // DiscardPolicy 直接丢弃
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(1),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        for (int j = 1; j <= 7; j++) {
            poolExecutor.submit(() -> {
                System.out.println(Thread.currentThread().getName() + " 处理业务");
                try {
                    TimeUnit.SECONDS.sleep(3L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            System.out.println("poolExecutor.getActiveCount = " + poolExecutor.getActiveCount());
        }
        System.out.println("getLargestPoolSize = " + poolExecutor.getLargestPoolSize());
        System.out.println("corepool = " + poolExecutor.getCorePoolSize());
        poolExecutor.shutdown();
    }
}
