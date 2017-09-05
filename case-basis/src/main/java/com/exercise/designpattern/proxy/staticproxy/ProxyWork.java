package com.exercise.designpattern.proxy.staticproxy;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2017/6/7 上午10:21
 * @see JDK 1.7
 **/
public class ProxyWork implements Work {

    private Work work;

    public ProxyWork(Work work) {
        this.work = work;
    }

    public void writeBook() {
        beforeReadBook();
        work.writeBook();
        afterCoffee();
    }

    public void beforeReadBook() {
        System.out.println("先读书");
    }

    public void afterCoffee() {
        System.out.println("喝咖啡");
    }
}
