package com.exercise.algorithm.stack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author guofeng
 * @version 1.0
 * @desc 通过数组实现栈, 栈是限制数据结构
 * 特性，先进后出，后进先出
 * 1，入栈 O(1),如果实现数组扩容,入栈在扩容的时间复杂度是O(n)
 * 2，出栈 O(1)
 * @createtime 2018/12/12 3:16 PM
 * @see jdk 1.7
 **/
public class MyStack<T> implements Stack<T> {
    /**
     * 数据存储
     */
    private Object[] data;
    /**
     * 默认stack大小
     */
    private int capacity = 4;
    /**
     * 容器数据大小
     */
    private int size;
    /**
     * 出栈和入栈的指针
     */
    private int index;

    public MyStack() {
        this.data = new Object[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void pushStack(T data) {
        if (size == this.data.length) {
            expandCapacity();
        }
        this.data[index++] = data;
        ++size;
    }

    /**
     * 扩容
     * 使用3种方式copy 数组
     */
    public void expandCapacity() {
        //分配以前数组的2倍大小
//        方案1
//        Object[] tmpObj = new Object[size * 2];
//        for (int i = 0; i < size; i++) {
//            tmpObj[i] = data[i];
//        }
//        方案2
//        System.arraycopy(this.data, 0, tmpObj, 0, data.length);
        //方案3
        this.data = Arrays.copyOf(this.data, size * 2);
//        this.data = tmpObj;
    }

    @Override
    public T popStack() {
        if (size == 0) {
            return null;
        }
        /**
         * 取出头部的值
         */
        T value = (T) this.data[index - 1];
        //删除值
        this.data[index - 1] = null;
        --size;
        --index;
        return value;
    }

    /**
     * 校验(){}[] 是否成对，支持嵌套
     * ({[]}) true
     * ({{}}) false
     * 第一个入栈的括号为右括号 false
     * exp = "{)(}"
     *
     * 1，把等于右括号的全部加入栈，如果出现不是右括号的，
     * 2，判断栈是否为空不为空，取出栈顶比较是否相同，如果不同，接着循环
     * 如果栈里面的为空，说明成对，不为空，说明不成对
     *
     * @param exp
     * @return
     */
    public static boolean isValid(String exp) {

        MyLinkedStack<Character> stack = new MyLinkedStack<>();
        // 存储括号的对应关系
        Map<Character, Character> map = new HashMap<>();
        map.put('}', '{');
        map.put(')', '(');
        map.put(']', '[');
        // {{}},[],{[]}
        for (char c : exp.toCharArray()) {
            if (map.containsValue(c)) {//如果全部是左括号加入栈中
                stack.pushStack(c);
                /**
                 * 1,如果map.get(c)和栈中的相同说明成对
                 * 2,不等或者栈不为空，都说明括号不成对
                 */
            } else if (stack.isEmpty() || map.get(c) != stack.popStack()) {
                return false;
            }
        }
        return stack.isEmpty();
    }

    /**
     * @desc {}
     * 如果等于左边括号，把右边括号加入栈中
     * 如果输入的右边括号和栈中的一样说明成对，不等说明不成对
     * @param s
     * @return
     */
    public static boolean isValid1(String s) {
        java.util.Stack<Character> stack = new java.util.Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(')
                stack.push(')');
            else if (c == '{')
                stack.push('}');
            else if (c == '[')
                stack.push(']');
            else if (stack.isEmpty() || stack.pop() != c)
                return false;
        }
        return stack.isEmpty();
    }


    public static void main(String[] args) {

        System.out.println(isValid("{}[]"));

        MyStack<Integer> stack = new MyStack<>();
        stack.pushStack(10);
        stack.pushStack(12);
        stack.pushStack(13);
        stack.pushStack(14);

        System.out.println("isEmpty = " + stack.isEmpty());
        System.out.println("size = " + stack.size());

        System.out.println("prop value =  " + stack.popStack());

        System.out.println("size = " + stack.size());

        stack.pushStack(20);

        System.out.println("prop value =  " + stack.popStack());
        System.out.println("size = " + stack.size());
    }
}
