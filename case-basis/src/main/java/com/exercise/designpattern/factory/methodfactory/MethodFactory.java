package com.exercise.designpattern.factory.methodfactory;

/**
 * @author guofeng
 * @version 1.0
 * @desc
 * @createtime 2019/9/20 7:30 AM
 * @see jdk 1.8
 **/
public class MethodFactory {

    /**
     * @return
     */
    public static Factory getFactory() {
        return new FactoryImpl();
    }
}
