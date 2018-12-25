package com.exercise.algorithm.sort;

import java.util.Arrays;

/**
 * @author guofeng
 * @version 1.0
 * @desc 插入排序
 * 1,时间复杂度O(n^2)
 * 2,空间复杂度O(1)
 * 3,稳定排序
 * 4,平均复杂度O(n)
 * @createtime 2018/12/17 11:24 AM
 * @see jdk 1.7
 **/
public class InsertSort {

    /**
     * {4, 1, 6, 5, 3, 2}
     */
    public static void insertSort(int[] arr) {
        final int n = arr.length;
        if (n <= 1) {
            return;
        }

        //几轮排序完成
        for (int i = 1; i < n; ++i) {
            // 未排序的数组值
            int value = arr[i];
            // 已经排完序数组的索引，默认第一个元素已经排好序了
            int j = i - 1;//为什么减1，因为数组索引从0开始
            for (; j >= 0; --j) {
                /**
                 * 与排完序的值做对比，如果排完序的值比未排序的值大，交换元素的位置
                 */
                if (arr[j] > value) {
                    arr[j + 1] = arr[j];
                } else {
                    break;
                }
            }
            // 移动数据
            arr[j + 1] = value;
        }
    }

    public static void main(String[] args) {
        int[] arr = {4, 1, 6, 5, 3, 2};
        insertSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
