package com.exercise.reflect.crazybee;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/**
 * @author guofeng
 * @version 1.0
 * @see jdk 1.8
 **/
public class GetAllClassType {
    // 有哪些类型有Class对象
    /**
     * 1. 基本数据类型 primitive type
     * 2. class 类型
     * 3. 接口类型
     * 4. 数组 []
     * 5. 枚举
     * 6. void
     * 7. 注解
     * 8。二维数组 [][]
     * =====输出结果======
     * int
     * double
     * float
     * long
     * char
     * byte
     * short
     * class java.lang.Object
     * class [Ljava.lang.String;
     * class [[I
     * interface java.lang.reflect.Type
     * class java.lang.Thread$State
     * void
     * interface java.lang.annotation.Annotation
     */
    public static void main(String[] args) {
        // 基本数据类型
        final Class<Integer> intClass = int.class;
        System.out.println(intClass);
        final Class<Double> doubleClass = double.class;
        System.out.println(doubleClass);
        final Class<Float> floatClass = float.class;
        System.out.println(floatClass);
        final Class<Long> longClass = long.class;
        System.out.println(longClass);
        final Class<Character> characterClass = char.class;
        System.out.println(characterClass);
        final Class<Byte> byteClass = byte.class;
        System.out.println(byteClass);
        final Class<Short> shortClass = short.class;
        System.out.println(shortClass);
        // Class 类型
        final Class<Object> objectClass = Object.class;
        System.out.println(objectClass);
        final Class<Integer> integerClass1 = Integer.class;
        System.out.println(integerClass1);
        // 数组类型
        final Class<String[]> aClass1 = String[].class;
        System.out.println(aClass1);
        // 二维数组类型
        final Class<int[][]> aClass = int[][].class;
        System.out.println(aClass);
        // 接口类型
        final Class<Type> interfaceClass = Type.class;
        System.out.println(interfaceClass);
        // 枚举类型
        final Class<Thread.State> enumClass = Thread.State.class;
        System.out.println(enumClass);
        // 无返回值类型
        final Class<Void> voidClass = void.class;
        System.out.println(voidClass);
        // 注解类型
        final Class<Annotation> annotationClass = Annotation.class;
        System.out.println(annotationClass);
    }
}
