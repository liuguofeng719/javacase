package com.exercise.lambda;

import java.awt.event.ActionEvent;

/**
 * @author guofeng
 * @version 1.0
 * @desc
 * @createtime 2019/3/10 5:05 PM
 * @see jdk 1.8
 **/
public class Greeter {

    public void greet(ActionEvent event) {
        System.out.println("Hello,World " + event.getActionCommand());
    }
}
