package com.exercise.algorithm.queue;

/**
 * @author guofeng
 * @version 1.0
 * @desc 循环队列
 * 1，出队和入队的时间复杂度都是O(1)
 * 2，扩展数据不需要移动数据
 * @createtime 2018/12/13 10:28 PM
 * @see jdk 1.7
 **/
public class CricleQueue<T> implements Queue<T> {

    private int capacity = 1 << 2;

    private Object[] items;
    private int putIndex;
    private int takeIndex;
    private int count;

    public CricleQueue() {
        this.items = new Object[capacity];
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
     * (putIndex+1) % 队列总长度 = 存储的位置
     * 判断队列满的公式
     * (putIndex+1) % 队列总长度 = takeIndex
     * 判断队列是否为空
     * putIndex == takeIndex
     * 获取出队数据的位置
     * (takeIndex+1) % 队列总长度 = 获取队列数据的位置
     * ---------------------
     * putIndex = 3
     * ---------------------
     * | 1 | 2 | 3 | 4 | 5 |
     * ---------------------
     *   0   1   2   3   4
     * ---------------------
     * takeIndex = 2
     * ---------------------
     *
     * @param data
     * @return
     */
    @Override
    public boolean enQueue(T data) {

        final int position = (putIndex + 1) % capacity;
        if (position == takeIndex) {
            return false;
        }

        items[putIndex] = data;

        putIndex = position;

        ++count;

        return true;
    }

    @Override
    public T deQueue() {

        if (takeIndex == capacity) {
            return null;
        }

        final T item = (T) items[takeIndex];
        // gc
        items[takeIndex] = null;
        takeIndex = (takeIndex + 1) % capacity;
        return item;
    }

    @Override
    public T peek() {
        return null;
    }

    public static void main(String[] args) {
        CricleQueue<Integer> cricleQueue = new CricleQueue<>();
        cricleQueue.enQueue(1);
        cricleQueue.enQueue(2);
        cricleQueue.enQueue(3);
        cricleQueue.enQueue(4);

        System.out.println(cricleQueue.deQueue());
        System.out.println(cricleQueue.deQueue());

        System.out.println(cricleQueue.enQueue(5));
    }
}
