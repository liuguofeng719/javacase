package com.exercise.algorithm.linked;

/**
 * 单向链表结构
 */
public class SingleLinkList<T> {
    /**
     * 头节点
     */
    private Node<T> head = null;
    /**
     * 当前节点数
     */
    private Node<T> currNode;
    /**
     * 总的节点数
     */
    private int size;
    /**
     * 添加节点
     *
     * @param data
     * @param <T>
     */
    public <T> void addNode(T data) {
        if (head == null) {
            head = new Node(data);
            currNode = head;
        } else {
            currNode.next = new Node(data);
            currNode = currNode.next;
        }
        ++size;
    }
    class Node<T> {
        /**
         * 节点数据
         */
        public T data;
        /**
         * 下一个节点的引用
         */
        public Node next;

        public Node(T data) {
            this.data = data;
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public static void main(String[] args) {
        SingleLinkList<String> singleLinkList = new SingleLinkList<>();
        singleLinkList.addNode("22");
        singleLinkList.addNode("33");
        singleLinkList.addNode("55");
        singleLinkList.addNode("66");
    }
}
