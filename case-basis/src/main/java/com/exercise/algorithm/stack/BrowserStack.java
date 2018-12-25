package com.exercise.algorithm.stack;

import java.util.Stack;

/**
 * @author guofeng
 * @version 1.0
 * @desc 通过栈实现浏览器的前进后退
 * 1，通过2个stack，x,y栈 实现浏览器的前进后退
 * 2，第一次浏览器页面的时候加入x栈
 * 3，点击后退，把x栈的页面，加入y栈中
 * 4，如果在当前页面打开了新的页面，需要清空y栈。
 * @createtime 2018/12/13 2:54 PM
 * @see jdk 1.7
 **/
public class BrowserStack {

    //后退的页面
    private Stack<String> prev = new Stack<>();

    //前进页面
    private Stack<String> next = new Stack<>();


    public void push(String page) {
        /**
         * 检查前进栈中有数据，如果在添加数据，
         * 证明在当前页面打开了新的页面，需要清除，前进页面
         */
        if (!next.isEmpty()) {
            next.clear();
        }
        prev.push(page);
    }

    public String goBack() {
        //如果为空不能后退
        if (prev.empty()) {
            return null;
        }
        String pop = prev.pop();
        next.push(pop);
        return pop;
    }

    public String goForward() {
        //如果为空不能前进
        if (next.empty()) {
            return null;
        }
        String pop = next.pop();
        prev.push(pop);
        return pop;
    }

    public static void main(String[] args) {
        //初始化页面层次为a->b->c
        BrowserStack stack = new BrowserStack();
        stack.push("http://www.baidu.com");
        stack.push("http://www.baidu.com/b");
        stack.push("http://www.baidu.com/b/c");

        System.out.println("后退===" + stack.goBack());
        System.out.println("后退===" + stack.goBack());
        System.out.println("前进size == " + stack.next.size());
        System.out.println("后退size == " + stack.prev.size());
        System.out.println("前进===" + stack.goForward());
        System.out.println("前进size == " + stack.next.size());
        System.out.println("后退size == " + stack.prev.size());
        stack.push("http://www.it.com");
        System.out.println("前进size == " + stack.next.size());
        System.out.println("后退size == " + stack.prev.size());
        System.out.println("前进===" + stack.goForward());
    }
}
