package com.exercise.designpattern.proxy.cglibproxy;

import com.exercise.designpattern.proxy.staticproxy.Work;
import com.exercise.designpattern.proxy.staticproxy.WorkImpl;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2017/6/7 上午10:46
 * @see JDK 1.7
 **/
public class AppCGLibProxy {

    public static void main(String[] args) {
        CGlibProxy cGlibProxy = new CGlibProxy();
        final Work cGlibProxyProxy = cGlibProxy.getProxy(WorkImpl.class);
        cGlibProxyProxy.writeBook();
    }
}
