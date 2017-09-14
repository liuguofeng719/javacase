package com.exercise.lsp;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2017/9/13 下午3:27
 * @see jdk 1.7
 **/
public class Cat extends Animal {
    public void sayHello() {
        System.out.println("Cat");
    }

    public int function1(int a, int b) {
        return a - b;
    }

    public void params(Map map) {
        System.out.println("Cat==params");
    }

    public Integer getNum(){
        return new Integer(1);
    }
}
