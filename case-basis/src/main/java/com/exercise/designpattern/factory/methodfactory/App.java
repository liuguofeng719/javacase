package com.exercise.designpattern.factory.methodfactory;

import com.exercise.designpattern.factory.simplefactory.Operation;
import lombok.extern.slf4j.Slf4j;

/**
 * @author guofeng
 * @version 1.0
 * @desc
 * @createtime 2019/9/20 7:31 AM
 * @see jdk 1.8
 **/
@Slf4j
public class App {

    public static void main(String[] args) {
        final Factory factory = MethodFactory.getFactory();
        final Operation addOper = factory.createAddOper();
        final long operation = addOper.operation(10, 20);
        log.info("total = {} ", operation);
    }
}
