package com.exercise.designpattern.chain.v1;

/**
 * @author guofeng
 * @version 1.0
 * @see jdk 1.8
 **/
public interface ChainHandler {

    void handler(RequestContext contexts);

    void setNextHandler(ChainHandler chainHandler);
}
