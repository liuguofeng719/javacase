package com.exercise.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc 带返回值的线程
 * @createtime 2018/3/21 上午10:54
 * @see jdk 1.7
 **/
public class Thread03 implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName());
        return 1;
    }

    public static void main(String[] args) {

        FutureTask<Integer> future = new FutureTask<Integer>(new Thread03());

        Thread th = new Thread(future);

        th.start();

        System.out.println(Thread.currentThread().getName());
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
