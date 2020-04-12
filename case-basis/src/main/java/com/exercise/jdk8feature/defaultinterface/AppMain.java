package com.exercise.jdk8feature.defaultinterface;

/**
 * @author guofeng
 * @version 1.0
 * @desc Jdk 8 接口冲突问题，解决规则
 * 1，当一个类实现了多个接口，但是接口里面的方面都相同，并且方面都是默认实现，这样就产生了冲突
 * 1，解决办法，需要实现类强制实现该方法
 * 2，如果一个类继承了父类且实现了多个接口且父类的方法和接口里面默认的实现方法相同，则父类的方法优先，接口里面的方法无效
 * @createtime 2019/3/10 10:19 AM
 * @see jdk 1.8
 **/
public class AppMain {
    public static void main(String[] args) {
        Action action = new User();
        Listener listener = new User();
        action.show();
        listener.show();
    }
}
