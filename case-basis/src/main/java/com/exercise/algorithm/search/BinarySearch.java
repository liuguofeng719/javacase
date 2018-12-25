package com.exercise.algorithm.search;

/**
 * @author guofeng
 * @version 1.0
 * @desc 二分查找, 必须是有序的数据
 * 时间复杂度是logN
 * logN = log2 ^ 8 = 3 有8位，查找3次就能查找到
 * 对数
 *  x = log2^8 = 3
 *  以2低真数为8 ,x = 3
 *  log2^8 = 3 = 2^3 次方 = 2 * 2 * 2
 *  log2^16 = 4 = 2^4 次方 = 2 * 2 * 2 * 2
 *
 * @createtime 2018/12/14 10:54 PM
 * @see jdk 1.7
 **/
public class BinarySearch {

    /**
     * 二分查找,查找的值为6
     * low = 0
     * hight = 5 索引是从0开始的
     * -----------------
     * 1, middle = low + hight / 2 = 2 。 array[2] 取到中间的值
     * 2, 如果中间的值比查找的值大，middle - 1 = hight，缩小索引
     * 3, 如果中间的值比查找的值小，middle + 1 = low，扩大索引
     * 第一轮 中间值的索引 middle = 2 , value = 5 , destValue = 6,
     *       比目标值小，5的值索引为2，排除比较5小的值，所以最小的索引（5的索引）+1
     * 第二轮 low = 3 ,low < = hight
     *       (low + hight) / 2 = 4
     *      middle = 4 , value = 8 ,destValue = 6 中间值比目标值大，
     *      缩小最大值的范围，value = 8 的索引为 4，把值的当前索引缩小一位，hight = 4 - 1 = 3
     * 第三轮 low = 3 hight = 3
     *     （low + hight ) / 2 = 3
     *      value = 6 , destValue = 6 查找到值，直接返回
     * -----------------
     * 1, 3, 5, 6, 8, 10
     * -----------------
     * @param array
     * @param item
     */
    public static int binarySearch(int[] array, int item) {
        int low = 0;
        int hight = array.length - 1;//数组索引从1开始，

        while (low <= hight) {
            // 求数组的一半
            final int mid = (low + hight) / 2;
            final int guess = array[mid];
            if (guess == item) {// 查找到元素了，则返回元素的位置
                return mid;
            } else if (guess > item) { //如果中间的数据大于查找的数据，则往小的查找
                hight = mid - 1;
            } else {//如果中间数据比查找的数小则，往大的方向查找
                low = mid + 1;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] array = {1, 3, 5, 6, 8, 10};
        final int i = binarySearch(array, 6);
        System.out.println(String.format("索引为%d，值为%d", i, array[i]));
    }
}
