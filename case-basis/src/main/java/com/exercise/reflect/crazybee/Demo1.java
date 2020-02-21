package com.exercise.reflect.crazybee;

/**
 * @author guofeng
 * @version 1.0
 * @see jdk 1.8
 * 获取Class的4种方式
 **/

class User {
    private int age;
    private String name;
    private String address;
    static {
        MAX = 200;
        System.out.println("====static====" );
    }
    public static int MAX = 100;

    public User() {
        System.out.println("=======user======" + MAX);
    }

    public User(int age, String name, String address) {
        this.age = age;
        this.name = name;
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

public class Demo1 {

    public static void main(String[] args) throws Exception {
        // 第一种方式 所有的实例都是复用一份Class，所以这里的hashCode 相同
        final Class cls1 = User.class;
        final Class cls2 = User.class;
        System.out.println(cls1.hashCode());
        System.out.println(cls2.hashCode());
        // 135721597
        // 135721597
        System.out.println("User.class =================");

        // 第二种方式 包名 + 类名
        final Class cls3 = Class.forName("com.exercise.reflect.crazybee.User");
        final Class cls4 = Class.forName("com.exercise.reflect.crazybee.User");
        System.out.println(cls3.hashCode());
        System.out.println(cls4.hashCode());
        System.out.println("Class.forName =================");

        // 第三种方式
        User u1 = new User();
        final Class cls5 = u1.getClass();
        User u2 = new User();
        final Class cls6 = u2.getClass();
        System.out.println(cls5.hashCode());
        System.out.println(cls6.hashCode());
        System.out.println("u1.getClass() =================");

        // 第四种方式
        final Class cls7 = ClassLoader.getSystemClassLoader().loadClass("com.exercise.reflect.crazybee.User");
        final Class cls8 = ClassLoader.getSystemClassLoader().loadClass("com.exercise.reflect.crazybee.User");
        System.out.println(cls7.hashCode());
        System.out.println(cls8.hashCode());
        System.out.println("loadClass  ============");
    }
}
