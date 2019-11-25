package com.exercise.designpattern.chain.v3;

import com.exercise.designpattern.chain.v2.model.RequestInput;
import com.exercise.designpattern.chain.v2.model.ResponseOutput;

/**
 * @author guofeng
 * @version 1.0
 * @see jdk 1.8
 **/
public class App {

    public static void main(String[] args) {
        Filter filter = new ValidateFilter(new AuthenticationFilter(new Target()));
        filter.executor(RequestInput.builder().build(),ResponseOutput.builder().build() );
    }
}
