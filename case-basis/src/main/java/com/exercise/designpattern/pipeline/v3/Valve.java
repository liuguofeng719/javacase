package com.exercise.designpattern.pipeline.v3;

/**
 * @author guofeng
 * @version 1.0
 * @desc
 * @createtime 2019/9/1 12:30 PM
 * @see jdk 1.8
 **/
public interface Valve {
    void invoke(ValveContext valveContext);
}
