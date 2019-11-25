package com.exercise.designpattern.factory.methodfactory.exercise01;

/**
 * @author guofeng
 * @version 1.0
 * @desc
 * @createtime 2019/9/25 10:59 PM
 * @see jdk 1.8
 **/
public class ConcretorIphone extends AbstractCreator {

    @Override
    protected <T extends Iphone> T creator(Class<T> clzz) {
        try {
            return (T) Class.forName(clzz.getName()).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
