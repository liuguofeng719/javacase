package com.exercise.designpattern.factory.simplefactory;

/**
 * @author guofeng
 * @version 1.0
 * @desc
 * @createtime 2019/9/19 11:43 PM
 * @see jdk 1.8
 **/
public class SimpleOperationFactory {

    public static Operation createOperation(String operType) {
        switch (operType) {
            case "+":
                return new AddOperation();
            case "*":
                return new MultiplyOperation();
            case "/":
                return new DivideOperation();
            default:
                throw new RuntimeException("暂时不支持操作");
        }
    }
}
