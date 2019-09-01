package com.exercise.anonymous;

import java.lang.reflect.Field;

/**
 * @author guofeng
 * @version 1.0
 * @desc
 * @createtime 2019/3/21 10:36 PM
 * @see jdk 1.8
 **/
public class Pair<T> {
    private T data;

    public <T> T set(T t) {
        return t;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static void main(String[] args) {
        Pair<String> pair = new Pair<>();
        final Class<? extends Pair> aClass = pair.getClass();
        final Field[] declaredFields = aClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println(declaredField.getType().getName());
        }
    }
}
