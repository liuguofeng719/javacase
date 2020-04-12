package com.exercise.jdk8feature.lambda;


import javax.swing.*;

/**
 * @author guofeng
 * @version 1.0
 * @desc
 * @createtime 2019/3/10 5:06 PM
 * @see jdk 1.8
 **/
public class TimeGreeter extends Greeter {

    public void greet() {
        Timer timer = new Timer(1000, super::greet);
        timer.start();
    }

    public static void main(String[] args) {
        TimeGreeter timeGreeter = new TimeGreeter();
        timeGreeter.greet();
    }
}
