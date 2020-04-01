package com.exercise.designpattern.strategy.discount;

/**
 * @author guofeng
 * @version 1.0
 * @see jdk 1.8
 **/
public class OrderService {

    public long discount(Order order) {
        final DiscountStrategy strategy = DiscountStrategyFactory.getType(order.getOrderType());
        return strategy.calDiscount(order);
    }
}
