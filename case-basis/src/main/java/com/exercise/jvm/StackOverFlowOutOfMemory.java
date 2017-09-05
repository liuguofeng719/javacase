package com.exercise.jvm;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2017/9/5 下午4:04
 * @see jdk 1.7
 **/
public class StackOverFlowOutOfMemory {

    int i = 0;

    public void count(){
        i++;
        count();
    }

    //栈溢出，stack区是线程私有，每个线程都包含一个栈区域，Stack区包括基本数据类型与对象的引用，其他线程不能访问
    //stack 分为三部分，基本数据类型区，操作指令区，上下文等，
    public static void main(String[] args) {
        try {
            StackOverFlowOutOfMemory stackOverFlow = new StackOverFlowOutOfMemory();
            stackOverFlow.count();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
