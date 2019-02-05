package com.exercise.collection;

/**
 * @author guofeng
 * @version 1.0
 * @desc
 * @createtime 2019/1/13 11:00 AM
 * @see jdk 1.7
 **/
public class HashMapTest {

    private static final int MAXIMUM_CAPACITY = Integer.MAX_VALUE;

    public static void main(String[] args) {
        final int size = HashMapTest.tableSizeFor(8);
        System.out.println(" === " + size);

        String greeting = "Hello";
        final int pointCount = greeting.codePointCount(0, greeting.length());
        System.out.println("point = " + pointCount);
        final char charAt = greeting.charAt(0);
        final char at = greeting.charAt(4);
        System.out.println("charAt = " + charAt + " at = " + at);
        final int index = greeting.offsetByCodePoints(0, 1);
        final int pointAt = greeting.codePointAt(index);//unicode-16 中的对应的码点
        System.out.println("index = " + index + " = " + pointAt);

        System.out.println((char) 101);
        for (int i = 0; i < 2; i++) {
            Employee employee = new Employee(i,"alex" + i);
            System.out.println(employee.getId());
            System.out.println(employee.getNextId());
        }
        double x = 10;
        System.out.println("before = " + x);
        tripleValue(x);
        System.out.println("after = " + x);

        Employee el = new Employee(0, "alex");
        System.out.println("before = " + el.getId());
        tripleSalary(el);
        System.out.println("after = " + el.getId());
        Employee xx = new Employee(1, "alexName");
        Employee yy = new Employee(2, "alexZhang");
        System.out.println("before= " + xx.getName());
        System.out.println("before= " + yy.getName());
        swap(xx, yy);
        System.out.println("after= " + xx.getName());
        System.out.println("after= " + yy.getName());
    }

    public static void tripleValue(double x) {
        x = x * 10;
    }

    public static void tripleSalary(Employee e) {
        e.setId(100);
    }

    public static void swap(Employee x, Employee y) {
        Employee tmp = x;
        x = y;
        y = tmp;

        System.out.println(" name = " + x.getName());
        System.out.println(" name = " + y.getName());
    }


    /**
     * @param cap
     * @return 5
     * 2^1
     * 2^2
     * 2^3
     * 2^4
     */
    static final int tableSizeFor(int cap) {
        int n = cap - 1;// 4
        n |= n >>> 1;// 0100 | 0010 = 0110 = 6
        n |= n >>> 2;// 0110 | 0001 = 0111 = 7
        n |= n >>> 4;// 0111 | 0001 = 0111 = 7
        n |= n >>> 8;//
        n |= n >>> 16;//
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
}

class Employee {
    private static int nextId = 1;
    private int id;
    private String name;

    static {
        System.out.println("static block");
    }

    {
        System.out.println("block");
    }

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
        System.out.println("construct");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNextId() {
        return nextId++;
    }
}