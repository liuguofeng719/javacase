package com.exercise.algorithm.queue;

/**
 * @author guofeng
 * @version 1.0
 * @desc
 * @createtime 2018/12/13 3:38 PM
 * @see jdk 1.7
 **/
public interface Queue<T> {
    /**
     * this queue size total
     * @return
     */
    int size();

    /**
     * is queue empty
     * @return
     */
    boolean isEmpty();

    /**
     * 入队
     * @return
     */
    boolean enQueue(T data);

    /**
     * 出队，删除出队的元素
     * @return
     */
    T deQueue();

    /**
     * 出队，不删除出队元素
     * @return
     */
    T peek();

}
