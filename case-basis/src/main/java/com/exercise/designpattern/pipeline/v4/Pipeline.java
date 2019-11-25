package com.exercise.designpattern.pipeline.v4;

/**
 * @author guofeng
 * @version 1.0
 * @see jdk 1.8
 **/
public interface Pipeline {

    Valve getBasic();

    void setBasic(Valve valve);

    void addValve(Valve valve);

    Valve[] getValves();

    void removeValve(Valve valve);

    Valve getFirst();
}
