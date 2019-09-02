package com.exercise.designpattern.pipeline.v2;

/**
 * @author guofeng
 * @version 1.0
 * @desc
 * @createtime 2019/9/1 12:16 PM
 * @see jdk 1.8
 **/
public class ValvePrintlnImpl implements Valve {
    @Override
    public void invoke() {
        System.out.println("======ValvePrintlnImpl======");
    }
}
