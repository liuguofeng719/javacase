package com.exercise.reflect.crazybee;

import java.lang.reflect.Array;

/**
 * @author guofeng
 * @version 1.0
 * @see jdk 1.8
 **/
public class ReflectArray {
    /**
     * getComponentType() 返回数组的类型
     */
    public static void main(String[] args) {
        int[] intArrays = {1, 2, 3, 4, 5};

        final Class<?> aClass = intArrays.getClass().getComponentType();
        // int
        System.out.println("===type = " + aClass.getTypeName());
        // 创建一个int数组，长度为10
        final Object instance = Array.newInstance(int.class, 5);
        // 获取数组的长度
        final int length = Array.getLength(instance);
        System.out.println("array length = " + length);
        System.arraycopy(intArrays, 0, instance, 0, length);

        for (final int i : ((int[]) instance)) {
            System.out.println("i = " + i);
        }
        // 初始化String类型的数组
        final Object newInstance = Array.newInstance(String.class, 10);
        // 设置值
        Array.set(newInstance, 0, "zhsan0");
        Array.set(newInstance, 1, "zhsan1");
        // 获取值
        System.out.println(Array.get(newInstance, 0));
        // 遍历值
        final String[] ints = (String[]) newInstance;
        for (final String anInt : ints) {
            System.out.println(anInt);
        }
    }

}
