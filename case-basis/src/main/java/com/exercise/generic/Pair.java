package com.exercise.generic;

/**
 * @author guofeng
 * @version 1.0
 * @desc
 * @createtime 2019/3/24 8:02 PM
 * @see jdk 1.8
 **/
public class Pair<T> {

    private T first;

    public T getFirst() {
        return first;
    }

    public void setFirst(T first) {
        this.first = first;
    }
}
