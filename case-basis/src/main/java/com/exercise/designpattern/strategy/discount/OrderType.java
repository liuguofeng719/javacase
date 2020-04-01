package com.exercise.designpattern.strategy.discount;

/**
 * @author guofeng
 * @version 1.0
 * @see jdk 1.8
 **/
public enum OrderType {
    NORMAL("normal","默认折扣"),
    GROUPON("groupon","团购折扣"),
    PROMOTION("promotion","促销折扣")
    ;
    private String type;
    private String desc;
    OrderType(String type,String desc) {
        this.type = type;
        this.desc = desc;
    }
}
