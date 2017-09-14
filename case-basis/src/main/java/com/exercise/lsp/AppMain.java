package com.exercise.lsp;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2017/9/13 下午3:29
 * @see jdk 1.7
 **/

import java.util.HashMap;

/**
 * 　里氏替换原则包含以下4层含义：
 * 1,子类可以实现父类的抽象方法，但是不能覆盖父类的非抽象方法。
 * 2,子类中可以增加自己特有的方法。
 * 3,当子类覆盖或实现父类的方法时，方法的前置条件（即方法的形参）要比父类方法的输入参数更宽松。
 * 4,当子类的方法实现父类的抽象方法时，方法的后置条件（即方法的返回值）要比父类更严格。
 */
public class AppMain {

    public static void main(String[] args) {
//        1,子类可以实现父类的抽象方法，但是不能覆盖父类的非抽象方法。如果覆盖了非抽象方法，并改变行为，覆盖类的子类都会受到影响。
        Animal animal = new Cat();
        System.out.println(animal.function1(2, 1));
//        2,子类中可以增加自己特有的方法。
        Cat cat = new Cat();
        cat.sayHello();
//        3,当子类覆盖或实现父类的方法时，方法的前置条件（即方法的形参）要比父类方法的输入参数更宽松。
        Animal animal1 = new Cat();
        HashMap map = new HashMap();
        animal1.params(map);
//        4,当子类的方法实现父类的抽象方法时，方法的后置条件（即方法的返回值）要比父类更严格。
        Animal animal2 = new Cat();
        System.out.println("Animal=" + animal2.getNum());

//        B b= new A(); error
    }
}
