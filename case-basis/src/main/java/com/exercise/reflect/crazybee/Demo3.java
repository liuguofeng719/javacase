package com.exercise.reflect.crazybee;

/**
 * @author guofeng
 * @version 1.0
 * @see jdk 1.8
 * // 什么时候发生类初始化？
 * 类主动初始化
 * 1、虚拟机启动，首先初始化main函数所在的类
 * 2、通过new的方式初始化的类
 * 3、调用类的静态方法和静态属性，不包括final常量
 * 4、使用java.lang.reflect包对类的反射操作
 * 5、初始化一个类，如果父类没有初始化，首先初始化父类
 * 类被动初始化
 * 1、当类访问一个静态域的时候，只有包含的静态域的类才会被初始化，例如：如果是子类调用父类的静态域，子类不会初始化
 * 2、通过数组定义类引用，不会触发该类的初始化
 * 3、引用常量不会导致该类的初始化，因为常量的初始化在链接阶段已经做了
 **/
class Person1 {

    public static final String OPEN = "open";
    public static String CLOSE = "close";

    static {
        System.out.println(" person static ");
    }

    public Person1() {
        System.out.println(" Person ");
    }

    public static void show(){
        System.out.println(" == show ==");
    }
}

class Student1 extends Person1 {

    public static final String OPEN = "open";
    public static String CLOSE = "close";

    private String name;
    private int age;

    static {
        System.out.println(" student static");
    }

//    public Student() {
//        System.out.println(" Student ");
//    }


    public Student1(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

class Teacher1 extends Person1 {

    static {
        System.out.println(" Teacher static ");
    }

    public Teacher1() {
        System.out.println(" Teacher" );
    }
}

public class Demo3 {

    public static void main(String[] args) throws Exception{
        // new 方式
//        Person p = new Student();
//        final Class<?> aClass = Class.forName("com.exercise.reflect.crazybee.Student1");
//        aClass.newInstance();
        // 通过无参构造函数初始化
//        final Student newInstance = (Student) aClass.getDeclaredConstructor(null).newInstance(null);
//        System.out.println(newInstance.getAge());

        // 通过有参构成函数初始化
//        final Student newInstance = (Student) aClass.getDeclaredConstructor(String.class, int.class).newInstance("疯狂的小蜜蜂", 20);
//        System.out.println(newInstance.getAge());
        // 当类访问一个静态域的时候，只有包含的静态域的类才会被初始化，例如：如果是子类调用父类的静态域，子类不会初始化
        //System.out.println(Teacher.CLOSE);
        //通过数组定义类引用，不会触发该类的初始化
//        Student[] students = new Student[10];

        // 引用常量不会导致该类的初始化，因为常量的初始化在链接阶段已经做了
//        System.out.println(Student.OPEN);
    }
}
