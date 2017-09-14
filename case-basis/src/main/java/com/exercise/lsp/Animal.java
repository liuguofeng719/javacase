package com.exercise.lsp;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2017/9/13 下午3:27
 * @see jdk 1.7
 **/
public abstract class Animal {

    public int function1(int a, int b) {
        return a + b;
    }

    public void params(HashMap map) {
        System.out.println("Animal= params");
    }

    public abstract Number getNum();
}
