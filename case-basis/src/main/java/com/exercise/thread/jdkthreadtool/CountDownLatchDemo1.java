package com.exercise.thread.jdkthreadtool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/4/4 下午4:15
 * @see jdk 1.7
 **/
public class CountDownLatchDemo1 {

    public List<String> list = new ArrayList<>();

    private int[] nums = null;//每个线程的结果集

    public CountDownLatchDemo1() {
        list.add("10,20,30,40,50");
        list.add("12,22,32,42");
        list.add("23,33,44,55,66");
        nums = new int[list.size()];
    }

    public void cal(String line, int index,CountDownLatch countDownLatch) {
        final String[] split = line.split(",");
        int total = 0;
        for (final String s : split) {
            final int num = Integer.parseInt(s);
            total += num;
        }
        nums[index] = total;
        System.out.println(Thread.currentThread().getName() + "执行的计算结果 total = " + total);
        countDownLatch.countDown();
    }

    public void calTotal() {
        int total = 0;
        for (final int num : nums) {
            total = total + num;
        }
        System.out.println(Thread.currentThread().getName() + " 合并的结果集为 = " + total);
    }

    public static void main(String[] args) {
        CountDownLatchDemo1 latchDemo = new CountDownLatchDemo1();

        final List<String> list = latchDemo.list;
        final int size = list.size();
        final CountDownLatch latch = new CountDownLatch(size);
        for (int i = 0; i < size; ++i) {
            final int index = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    latchDemo.cal(list.get(index), index,latch);
                }
            }).start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        latchDemo.calTotal();
    }
}
