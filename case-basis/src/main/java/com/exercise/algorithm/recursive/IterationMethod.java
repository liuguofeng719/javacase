package com.exercise.algorithm.recursive;

import java.lang.reflect.Method;

/**
 * @author guofeng
 * @version 1.0
 * @desc 数学迭代法，在程序里面循环就是迭代法的体现，自上而下
 * <p>
 * 具体应用
 * 1，求解近似值/求值的精确
 * 2，在一定范围查找值
 * 3，机器学中的迭代
 * @createtime 2019/1/8 10:21 AM
 * @see jdk 1.7
 **/
public class IterationMethod {

    /**
     * 例如求解1+2+3+4+5+..+100
     */
    public static int iteration(int number) {
        int total = 0;
        for (int i = 1; i <= number; i++) {
            total += i;
        }
        return total;
    }

    /**
     * 在数组中找到指定的值
     * [1，3，6，8，9，10，13，17，19]
     * [0，1，2，3，4，5，6，7，8，9]
     * 通过迭代发二分查找指定的数
     *
     * @return
     */
    public static int binarySearch(int[] array, int target) {
        int min = 0;// 最小的
        int max = array.length - 1;//最大的
        for (int i = 0; i < 5; i++) {
            int middle = min + (max - min) / 2;//每次折半
            if (array[middle] == target) {
                return middle;
            }

            if (array[middle] > target) {
                max = middle - 1;
            } else {
                min = middle + 1;
            }
        }
        return -1;
    }

    /**
     * 求解近似值
     * 请求1到n中的数的平方小于n
     * n = 10
     *
     * @return
     */
    public static double getSquareRoot(int n, double deltaThreshold) {
        if (n <= 1) {
            return -0.1;
        }
        double min = 1.0;
        double max = n;
        for (int i = 0; i < 10000; i++) {
            double middle = min + (max - min) / 2;
            double square = middle * middle;
            // 误差值和设置一样或者小于返回平方根的值
            double delta = Math.abs((square / n) - 1);
            if (delta <= deltaThreshold) {
                return middle;
            }
            if (square > n) {
                max = middle;
            } else {
                min = middle;
            }
        }
        return -2.0;
    }


    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        System.out.println(IterationMethod.iteration(100));
        int[] arrays = {1, 3, 6, 8, 9, 10, 13, 17, 19};
        System.out.println(binarySearch(arrays, 10));

        final double squareRoot = getSquareRoot(10, 0.0001);
        if (squareRoot == -0.1) {
            System.out.println("请输入大于1的整数");
        } else if (squareRoot == -0.2) {
            System.out.println("没有找到近似的值");
        } else {
            System.out.println("结果为==" + squareRoot);
        }
        System.out.println(squareRoot);

        int[] array = new int[5];
        final int length = array.length - 1;
        final int i = length & "redis_1".hashCode();
        final int j = length & "redis_2".hashCode();
        System.out.println(i + " =-===" + j);


        try {
            final Class<?> forName = Class.forName("java.util.HashMap", true, Thread.currentThread().getContextClassLoader());
            final Method[] methods = forName.getMethods();
            for (Method method : methods) {
                if (method.getName().startsWith("put")) {
                }
            }
            System.out.println(methods);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
