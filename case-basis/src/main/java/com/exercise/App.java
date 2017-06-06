package com.exercise;

import com.exercise.designpattern.decorator.Coffee;
import com.exercise.designpattern.decorator.ComponetCoffee;
import com.exercise.designpattern.decorator.MilkDecorator;
import com.exercise.designpattern.decorator.SugarDecorator;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        Coffee coffee = new Coffee("德国", 12L);
        ComponetCoffee milkDecorator = new MilkDecorator(coffee);
        ComponetCoffee sugarDecorator = new SugarDecorator(milkDecorator);
        sugarDecorator.showConffee();
        System.out.println(milkDecorator.coffeePrice());

    }
}
