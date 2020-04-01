package com.exercise.designpattern.strategy.discount.impl;

import com.exercise.designpattern.strategy.discount.DiscountStrategy;
import com.exercise.designpattern.strategy.discount.Order;

/**
 * @author guofeng
 * @version 1.0
 * @see jdk 1.8
 * 促销
 **/
public class PromotionDiscountStrategyImpl implements DiscountStrategy {
    @Override
    public long calDiscount(Order order) {
        return 0;
    }
}
