package com.exercise.thread.jdkthreadtool;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/4/8 下午4:55
 * @see jdk 1.7
 **/
public class ExchangerDemo {

    public void a(Exchanger<String> exchanger) {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String a = "12345";
        try {
            exchanger.exchange(a);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void b(Exchanger<String> exchanger) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String b = "12345";
        try {
            final String exchange = exchanger.exchange("");
            System.out.println(exchange.equals(b));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        ExchangerDemo exchangerDemo = new ExchangerDemo();

        final ExecutorService executorService = Executors.newFixedThreadPool(2);

        Exchanger<String> exchanger = new Exchanger<>();

        executorService.submit(new Runnable() {
            @Override
            public void run() {
                exchangerDemo.a(exchanger);
            }
        });

        executorService.submit(new Runnable() {
            @Override
            public void run() {
                exchangerDemo.b(exchanger);
            }
        });
        executorService.shutdown();
    }
}
