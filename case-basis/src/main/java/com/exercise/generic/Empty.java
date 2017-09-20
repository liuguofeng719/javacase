package com.exercise.generic;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2017/9/20 上午10:25
 * @see jdk 1.7
 **/
public class Empty<K extends String, V extends Number> {
    private K key;
    private V value;

    public Empty(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}

class App {

    public static void main(String[] args) {
        Empty<String, Integer> empty = new Empty<>("JAVA", 22);
        log(empty);
    }

    private static void log(Empty<? extends String, ? extends Number> empty) {
        System.out.println(empty.getKey() + " = " + empty.getValue());
    }
}
