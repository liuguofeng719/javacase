package com.exercise.jdk8feature.lambda;

import com.google.common.collect.Lists;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author guofeng
 * @version 1.0
 * @desc lambda 表达解决方法传入代码问题
 * jdk 1.8 之前通过传递类实现传递代码片段问题
 * @createtime 2019/3/10 3:56 PM
 * @see jdk 1.8
 **/
public class AppMain {
    public static void main(String[] args) {
        System.out.println("lambda before ==========================");
        List<String> ls = Lists.newArrayList();
        ls.add("java");
        ls.add("c++");
        ls.add("javascript");
        Collections.sort(ls, new FileNameComparator());
        System.out.println("lambda before " + ls);
        System.out.println("lambda after ==========================");
        // 如果方法只有一个参数可以省略到(event) ->{} = event -> {}
        final ActionListener l1 = (ActionEvent event) -> System.out.println(event.toString());
        final ActionListener l2 = event -> System.out.println(event.toString());
        final Runnable runnable = () -> {
            for (int i = 0; i < 5; i++) {
                System.out.println(i);
            }
        };
        //(String f1, String f2) -> { 表达式 }
        //这里数据类型可以省略
        final Comparator<String> comparator = (f1, f2) -> {
            if (f1.length() > f2.length()) {
                return -1;
            }
            if (f1.length() < f2.length()) {
                return 1;
            }
            return 0;
        };
        Collections.sort(ls, comparator);
        System.out.println("lambda after " + ls);
    }
}
