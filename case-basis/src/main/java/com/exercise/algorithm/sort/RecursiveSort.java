package com.exercise.algorithm.sort;

/**
 * @author guofeng
 * @version 1.0
 * @desc 1, 结束条件
 * 2，递归条件
 * @createtime 2018/12/16 9:49 AM
 * @see jdk 1.7
 **/
public class RecursiveSort {

    public static int coutDown(int count) {
        if (count == 1) {
            return count;
        }
        System.out.println(count);
        return coutDown(count - 1);
    }

    /**
     * 栈的特性是，先进后出，后进先出，
     * 递归：首先把把递归方法添加到栈中，等递归的条件终止了
     * 计算结果返回按照后进先出的原理一个函数一个函数执行
     * 阶乘
     * 调用顺序
     * factorial(5) 入栈
     *    factorial(4) 入栈
     *       factorial(3) 入栈
     *          factorial(2) 入栈
     *             factorial(1) 入栈
     *                return 1 出栈 = factorial(f=1) = factorial(1) =1
     *             return 2*1 = 2 出栈 = factorial(f=2) * factorial(1) = 2
     *          return 3*2 = 6 出栈 = factorial(f=3) * factorial(2) * factorial(1) = 6
     *       return 4*6 = 24 出栈 = factorial(f=4) * factorial(3) * factorial(2) =24
     *    return 5*24 = 120 出栈 = factorial(f=5) * factorial(4) * factorial(3) * factorial(2) = 120
     * @param f
     * @return
     * code                         stack
     * ------------------------------------------
     * factorial(5)                 factorial f=5
     * if语句                       factorial f=5
     * f*factorial(f-1)递归调用     f = 4
     *                             f = 5
     * f*factorial(f-1)递归调用     f = 3
     *                             f = 4
     *                             f = 5
     */
    public static int factorial(int f) {
        if (f == 1) {
            return f;
        }
        return f * factorial(f - 1);
    }

    public static void main(String[] args) {
        System.out.println(coutDown(5));
        System.out.println(factorial(5));
    }
}
