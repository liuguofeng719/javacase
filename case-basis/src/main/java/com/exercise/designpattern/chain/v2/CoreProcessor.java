package com.exercise.designpattern.chain.v2;

import com.exercise.designpattern.chain.v2.model.RequestInput;
import com.exercise.designpattern.chain.v2.model.ResponseOutput;

/**
 * @author guofeng
 * @version 1.0
 * @see jdk 1.8
 **/
public class CoreProcessor implements Processor {

    @Override
    public ResponseOutput processor(RequestInput input) {
        final String content = input.getContent();
        return ResponseOutput.builder().content(content).build();
    }
}
