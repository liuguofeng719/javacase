package com.exercise.designpattern.proxy.staticproxy;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2017/6/7 上午10:24
 * @see JDK 1.7
 **/
public class AppStaticProxy {

    public static void main(String[] args) {

        Work realWrok = new WorkImpl();

        Work workProxy = new ProxyWork(realWrok);

        workProxy.writeBook();
    }
}
