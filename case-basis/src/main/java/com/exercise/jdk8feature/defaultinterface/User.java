package com.exercise.jdk8feature.defaultinterface;

/**
 * @author guofeng
 * @version 1.0
 * @desc
 * @createtime 2019/3/10 10:18 AM
 * @see jdk 1.8
 **/
public class User implements Action, Listener {

    @Override
    public void show() {
        System.out.println("User = show");
    }
}
