package com.exercise.designpattern.factory.methodfactory.exercise01;

/**
 * @author guofeng
 * @version 1.0
 * @desc
 * @createtime 2019/9/25 11:02 PM
 * @see jdk 1.8
 **/
public class AppClient {
    public static void main(String[] args) {
        AbstractCreator creator = new ConcretorIphone();
        final Iphone phone = creator.creator(SmallPhone.class);
        phone.setName("smallPhone");
        phone.setDescription("this is small phone");
        phone.setDescription("small");
        phone.call();
        phone.accept();

        final Iphone largerPhone = creator.creator(LargerPhone.class);
        largerPhone.setName("larger phone");
        largerPhone.setDescription("this is larger phone,is very good");
        largerPhone.setDimension("larger");
        largerPhone.call();
        largerPhone.accept();
    }
}
