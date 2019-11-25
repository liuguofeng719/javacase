package com.exercise.designpattern.chain.v1;

/**
 * @author guofeng
 * @version 1.0
 * @see jdk 1.8
 **/
public class App {
    public static void main(String[] args) {

        ChainHandler c1 = new ChainHandler01();
        ChainHandler c2 = new ChainHandler02();
        ChainHandler c3 = new ChainHandler03();

        c1.setNextHandler(c2);
        c2.setNextHandler(c3);

        RequestContext requestContext1 = new RequestContext();
        requestContext1.setType("java");
        RequestContext requestContext2 = new RequestContext();
        requestContext2.setType("c++");
        RequestContext requestContext3 = new RequestContext();
        requestContext3.setType("go");
        c1.handler(requestContext3);
        c1.handler(requestContext1);
        c1.handler(requestContext2);
    }
}
