package com.exercise.designpattern.decorator;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc 加奶装饰
 * @createtime 2017/6/6 下午4:29
 * @see JDK 1.7
 **/
public class MilkDecorator extends AbstractDecorator {

    public MilkDecorator(ComponetCoffee coffee) {
        super(coffee);
    }

    @Override
    public void showConffee() {
        System.out.print("加奶");
        super.showConffee();
    }

    @Override
    public Long coffeePrice() {
        return 20L + super.coffeePrice();
    }
}
