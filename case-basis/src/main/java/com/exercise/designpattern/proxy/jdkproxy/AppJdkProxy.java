package com.exercise.designpattern.proxy.jdkproxy;

import com.exercise.designpattern.proxy.staticproxy.Work;
import com.exercise.designpattern.proxy.staticproxy.WorkImpl;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2017/6/7 上午10:35
 * @see JDK 1.7
 **/
public class AppJdkProxy {

    public static void main(String[] args) {
        JdkProxyWork proxyWork = new JdkProxyWork(new WorkImpl());
        final Work work = proxyWork.getInstance();
        work.writeBook();
    }
}
