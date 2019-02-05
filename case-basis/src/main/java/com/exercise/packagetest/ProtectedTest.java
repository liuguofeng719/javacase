package com.exercise.packagetest;

/**
 * @author guofeng
 * @version 1.0
 * @desc
 * @createtime 2019/1/16 10:37 PM
 * @see jdk 1.7
 **/
public class ProtectedTest {
    public static void main(String[] args) {
        UserInfoExt ext = new UserInfoExt();
        final String address = ext.address;
    }
}
