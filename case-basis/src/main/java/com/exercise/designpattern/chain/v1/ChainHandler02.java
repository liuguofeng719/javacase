package com.exercise.designpattern.chain.v1;

/**
 * @author guofeng
 * @version 1.0
 * @see jdk 1.8
 **/
public class ChainHandler02 extends ChainHandlerImpl {

    @Override
    public void handlerProcess(RequestContext context) {
        System.out.println(context);
    }

    @Override
    public String getType() {
        return "c++";
    }
}
