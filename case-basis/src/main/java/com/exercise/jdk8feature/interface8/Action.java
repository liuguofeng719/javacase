package com.exercise.jdk8feature.interface8;

/**
 * @author guofeng
 * @version 1.0
 * @desc
 * @createtime 2019/3/10 10:17 AM
 * @see jdk 1.8
 **/
public interface Action {
    default void show() {
        System.out.println("default show()");
    }
}
