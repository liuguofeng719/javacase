package com.exercise.designpattern.strategy.discount.impl;

import com.exercise.designpattern.strategy.discount.DiscountStrategy;
import com.exercise.designpattern.strategy.discount.Order;

/**
 * @author guofeng
 * @version 1.0
 * @see jdk 1.8
 * 正常折扣
 **/
public class NormalDiscountStrategyImpl implements DiscountStrategy {
    @Override
    public long calDiscount(Order order) {
        return 0;
    }
}
