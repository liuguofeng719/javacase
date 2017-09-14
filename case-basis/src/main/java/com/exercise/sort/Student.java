package com.exercise.sort;

import java.util.Comparator;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2017/9/12 下午5:59
 * @see jdk 1.7
 **/
public class Student implements Comparable<Student> {

    private int age;

    private int score;

    private String name;

    public Student(int age, int score, String name) {
        this.age = age;
        this.score = score;
        this.name = name;
    }

    @Override
    public int compareTo(Student o) {
        return this.age > o.age ? 1 : (this.age < o.age ? -1 : 0);//升序排列
//        return this.age > o.age ? -1 : (this.age < o.age ? 1 : 0);//降序排列
    }


    public static class OrderByName implements Comparator<Student>{
        @Override
        public int compare(Student o1, Student o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", score=" + score +
                ", name='" + name + '\'' +
                '}';
    }
}
