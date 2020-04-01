package com.exercise.designpattern.strategy.discount;

/**
 * @author guofeng
 * @version 1.0
 * @see jdk 1.8
 **/
public class Order {
    private long price;
    private OrderType orderType;

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }
}
