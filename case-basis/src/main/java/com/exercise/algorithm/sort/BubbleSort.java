package com.exercise.algorithm.sort;

import java.util.Arrays;

/**
 * @author guofeng
 * @version 1.0
 * @desc 冒泡排序，就是外层循环一次，内层循环就会少一次，意思就是外层循环一次，内层就会找到一个最大的值
 * 1，平均或者最差的时间复杂度（n^2）
 * 2，稳定算法
 * 3，Best  最好的时间复杂度O(n)
 * 4, Auxiliary place 空间复杂度 O(1)
 * @createtime 2018/12/16 3:39 PM
 * @see jdk 1.7
 **/
public class BubbleSort {

    /**
     * array = [4,5,9,1,8,3]
     * v1 [4,5,1,8,3,9]
     * @param array
     */
    public static void bubbleSort1(int[] array) {
        //必须2个元素才排序
        if (array.length <= 1) {
            return;
        }
        // n = 6
        final int n = array.length;
        // 循环5次
        for (int i = 0; i < n; i++) {
//            boolean isSwap = false;
            // 为什么第一次减1,因为索引是从0开始的
            final int in = n - i - 1;
            for (int j = 0; j < in; j++) {
                if (array[j] > array[j + 1]) {
                    // swap element
                    // 小的元素存储在临时变量
                    int tmp = array[j + 1];
                    // 把大的元素赋值给小的元素
                    array[j + 1] = array[j];
                    // 把临时变量的小元素赋值给大的元素
                    array[j] = tmp;
//                    isSwap = true;
                }
            }
//            if (!isSwap) {
//                break;
//            }
        }
    }

    /**
     * 优化之后的bubble sort
     * {4, 5, 9, 1, 8, 3}
     * @param arr
     */
    static void bubbleSort2(int arr[]) {
        int n = arr.length;
        boolean isSwap;
        /**
         * 循环5轮,
         * 循环一次把较大的一个选出来，内层循环就要少一次
         * 外层循环n-1，因为第一次循环完成就会找到最大值，所以最后一次循环不需要比较了
         * 内层n-i-1
         */
        for (int i = 0; i < n - 1; i++) {
            isSwap = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // swap arr[j+1] and arr[i]
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    isSwap = true;
                }
            }
            if (!isSwap) break;
        }
    }


    public static void main(String[] args) {
        int[] array = {4, 5, 9, 1, 8, 3};
//        bubbleSort1(array);
        bubbleSort2(array);
        System.out.println(Arrays.toString(array));
    }
}
