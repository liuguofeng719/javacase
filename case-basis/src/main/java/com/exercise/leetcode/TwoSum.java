package com.exercise.leetcode;

import com.google.common.collect.Maps;

import java.util.Arrays;
import java.util.Map;

/**
 * @author guofeng
 * @version 1.0
 * @desc 一个数组中，2个数相加结果等于输入的数
 * [2,7,11,15] target = 9
 * 2 + 7 = 9 返回的索引为[0,1]
 * @createtime 2019/1/21 10:22 PM
 * @see jdk 1.7
 **/
public class TwoSum {

    public static void main(String[] args) {
        int[] nums = {2, 7, 15, 11};
        System.out.println(Arrays.toString(twoSum(nums, 9)));
        System.out.println(Arrays.toString(twoSum2(nums, 18)));
    }

    /**
     * 通过2层循环来实现2个数的和等于第三个数
     * 时间复杂度 O(n^2)
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    /**
     * [2,7,11,15] target = 9
     * 通过Map实现
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = Maps.newHashMap();
        for (int i = 0; i < nums.length; i++) {
            final int value = target - nums[i];
            if (map.get(value) != null) {
                return new int[]{map.get(value), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }
}
