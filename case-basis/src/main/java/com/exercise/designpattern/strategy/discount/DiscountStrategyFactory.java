package com.exercise.designpattern.strategy.discount;

import com.exercise.designpattern.strategy.discount.impl.NormalDiscountStrategyImpl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author guofeng
 * @version 1.0
 * @see jdk 1.8
 **/
public class DiscountStrategyFactory {

    private static Map<OrderType, DiscountStrategy> discountStrategyMap = new ConcurrentHashMap<>();

    static {
        discountStrategyMap.put(OrderType.NORMAL, new NormalDiscountStrategyImpl());
        discountStrategyMap.put(OrderType.GROUPON, new NormalDiscountStrategyImpl());
        discountStrategyMap.put(OrderType.PROMOTION, new NormalDiscountStrategyImpl());
    }

    public static DiscountStrategy getType(OrderType discountType) {
        return discountStrategyMap.get(discountType);
    }
}
