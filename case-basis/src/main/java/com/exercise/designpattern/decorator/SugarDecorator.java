package com.exercise.designpattern.decorator;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc 加糖装饰
 * @createtime 2017/6/6 下午4:26
 * @see JDK 1.7
 **/
public class SugarDecorator extends AbstractDecorator {

    public SugarDecorator(ComponetCoffee coffee) {
        super(coffee);
    }

    @Override
    public void showConffee() {
        System.out.print("加糖");
        super.showConffee();
    }

    @Override
    public Long coffeePrice() {
        return 12L + super.coffeePrice();
    }
}
