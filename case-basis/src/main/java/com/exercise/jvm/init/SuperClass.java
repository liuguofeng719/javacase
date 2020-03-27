package com.exercise.jvm.init;

/**
 * @author guofeng
 * @version 1.0
 * @see jdk 1.8
 * jvm 参数
 * -XX:+TraceClassLoading 或者 -verbose:class
 * 读取或者设置一个类型的静态字段或者静态方法，被调用类的静态块被初始化
 *  public static final int value =12; final 修饰的已经在编译期已经把结果放入常量池中了。
 *
 * SuperClass init
 * 123
 * [Loaded java.lang.Shutdown from /Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/jre/lib/rt.jar]
 * [Loaded java.lang.Shutdown$Lock from /Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/jre/lib/rt.jar]
 **/
public class SuperClass {
    static {
        System.out.println("SuperClass init");
    }

    public SuperClass() {
        System.out.println(" constructor SuperClass");
    }

    public static int value = 123;



    public static void test() {
        System.out.println("test");
    }
}

class SubClass extends SuperClass {
    static {
        System.out.println("SubClass init!");
    }
}

class NotIntitialization {

    public static void main(String[] args) {
//        System.out.println(SubClass.value);
        SuperClass.test();
//        SuperClass[] sca = new SuperClass[10];
    }

}
