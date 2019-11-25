package com.exercise.designpattern.chain.v2;

import com.exercise.designpattern.chain.v2.model.RequestInput;
import com.exercise.designpattern.chain.v2.model.ResponseOutput;

/**
 * @author guofeng
 * @version 1.0
 * @see jdk 1.8
 **/
public class Transformer extends DecoratorProcessor {

    public Transformer(Processor processor) {
        super(processor);
    }

    @Override
    protected RequestInput preProcess(RequestInput request) {
        return RequestInput.builder().content("go").build();
    }

    @Override
    protected ResponseOutput postProcess(ResponseOutput response) {
        return super.postProcess(response);
    }
}
