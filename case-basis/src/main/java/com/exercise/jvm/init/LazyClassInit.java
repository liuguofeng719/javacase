package com.exercise.jvm.init;

/**
 * @author guofeng
 * @version 1.0
 * @see jdk 1.8
 * 显示类的加载信息
 * jvm 参数 -XX:+TraceClassLoading
 * 类只有在使用到的时候才会加载
 * [Loaded com.exercise.jvm.init.LazyClassInit from file:/Users/lgfcxx/Documents/IdeaProject/javacase/case-basis/target/classes/]
 * [Loaded sun.launcher.LauncherHelper$FXHelper from /Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/jre/lib/rt.jar]
 * [Loaded java.lang.Void from /Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/jre/lib/rt.jar]
 * =====LazyClassInit=====
 * ======init======
 * [Loaded com.exercise.jvm.init.A from file:/Users/lgfcxx/Documents/IdeaProject/javacase/case-basis/target/classes/]
 * =======A=======
 * =============
 * [Loaded com.exercise.jvm.init.B from file:/Users/lgfcxx/Documents/IdeaProject/javacase/case-basis/target/classes/]
 * =======B=======
 * [Loaded java.lang.Shutdown from /Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/jre/lib/rt.jar]
 * [Loaded java.lang.Shutdown$Lock from /Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/jre/lib/rt.jar]
 **/
public class LazyClassInit {

    static {
        System.out.println("=====static LazyClassInit=====");
    }

    public static void main(String[] args) {
        LazyClassInit init = new LazyClassInit();
        A a1=new A();
        System.out.println("=============");
        B b1 = new B();
    }
}

class A {
    public A() {
        System.out.println("=======A=======");
    }
}

class B {
    public B() {
        System.out.println("=======B=======");
    }
}