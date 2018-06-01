package com.exercise.thread.jdkthreadtool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/4/4 下午4:15
 * @see jdk 1.7
 **/
public class CountDownLatchDemo {

    public List<String> list = new ArrayList<>();

    private int[] nums = null;//每个线程的结果集

    public CountDownLatchDemo() {
        list.add("10,20,30,40,50");
        list.add("12,22,32,42");
        list.add("23,33,44,55,66");
        nums = new int[list.size()];
    }

    public void cal(String line, int index) {
        final String[] split = line.split(",");
        int total = 0;
        for (final String s : split) {
            final int num = Integer.parseInt(s);
            total += num;
        }
        nums[index] = total;
        System.out.println(Thread.currentThread().getName() + "执行的计算结果 total = " + total);
    }

    public void calTotal() {
        int total = 0;
        for (final int num : nums) {
            total = total + num;
        }
        System.out.println(Thread.currentThread().getName() + " 合并的结果集为 = " + total);
    }

    public static void main(String[] args) {
        CountDownLatchDemo latchDemo = new CountDownLatchDemo();
        final List<String> list = latchDemo.list;
        final int size = list.size();
        for (int i = 0; i < size; ++i) {
            final int index = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    latchDemo.cal(list.get(index), index);
                }
            }).start();
        }
        //IDEA 在 run 的时候会通过 -javaagent 参数设置自己的监视器（lib/idea_rt.jar）
        //在idea 中通过 run 模式运行 会获取 Monitor Ctrl-Break Thread 激活线程数量为 2 ，通过debug 运行是1
        while (Thread.activeCount() > 1) {

        }
        latchDemo.calTotal();
    }
}
