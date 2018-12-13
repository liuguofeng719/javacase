package com.exercise.algorithm.stack;

/**
 * @author guofeng
 * @version 1.0
 * @desc 通过单项链表实现stack,
 * 线性链表模拟栈，不需要连续存储空间
 * 1,入栈 O(1)
 * 2,出栈 O(1)
 * @createtime 2018/12/12 4:14 PM
 * @see jdk 1.7
 **/
public class MyLinkedStack<T> implements Stack<T> {
    /**
     * 栈大小
     */
    private int size;
    /**
     * 首节点
     */
    private Node<T> first;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 输入 1->2->3
     * 结果 3->2->1
     *
     * @param data
     */
    @Override
    public void pushStack(T data) {
        Node<T> newNode = new Node<>(data);
        if (first == null) {
            first = newNode;
        } else {
            newNode.next = first;
            first = newNode;
        }
        ++size;
    }

    /**
     * 弹出节点
     *
     * @return
     */
    @Override
    public T popStack() {
        if (size == 0) {
            return null;
        }

        T data = first.data;
        Node next = first.next;

        // gc
        first.next = null;
        first.data = null;
        first = next;
        --size;
        return data;
    }

    /**
     * 打印所有节点
     */
    public void display() {
        Node<T> head = this.first;
        while (head != null) {
            System.out.println(head.data);
            head = head.next;
        }
    }

    private static class Node<T> {

        public T data;

        public Node next;

        public Node(T data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {

        MyLinkedStack<Integer> stack = new MyLinkedStack<>();
        stack.pushStack(1);
        stack.pushStack(2);
        stack.pushStack(3);

        stack.display();

        System.out.println(" size = " + stack.size());
        System.out.println(" isEmpty = " + stack.isEmpty());

        System.out.println(" stack value " + stack.popStack());
        System.out.println(" size = " + stack.size());
        System.out.println(" stack value " + stack.popStack());
        System.out.println(" size = " + stack.size());
        System.out.println(" stack value " + stack.popStack());
        System.out.println(" size = " + stack.size());
    }
}
