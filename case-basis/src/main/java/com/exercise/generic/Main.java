package com.exercise.generic;

/**
 * @author guofeng
 * @version 1.0
 * @desc
 * @createtime 2019/3/24 8:00 PM
 * @see jdk 1.8
 **/
public class Main {

    public static void main(String[] args) {
        Pair<Manager> pair = new Pair<>();
        Pair<? extends Employee> pair1 = pair;
//        pair1.setFirst(new Manager());
        show(new Pair<Manager>());
        show1(new Pair<Manager>());
    }

    public static void show(Pair<? extends Manager> pair) {

    }

    public static void show1(Pair<?> pair) {

    }
}
