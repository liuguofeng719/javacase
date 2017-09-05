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
        i ++;
        count();
    }

    //栈溢出，stack区是线程私有
    public static void main(String[] args) {
        try {
            StackOverFlowOutOfMemory stackOverFlow = new StackOverFlowOutOfMemory();
            stackOverFlow.count();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
