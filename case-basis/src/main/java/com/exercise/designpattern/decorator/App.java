package com.exercise.designpattern.decorator;

/**
 * @author guofeng
 * @version 1.0
 * @desc
 * @createtime 2019/9/22 9:42 PM
 * @see jdk 1.8
 **/
public class App {
    public static void main(String[] args) {
        Coffee coffee = new Coffee("德国", 12L);
        ComponetCoffee milkDecorator = new MilkDecorator(coffee);
        ComponetCoffee sugarDecorator = new SugarDecorator(milkDecorator);
        sugarDecorator.showConffee();
        System.out.println(milkDecorator.coffeePrice());
    }
}
