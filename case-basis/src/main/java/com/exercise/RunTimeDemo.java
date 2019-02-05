package com.exercise;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author guofeng
 * @version 1.0
 * @desc
 * @createtime 2018/12/25 9:53 AM
 * @see jdk 1.7
 **/
public class RunTimeDemo {

    private CountDownLatch count = new CountDownLatch(5);

    CopyOnWriteArrayList<Integer> copy = new CopyOnWriteArrayList<>();

    ThreadLocal<Integer> local = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    ExecutorService executor = Executors.newFixedThreadPool(5);

    AtomicInteger atomic = new AtomicInteger();

    private void testCountDownLatch() {
        for (int i = 0; i < 5; i++) {
            /**
             * 会立即抛出异常
             */
            executor.execute(() -> {
                try {
                    for (int j = 0; j < 3; j++) {
                        local.set(local.get() + 1);
                        System.out.println(local.get() + "====" + Thread.currentThread().getName());
                    }
                    copy.add(local.get());
                    TimeUnit.MICROSECONDS.sleep(1000);
                    final int get = atomic.incrementAndGet();
                    if (get % 2 == 0) {
                        int i1 = 1 / 0;
                    }
                    count.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            /**
             * 线程里面出现异常，会捕获异常，在获取的时候会抛异常
             */
//            final Future<?> submit = executor.submit(() -> {
//                try {
//                    for (int j = 0; j < 3; j++) {
//                        local.set(local.get() + 1);
//                        System.out.println(local.get() + "====" + Thread.currentThread().getName());
//                    }
//                    copy.add(local.get());
//                    TimeUnit.MICROSECONDS.sleep(1000);
//                    final int get = atomic.incrementAndGet();
//                    if (get % 2 == 0) {
//                        int i1 = 1 / 0;
//                    }
//                    count.countDown();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            });
//            try {
//                submit.get();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }
//            new Thread(() -> {
//                try {
//                    for (int j = 0; j < 3; j++) {
//                        local.set(local.get() + 1);
//                        System.out.println(local.get() + "====" + Thread.currentThread().getName());
//                    }
//                    copy.add(local.get());
//                    TimeUnit.MICROSECONDS.sleep(1000);
//                    final int get = atomic.incrementAndGet();
//                    if (get % 2 == 0) {
//                        int i1 = 1 / 0;
//                    }
//                    count.countDown();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }).start();
        }
        try {
            count.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int total = 0;
        for (int i = 0; i < copy.size(); i++) {
            total += copy.get(i).intValue();
        }

        System.out.println("每个线程的计算的总和 = " + total);

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("正在释放资源");
            }
        }, "ShutdownHook"));


    }

    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(new CrashHandler());
        RunTimeDemo runTimeDemo = new RunTimeDemo();
        runTimeDemo.testCountDownLatch();
    }
}

class CrashHandler implements Thread.UncaughtExceptionHandler {


    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Throwable cause = e.getCause();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream s = new PrintStream(outputStream);
        e.printStackTrace(s);
        final byte[] bytes = outputStream.toByteArray();
        while (cause != null) {
            e.printStackTrace(s);
            cause = e.getCause();
        }

        System.out.println(t.getName() + " = " + new String(bytes));
    }
}
