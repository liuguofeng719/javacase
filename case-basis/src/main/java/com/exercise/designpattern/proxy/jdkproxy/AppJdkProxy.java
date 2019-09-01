package com.exercise.designpattern.proxy.jdkproxy;

import com.exercise.designpattern.proxy.staticproxy.Work;
import com.exercise.designpattern.proxy.staticproxy.WorkImpl;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc $Proxy0 extend Proxy implments Subject {}
 * 1，通过Proxy.newInstance方法生成代理对象，这个对象继承Proxy并且实现目标对象相同的接口
 * 2，通过代理对象的实例，调用$Proxy0同样实现目标对象的接口方法,目标方法在调用Proxy父类包含InvocationHandler接口实现类的invoke方法，并且传入目标接口的Method对象
 * 3，实现InvocationHandler类里面包括目标类的引用
 * 4，调用InvocationHandler类的Invoke方法，方法的Method的参数执行invoke方法并且传入目标类的引用，执行真实的对象。
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
