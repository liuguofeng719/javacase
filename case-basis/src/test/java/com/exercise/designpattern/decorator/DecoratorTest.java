package com.exercise.designpattern.decorator;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2017/6/6 下午4:51
 * @see JDK 1.7
 **/
public class DecoratorTest {


    @Test
    public void testAddMilk() {
        Coffee coffee = new Coffee("拿铁", 55L);
        AbstractDecorator abstractDecorator = new MilkDecorator(coffee);
        abstractDecorator.showConffee();
        System.out.println(abstractDecorator.coffeePrice());
    }

    @Test
    public void testAddSugar() {
        Coffee coffee = new Coffee("拿铁", 55L);
        AbstractDecorator abstractDecorator = new SugarDecorator(coffee);
        abstractDecorator.showConffee();
        System.out.println(abstractDecorator.coffeePrice());
    }

    @Test
    public void testAddSugarAndMilk() {
        Coffee coffee = new Coffee("拿铁", 55L);
        AbstractDecorator sugarCoffee = new SugarDecorator(coffee);
        AbstractDecorator milkCoffee = new MilkDecorator(sugarCoffee);
        milkCoffee.showConffee();
        System.out.println(milkCoffee.coffeePrice());
    }

}
