package com.exercise.designpattern.factory.methodfactory.exercise01;


/**
 * @author guofeng
 * @version 1.0
 * @desc
 * @createtime 2019/9/25 10:56 PM
 * @see jdk 1.8
 **/
public abstract class AbstractCreator {
    protected abstract <T extends Iphone> T creator(Class<T> clzz);
}
