package com.exercise.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2017/9/12 下午6:05
 * @see jdk 1.7
 **/
public class AppMain {

    public static void main(String[] args) {
        List<Student> list = new ArrayList<>();
        Student student1 = new Student(50, 90, "战三");
        Student student2 = new Student(30, 120, "张三");
        Student student3 = new Student(40, 100, "李四");
        Student student4 = new Student(60, 130, "王五");
        list.add(student1);
        list.add(student2);
        list.add(student3);
        list.add(student4);
        Collections.sort(list);

        for (final Student student : list) {
            System.out.println(student.toString());
        }

        Collections.sort(list,new Student.OrderByName());

        for (final Student student : list) {
            System.out.println(student.toString());
        }
    }
}
