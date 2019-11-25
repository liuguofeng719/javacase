package com.exercise.designpattern.decorator;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc 具体的实现者
 * @createtime 2017/6/6 下午4:15
 * @see JDK 1.7
 **/
public class Coffee implements ComponetCoffee {

    private String name;
    private Long price;

    public Coffee(String name, Long price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public void showConffee() {
        System.out.println("的" + this.getName() + "咖啡");
    }

    @Override
    public Long coffeePrice() {
        return this.getPrice();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
