package com.exercise.algorithm.queue;

import java.util.Iterator;

/**
 * @author guofeng
 * @version 1.0
 * @desc 通过数组实现队列叫着有序队列，链表实现队列叫着链式队列
 * 1，队列的特性是 FIFO 先进先出
 * 2，也是一种限制性数据结构
 * 3，入队和出队时间复杂度都是O(1)
 * 4，实现队列需要2个指针标识，1个标识入队列，1个标识出队列
 * @createtime 2018/12/13 3:33 PM
 * @see jdk 1.7
 **/
public class SeqQueue<T> implements Queue<T>, Iterable<T> {

    /**
     * 默认大小4
     */
    private int capacity = 1 << 2;
    /**
     * the queues items
     */
    private Object[] items;
    /**
     * the putIndex
     */
    private int putIndex;
    /**
     * the takeIndex
     */
    private int takeIndex;
    /**
     * 队列数据count
     */
    private int count;

    public SeqQueue() {
        items = new Object[capacity];
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * -----------------
     * putIndex = 3
     * -----------------
     * | 0 | 1 | 2 | 3 |
     * -----------------
     * takeIndex = 2
     * -----------------
     *
     * @param data
     * @return boolean
     */
    @Override
    public boolean enQueue(T data) {
        //入队指针索引等于数组大小
        if (putIndex == items.length) {

            // 判断是否还有空间可以移动数据
            if (takeIndex == 0) {
                throw new RuntimeException("队列已经满！");
            }

            // 转移数据
            for (int i = takeIndex; i < putIndex; i++) {
                items[i - takeIndex] = items[i];
                // gc
                items[i] = null;
            }

            //重置指针索引
            putIndex = putIndex - takeIndex;
            takeIndex = 0;
        }

        items[putIndex++] = data;
        ++count;

        return true;
    }

    /**
     * 输入 1->2->3
     * 输出 1->2->3
     * 出队移除数据
     *
     * @return T
     */
    @Override
    public T deQueue() {
        if (count == 0) {
            return null;
        }
        T item = (T) items[takeIndex];
        items[takeIndex] = null;
        ++takeIndex;
        --count;
        return item;
    }

    /**
     * 出队不移除数据
     *
     * @return T
     */
    @Override
    public T peek() {
        if (count == 0) {
            return null;
        }
        T item = (T) items[takeIndex++];
        return item;
    }

    public static void main(String[] args) {

        SeqQueue<Integer> seqQueue = new SeqQueue<>();

        for (int i = 0; i < 4; i++) {
            seqQueue.enQueue(i);
        }
//        seqQueue.deQueue();
//        seqQueue.deQueue();
//        seqQueue.enQueue(4);

//        boolean isflag = true;
//        while (isflag) {
//            Integer integer = seqQueue.deQueue();
//            System.out.println(integer);
//            isflag = integer == null ? false : true;
//        }

        Iterator<Integer> iterator = seqQueue.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new QueueItrator<>();
    }

    private class QueueItrator<T> implements Iterator<T> {

        @Override
        public boolean hasNext() {
            return takeIndex < putIndex;
        }

        @Override
        public T next() {
            return (T) deQueue();
        }
    }
}
