package com.exercise.algorithm.stack;

/**
 * @author guofeng
 * @version 1.0
 * @desc
 * @createtime 2018/12/12 3:16 PM
 * @see jdk 1.7
 **/
public interface Stack<T> {

    int size();

    boolean isEmpty();

    void pushStack(T data);

    T popStack();
}
