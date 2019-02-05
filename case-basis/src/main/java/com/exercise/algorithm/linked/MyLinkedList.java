package com.exercise.algorithm.linked;

import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author guofeng
 * @version 1.0
 * @desc 实现单向链表
 * @createtime 2018/12/9 11:08 AM
 * @see jdk 1.7
 **/
public class MyLinkedList<T> implements List<T> {
    /**
     * 头节点
     */
    private Node<T> first;
    /**
     * 最后一个节点，相当于当前节点或者说成临时节点变量
     */
    private Node<T> last;
    /**
     * 链表总数
     */
    private int size;

    /**
     * @param data
     */
    @Override
    public void insertNode(T data) {
        // 获取最后一个节点,给临时变量l
        final Node<T> l = this.last;
        // 创建新的节点
        final Node<T> newNode = new Node<>(data);
        // 把最新的节点给last节点，相当于当前节点
        this.last = newNode;
        //首轮，最后节点为空
        if (l == null) {
            /**
             * 1，这里first和last拥有相同的节点的引用
             * 2，不管那个变量修改都会使引用发生改变
             */
            first = newNode; // 赋值给首节点
        } else {
            // 设置下一个节点，引用对象都要发生改变
            l.next = newNode;
        }
        // 记录节点数
        ++size;
    }

    /**
     * source 1->2->3->4
     *
     * @param data
     * @param index
     */
    @Override
    public void insertIndexNode(T data, int index) {
        if (index > size + 1 || index < 1) {
            throw new RuntimeException("插入节点index越界");
        }
        //插入只能在头节点以后插入
        int mark = 1;
        //临时节点
        Node<T> temp = first;
        while (temp != null) {
            if (index == mark++) {
                Node<T> newNode = new Node<>(data);
                /**
                 * source 1->2->3
                 * 1，在1->2 中插入0
                 * 2，1->0->2 1的next变成0，0的next变2
                 */
                newNode.next = temp.next;
                temp.next = newNode;
                break;
            }
            temp = temp.next;
        }
    }

    /**
     * source 1->2->3->4->5
     * target 2->3->4->5
     */
    @Override
    public void deleteNode() {
        Node<T> f = this.first;
        //下一个节点变成头节点
        final Node<T> newFirst = f.next;
        if (f == null) {
            last = null;
            return;
        }
        //gc头节点的内存
        f.next = null;
        f.data = null;
        this.first = newFirst.next;
        --size;//计算节点数
    }

    /**
     * source 1->2->3->4->5
     * target 2->3->4->5
     * target 1->2->4->5 删除节点3
     *
     * @param index
     */
    @Override
    public void deleteForIndexNode(int index) {

        if (index > size || index < 0) {
            throw new RuntimeException("删除节点索引出错");
        }
        // 如果等于0，直接删除头节点
        if (index == 0) {
            this.deleteNode();
            return;
        }
        remove(index);
//        remove1(index);
//        removeIndexForZero(index);
        --size;
    }

    /**
     * 打印所有链表
     */
    @Override
    public void printLinkedList() {
        Node<T> first = this.first;
        while (first != null) {
            System.out.println(first.data);
            first = first.next;
        }
    }

    /**
     * 反转链表
     * source 1->2->3->4->5
     * target 5->4->3->2->1
     *
     * head = first
     * tmpNode = null
     * goBack = first
     * goForward = goBack.goForward
     * while( goBack !=null){ 1,2,3,4,5
     *     Node goForward = goBack.goForward; 2，3，4,5
     *     if(goForward == null){
     *         head = goBack;
     *     }
     *     1,2 = null
     *     2,2,1
     *     3,3,2,1
     *     4,4,3,2,1
     *     goBack.goForward = tmpNode;
     *     1,1
     *     2,2,1
     *     3,3,2,1
     *     4,4,3,2,1
     *     tmpNode = goBack;
     *     2，3,4,5
     *     goBack = goForward;
     * }
     */
    @Override
    public void reverseLinkedList() {
        Node<T> head = this.first;
        Node<T> pNode = this.first;
        Node<T> prev = null;
        while (pNode != null) {
            // 获取下一个节点
            Node pNext = pNode.next;// 2 , 3
            // 判断是为最后一个节点
            if (pNext == null) {
                head = pNode;
            }
            /**
             * 1，第1轮为空，下一轮就是上一轮的节点
             */
            pNode.next = prev;// null ,
            /**
             * 中间临时节点
             */
            prev = pNode; // 1
            /**
             * 下一个节点
             */
            pNode = pNext; // 2
        }
        this.first = head;
    }

