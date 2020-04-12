package com.exercise.jdk8feature.lambda;

import java.util.Comparator;

/**
 * @author guofeng
 * @version 1.0
 * @desc
 * @createtime 2019/3/10 3:59 PM
 * @see jdk 1.8
 **/
public class FileNameComparator implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        if (o1.length() < o2.length()) {
            return -1;
        }
        if (o1.length() > o2.length()) return 1;
        return 0;
    }
}
