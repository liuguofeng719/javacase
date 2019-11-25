package com.exercise.designpattern.factory.simplefactory;

/**
 * @author guofeng
 * @version 1.0
 * @desc
 * @createtime 2019/9/19 11:41 PM
 * @see jdk 1.8
 **/
public class MultiplyOperation implements Operation {

    @Override
    public long operation(long paramA, long paramB) {
        return paramA * paramB;
    }

}
