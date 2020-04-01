package com.exercise.designpattern.strategy.discount;

/**
 * @author guofeng
 * @version 1.0
 * @see jdk 1.8
 **/
public interface DiscountStrategy {
    long calDiscount(Order order);
}
