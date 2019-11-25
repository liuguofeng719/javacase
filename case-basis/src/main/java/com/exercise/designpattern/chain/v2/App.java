package com.exercise.designpattern.chain.v2;

import com.exercise.designpattern.chain.v2.model.RequestInput;

/**
 * @author guofeng
 * @version 1.0
 * @see jdk 1.8
 **/
public class App {

    public static void main(String[] args) {
        final Processor processor = new ValidateParamsProcessor(new Transformer(new CoreProcessor()));
        System.out.println(processor.processor(RequestInput.builder().content("java").build()));
    }
}
