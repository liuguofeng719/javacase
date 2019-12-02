package com.exercise.lsp.interfaces.v1;

import com.exercise.lsp.interfaces.EggLayable;
import com.exercise.lsp.interfaces.Flyable;
import com.exercise.lsp.interfaces.Tweetable;

/**
 * @author guofeng
 * @version 1.0
 * @see jdk 1.8
 **/
public class Penguin implements Flyable,Tweetable,EggLayable {

    @Override
    public void layEgg() {

    }

    @Override
    public void fly() {

    }

    @Override
    public void tweet() {

    }
}
