package com.exercise.algorithm.sort;

import java.util.Arrays;

/**
 * @author guofeng
 * @version 1.0
 * @desc 选择排序
 * 时间复杂度O(n^2) 平方
 * @createtime 2018/12/15 11:08 PM
 * @see jdk 1.7
 **/
public class SelectSort {

    /**
     * int[] array = {5, 2, 6, 1, 9, 3};
     * 选择排序，数组总的索引为：5
     * 外层循环是从0开始
     * 内层循环从1开始比较
     *
     *  外 ： index = 0  value = 5
     *  内 ： index = 1 value = 2
     *  5 > 2 true {2, 5, 6, 1, 9, 3};
     *  2 > 6 false
     *  2 > 1 true 交互位置 {1, 5, 6, 2, 9, 3};
     *  1 > 9 false
     *  1 > 3 false;
     *
     *  外 index = 1 value = 5
     *  内 index = 2 value = 6
     *  5 > 6 false
     *  5 > 2 true {1, 2, 6, 5, 9, 3};
     *  2 > 9 false
     *  2 > 3 false
     *
     *  外 index = 2 value 6
     *  内 index = 3 value 5
     *  6 > 5 true {1, 2, 5, 6, 9, 3};
     *  5 > 9 false
     *  5 > 3 true {1, 2, 3, 6, 9, 5};
     *
     *  外 index = 3 value 6
     *  内 index = 4 value 9
     *  6 > 9 false
     *  6 > 5 true {1, 2, 3, 5, 9, 6};
     *
     *  外 index = 4 value 9
     *  内 index = 5 value 6
     *  9 > 6 true {1, 2, 3, 5, 6, 9};
     * @param array
     */
    public static void selectSort(int[] array) {

        for (int i = 0; i < array.length; i++) {

            for (int j = i + 1; j < array.length; j++) {

                if (array[i] > array[j]) { //第一个数比第二个数大，交互位置

                    int tmp = array[j];//把小的存储临时变量

                    array[j] = array[i]; // 把大的赋值给小的

                    array[i] = tmp; // 把小的赋值给大的
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] array = {5, 2, 6, 1, 9, 3};
        selectSort(array);
        System.out.println(Arrays.toString(array));
    }
}
