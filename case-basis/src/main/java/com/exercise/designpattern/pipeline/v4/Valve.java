package com.exercise.designpattern.pipeline.v4;

import java.io.IOException;

/**
 * @author guofeng
 * @version 1.0
 * @see jdk 1.8
 **/
public interface Valve {

    Valve getNext();

    void setNext(Valve valve);

    void invoke(RequestInput requestInput, ResponseOutput responseOutput) throws IOException;
}
