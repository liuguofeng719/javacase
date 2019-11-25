package com.exercise.designpattern.factory.simplefactory;

import lombok.extern.slf4j.Slf4j;

/**
 * @author guofeng
 * @version 1.0
 * @desc
 * @createtime 2019/9/20 7:23 AM
 * @see jdk 1.8
 **/
@Slf4j
public class App {
    public static void main(String[] args) {
        final Operation operation = SimpleOperationFactory.createOperation("*");
        final long multiply = operation.operation(10, 20);
        log.info("金额 {}", multiply);
    }
}
