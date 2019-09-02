package com.exercise.designpattern.pipeline.v2;

/**
 * @author guofeng
 * @version 1.0
 * @desc
 * @createtime 2019/9/1 11:57 AM
 * @see jdk 1.8
 **/
public interface PipeLine {

    void addValve(Valve valve);

    void setBasicValve(Valve valve);

    void removeValve(Valve valve);

    void invoke();
}
