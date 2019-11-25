package com.exercise.designpattern.chain.v2;

import com.exercise.designpattern.chain.v2.model.RequestInput;
import com.exercise.designpattern.chain.v2.model.ResponseOutput;

/**
 * @author guofeng
 * @version 1.0
 * @see jdk 1.8
 **/
public class ValidateParamsProcessor extends DecoratorProcessor {

    public ValidateParamsProcessor(Processor processor) {
        super(processor);
    }

    @Override
    protected RequestInput preProcess(RequestInput request) {
        if (request.getContent().equals("go")) {
            throw new RuntimeException("参数错误");
        }
        return request;
    }

    @Override
    protected ResponseOutput postProcess(ResponseOutput response) {
        final String content = response.getContent();
        return ResponseOutput.builder().content(content + " end ").build();
    }
}
