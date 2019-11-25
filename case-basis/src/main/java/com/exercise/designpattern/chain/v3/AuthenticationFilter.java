package com.exercise.designpattern.chain.v3;

import com.exercise.designpattern.chain.v2.model.RequestInput;
import com.exercise.designpattern.chain.v2.model.ResponseOutput;

/**
 * @author guofeng
 * @version 1.0
 * @see jdk 1.8
 **/
public class AuthenticationFilter implements Filter {

    private final Filter filter;

    public AuthenticationFilter(Filter filter) {
        this.filter = filter;
    }

    @Override
    public void executor(RequestInput req, ResponseOutput resp) {
        System.out.println("AuthenticationFilter");
        filter.executor(req, resp);
    }
}
