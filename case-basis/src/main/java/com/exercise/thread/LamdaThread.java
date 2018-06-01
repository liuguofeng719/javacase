package com.exercise.thread;

import java.util.Arrays;
import java.util.List;
import java.util.function.ToIntFunction;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc 死锁、饥饿，活锁
 * @createtime 2018/3/21 上午11:37
 * @see jdk 1.7
 **/
public class LamdaThread {

    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(10, 20, 30, 40);
        LamdaThread lamdaThread = new LamdaThread();
        lamdaThread.add(integers);
    }

    public int add(List<Integer> values) {
        int sum = values.parallelStream().mapToInt(
                new ToIntFunction<Integer>() {
                    @Override
                    public int applyAsInt(Integer value) {
                        System.out.println(value);
                        return value;
                    }
                }).sum();
        return sum;
    }
}
