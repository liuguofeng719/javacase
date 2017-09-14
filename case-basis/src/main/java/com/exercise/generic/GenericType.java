package com.exercise.generic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2017/9/12 上午10:56
 * @see jdk 1.7
 **/
public class GenericType {
    public static void main(String[] args) {

        /**
         要从泛型类取数据时，用extends；
         要往泛型类写数据时，用super；
         既要取又要写，就不用通配符（即extends与super都不用）。
         */
        List<Word> dogs = new ArrayList<>();
        dogs.add(new Animal());
        dogs.add(new Cat());
        dogs.add(new Dog());

        List<Cat> cats = new ArrayList<>();
        print(dogs, cats);

        //<? super Animal> 下界限定只能是自己，或者是自己的子类，父类不能确认类型，所有为了保证类型的一致性，所以不能添加父类
        List<? super Animal> number = new ArrayList<>();
        number.add(new Animal());
        number.add(new Cat());

        List<Animal> animalsD = new ArrayList<>();
        Collections.addAll(animalsD,new Dog());
//        number.add(new Word());

        // <? extends Word> 上界限定 不能往里面添加任何对象，null 除外
        List<? extends Animal> animals = new ArrayList<>();
        //编译不过
//        animals.add(new Animal());
//        animals.add(new Cat());

    }

    public static void print(List<? super Animal> des, List<? extends Animal> src) {

    }
}
