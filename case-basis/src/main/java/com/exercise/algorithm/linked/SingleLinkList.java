package com.exercise.algorithm.linked;

/**
 * 单向链表结构
 */
public class SingleLinkList<T> {
    /**
     * 头节点
     */
    private Node<T> head;
    /**
     * 尾节点
     */
    private Node<T> tail;
    /**
     * 总的节点数
     */
    private int size;

    /**
     * 在头部添加节点
     *
     * @param data
     */
    public void addHeadNode(T data) {
        // 检查头节点是否为空
        if (this.head == null) {
            // 初始化头结点
            this.head = new Node(data);
        } else {
            // 把头节点临时存储起来
            Node tmpNode = this.head;
            // 把当前节点变为头结点
            this.head = new Node(data);
            // 把原来的头结点设置为新头节点的next节点
            this.head.next = tmpNode;
        }
    }

    /**
     * 在末尾添加节点
     *
     * @param data
     */
    public void addNode(T data) {
        // 检查头结点是否为空
        if (this.head == null) {
            // 初始化头节点
            this.head = new Node(data);
            // 头结点赋值给当前节点
            this.tail = this.head;
        } else {
            // 这里的当前节点一直持有Head节点的引用
            // 把节点赋值给当前节点的next、也会改变head节点的next节点
            this.tail.next = new Node(data);
            // 把当前节点重置为head的next节点、这里的next节点就是尾结点tailNode
            this.tail = this.tail.next;
        }
        // 链表元素+1
        ++size;
    }

    /**
     * 在指定位置插入节点
     * @param index
     * @param data
     */
    public void insertIndexNode(int index,T data) {

    }

    /**
     * 弹出头节点
     * @param
     */
    public Node popHead(){
        if (this.head != null) {
            Node tmp = this.head;
            this.head = this.head.next;
            --size;
            tmp.next = null;
            return tmp;
        }
        return null;
    }

    /**
     * 从尾部移除节点
     */
    public void removeTailHead() {
        Node nextNode = this.head;
        // 如果头节点和尾结点相等，说明是最后一个节点了
        if (nextNode == this.tail) {
            this.head = null;
            this.tail = null;
            size = 0;
            return;
        }

        //22 -> 33 -> 55 -> 66
        Node predNode = null;
        while (nextNode.next != null) {
            // 上一个节点
            predNode = nextNode;
            // 中间节点
            nextNode = nextNode.next;
        }

        // 中间节点的下一个节点
        Node next = nextNode.next;
        if (next == null) {
            predNode.next = null;
            this.tail = predNode;
        }
    }

    /**
     * 反转节点
     * 22 -> 33 -> 55 -> 66
     * 66 -> 55 -> 33 -> 22
     */
    public void reverseNode(){
        Node<T> tNode = this.head;
        Node node = null;
        while (tNode != null) {
            node = tNode;
            tNode = tNode.next;
        }
    }

    static class Node<T> {
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


//        System.out.println(singleLinkList.popHead());
//        System.out.println(singleLinkList.popHead());

        singleLinkList.removeTailHead();
        singleLinkList.removeTailHead();

//        LinkedList<String> linkedList = new LinkedList<>();
//        linkedList.add("12");
//        linkedList.add("13");
//        linkedList.add("14");
//        linkedList.addFirst("450");
//        linkedList.addFirst("451");
//        linkedList.addFirst("452");
//        linkedList.addFirst("453");
//        linkedList.addLast("66");
//
//        System.out.println(linkedList);


//        singleLinkList.addHeadNode("77");
//        singleLinkList.addHeadNode("88");
//
//        System.out.println(singleLinkList);

        Node head = new Node(11);
        Node current = head;
        current.next = new Node(22);
        current = current.next;
        System.out.println(head);
    }
}
