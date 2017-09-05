package com.exercise.designpattern.proxy.staticproxy;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2017/6/7 上午10:20
 * @see JDK 1.7
 **/
public class WorkImpl implements Work {

    public void writeBook() {
        System.out.println("在写书");
    }
}
