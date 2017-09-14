package com.exercise.generic;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2017/9/13 下午3:04
 * @see jdk 1.7
 **/
public class Variance {
    public static void main(String[] args) {
        //协变
        String[] a1 = { "abc" };
        Object[] a2 = a1;
        a2[0] = new Integer(17);
        String s = a1[0];
    }
}
