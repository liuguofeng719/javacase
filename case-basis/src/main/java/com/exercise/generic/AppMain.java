package com.exercise.generic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2017/9/12 下午3:36
 * @see jdk 1.7
 **/
public class AppMain {

    //为什么需要通配符限定,因为为了解决类型转换 ClassCastException
    public static void main(String[] args) {

        // 这里能添加子类，李斯替换原则
        List<Animal> list = new ArrayList<>();
        list.add(new Dog());
        list.add(new Cat());
        list.add(new Animal());
        printAnimal(list);

        List<Cat> cats = new ArrayList<>();
        cats.add(new Cat());
        //compile error
        printAnimal(cats);

        //为了解决这个问题 所以Sun公司 想出了类型限定的方式 让2个容器的元素是存在关系的

        /**
         * 集合里面存了Animal的子类，但是编译器，认为 List<Cat> List<Animal> 容器存的东西不一样，没有一点关联
         * 所以Sun公司 想出了类型限定的方式 让2个 容器的元素是存在关系的
         * <? extends T> 上界限定，表示，所有的类型只能是T 或者是T的子类，例如：Animal 的子类 Cat Dog，不能确定添加的是Cat或者是Dog
         * 但是这种限定只是保证了在读取数据的通用性，为了保证集合里面类型的一致性性，所以不能添加任何元素，null 除外
         * JAVA Efficient 书中 通过 PECS（Producer Extends Consumer Super）原则 介绍
         * 在输入数据源限定 使用<? extends T> 在使用数据的时候 <? super T>
         * <? super T> 这里T 只能是自己或者T的子类，不能是父类，因为父类不能确定类型，为了保证类型的一致性，所以不能添加父类
         */

        List<Animal> as = new ArrayList<>();

        printAnimal(as, list);
        printAnimal(as, cats);

        printAnimal(as);

        Collection<String> collection = new ArrayList<>();
        fromArrayToCollections(new String[]{"hello", "java"}, collection);

        List<String> ass = new ArrayList<>();
        ass.add("hello");
        ass.add("java");
        print(ass);
        print(as);
    }

    public static void printAnimal(List<? extends Animal> animals) {
        for (final Animal animal : animals) {
            System.out.println(animal);
        }
    }

    public static void printAnimal(List<? super Animal> newAnimals,
                                   List<? extends Animal> animals) {
        for (final Animal animal : animals) {
            newAnimals.add(animal);
        }
    }

    public static <T> void fromArrayToCollections(T[] ts, Collection<T> collection) {
        for (final T t : ts) {
            collection.add(t);
        }
    }

    // ? 任意类型匹配
    public static <T> void print(List<?> lists){
        for (final Object o : lists) {
            System.out.println(o);
        }
    }
}
