package com.exercise.designpattern.decorator;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc 抽象的装饰者
 * @createtime 2017/6/6 下午4:23
 * @see JDK 1.8
 **/
public abstract class AbstractDecorator implements ComponetCoffee {

    private ComponetCoffee coffee;

    public AbstractDecorator(ComponetCoffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public void showConffee() {
        this.coffee.showConffee();
    }

    @Override
    public Long coffeePrice() {
        return coffee.coffeePrice();
    }
}
