package com.exercise.algorithm.linked;

/**
 * @author guofeng
 * @version 1.0
 * @desc
 * @createtime 2018/12/9 11:03 AM
 * @see jdk 1.7
 **/
public interface List<T> {
    /**
     * 判断链表大小
     *
     * @return
     */
    int size();

    /**
     * 判断链表是否为空
     *
     * @return
     */
    boolean isEmpty();

    /**
     * 插入节点
     *
     * @param data
     */
    void insertNode(T data);

    /**
     * 指定索引插入
     *
     * @param data
     * @param index
     */
    void insertIndexNode(T data, int index);

    /**
     * 删除节点
     */
    void deleteNode();

    /**
     * 指定索引删除
     *
     * @param index
     */
    void deleteForIndexNode(int index);

    /**
     * 答应链表的值
     */
    void printLinkedList();

    /**
     * 反转链表的值
     * source 1->2->3->4->5
     * target 5->4->3->2->1
     */
    void reverseLinkedList();
}
