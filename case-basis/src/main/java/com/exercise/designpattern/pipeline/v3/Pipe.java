package com.exercise.designpattern.pipeline.v3;

/**
 * @author guofeng
 * @version 1.0
 * @desc
 * @createtime 2019/9/1 12:33 PM
 * @see jdk 1.8
 **/
public interface Pipe {
    void addValve(Valve valve);

    void removeValve(Valve valve);

    void setBasicValve(Valve valve);

    void invoke();
}
