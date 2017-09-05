package com.exercise.jvm;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2017/9/5 下午4:11
 * @see jdk 1.7
 **/
public class ConstantOutOfMemory {
    //常量池，溢出
    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<>();
        int i = 0;
        while (true) {
            strings.add(String.valueOf(i++));
        }
    }
}