    /**
     * 1->2->3->4->5
     * 2->3->4->5
     * 1->2->4->5 删除节点3
     * for 循环坐标重0开始
     *
     * @param index
     */
    private void remove(int index) {

        Node<T> tNode = this.first;
        Node<T> prev = null;

        for (int i = 0; i < index; i++) {
            prev = tNode;//上一个节点
            tNode = tNode.next;//中间节点
        }

        //中间节点的下一个节点
        Node next = tNode.next;
        //删除的最后一个节点
        if (next == null) {
            prev.next = null;
        } else {
            // gc
            tNode.next = null;
            prev.next = next;
        }

        tNode.data = null;
    }

    /**
     * 1->2->3->4->5
     * 2->3->4->5
     * 1->2->4->5 删除节点3
     *
     * @param index
     * @desc while 循环索引重1开始，删除节点
     */
    private void remove1(int index) {

        int delMark = 1;
        //创建一个临时节点
        Node<T> newNode = this.first;
        while (newNode != null) {
            /**
             * 1，这里的删除标记索引重1开始的，这里的index的索引是重0开始算的，
             * 所以newNode节点为删除节点的父节点
             */
            if (index == delMark++) {
                // newNode 为上一个节点，newNode.next需要删除节点
                Node node = newNode.next;
                // 下一个节点
                Node next = node.next;

                // 下一个节点为空，表示删除最后一个节点
                if (next == null) {
                    newNode.next = null;
                } else {
                    node.next = null;
                    newNode.next = next;
                }
                // gc
                node.data = null;
                break;
            }

            //获取下一个节点
            newNode = newNode.next;
        }
    }

    /**
     * while 循环，左边重0开始删除
     *
     * @param index
     */
    private void removeIndexForZero(int index) {
        int mark = 0;
        //创建一个临时节点
        Node<T> newNode = this.first;
        //上一个节点
        Node<T> prevNode = null;
        while (newNode != null) {
            if (index == mark++) {
                Node delNode = prevNode.next;
                // 下一个节点
                Node nextNode = delNode.next;
                // 下一个节点为空，表示删除最后一个节点
                if (nextNode == null) {
                    prevNode.next = null;
                } else {
                    delNode.next = null;
                    prevNode.next = nextNode;
                }
                // gc
                delNode.data = null;
                break;
            }
            prevNode = newNode;
            //获取下一个节点
            newNode = newNode.next;
        }
    }

    class Node<T> {

        public T data;

        public Node next;

        public Node(T data) {
            this.data = data;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public static void main(String[] args) {
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();
        myLinkedList.insertNode(1);
        myLinkedList.insertNode(2);
        myLinkedList.insertNode(3);
        myLinkedList.insertNode(4);
        myLinkedList.insertNode(5);
//        myLinkedList.insertIndexNode(6, 1);
//        myLinkedList.deleteNode();
//        myLinkedList.deleteForIndexNode(3);
        myLinkedList.reverseLinkedList();
        myLinkedList.printLinkedList();

        NodeX<Integer> first = null;
        NodeX<Integer> last = null;

        NodeX<Integer> nodeX = new NodeX<>(11);
        first = nodeX;
        last = nodeX;

        NodeX<Integer> nodeX1 = new NodeX<>(22);
        NodeX<Integer> l = last;//11
        last.setNext(nodeX1);//22
        l.setNext(nodeX1);//11->22

        System.out.println(first);
        System.out.println(last);


        System.out.println(tryFinally());;


//        testLinkedList();
//        TestLinked();
    }

    private static int tryFinally() {
        ReentrantLock lock = new ReentrantLock();
        try{
            lock.lock();
            return 1;
        }finally{
            lock.unlock();
        }
    }

    private static void testLinkedList() {
        LinkedList<Integer> ll = new LinkedList<>();
        ll.add(1);
        ll.add(2);
        ll.add(3);
        ll.add(4);
        Integer integer = ll.remove(0);
        System.out.println(integer);
        System.out.println(ll);
    }

    /**
     * 如果不用临时变量，会导致引用被覆盖
     */
    private static void TestLinked() {
        NodeX<Integer> node = new NodeX<>(1);
        NodeX<Integer> node1 = new NodeX<>(2);
        NodeX<Integer> node2 = new NodeX<>(3);
        NodeX<Integer> node3 = new NodeX<>(4);
        node.setNext(node1);
        node1.setNext(node2);
        node2.setNext(node3);
        while (node.getNext() != null) {
            node = node.getNext();
        }
        NodeX<Integer> node4 = new NodeX<>(5);
        node.setNext(node4);
        System.out.println(node);

    }
}

class NodeX<T> {

    private T data;

    private NodeX next;

    public NodeX(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public NodeX getNext() {
        return next;
    }

    public void setNext(NodeX next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "NodeX{" +
                "data=" + data +
                ", goForward=" + next +
                '}';
    }
}