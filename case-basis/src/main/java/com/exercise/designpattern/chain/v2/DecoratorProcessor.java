package com.exercise.designpattern.chain.v2;

import com.exercise.designpattern.chain.v2.model.RequestInput;
import com.exercise.designpattern.chain.v2.model.ResponseOutput;

/**
 * @author guofeng
 * @version 1.0
 * @see jdk 1.8
 **/
public class DecoratorProcessor implements Processor {

    private final Processor processor;

    public DecoratorProcessor(Processor processor) {
        this.processor = processor;
    }

    @Override
    public ResponseOutput processor(RequestInput input) {
        final RequestInput requestInput = this.preProcess(input);
        final ResponseOutput response = this.processor.processor(requestInput);
        final ResponseOutput responseOutput = this.postProcess(response);
        return responseOutput;
    }

    protected RequestInput preProcess(RequestInput request) {
        return request;
    }

    protected ResponseOutput postProcess(ResponseOutput response) {
        return response;
    }
}
