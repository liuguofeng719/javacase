package com.exercise;


import com.exercise.packagetest.PackageTest;
import com.exercise.packagetest.UserInfo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 2018/12/10 14:38
 * jiacheng
 */
public class CalcTimeMinute {
    public static void main(String[] args) throws ParseException {
        String[] startTime = "09:36\t09:52\t10:05\t09:53\t09:44".split("\t");
        String[] endTime = "19:19\t19:19\t19:38\t19:28\t17:32".split("\t");
        String day = "2018-12-01 %s:00";
        long total = 0;
        System.out.println("---计算规则：正常上班时间9小时=540分钟");
        for (int i = 0; i < 5; i++) {
            String s = String.format(day, startTime[i]);
            String e = String.format(day, endTime[i]);
            long minute = calcMinutes(s, e);
            System.out.println("周" + (i + 1) + "上班时长（分）： " + minute + " ,加班时长(分)：" + (minute - 540));
            total += minute;
        }
        int oneDay = 9 * 60;
        int workDays = 5;
        long leaveMinute = total - oneDay * workDays;
        System.out.println("加班时长（分）： " + leaveMinute);
        PackageTest packageTest = new PackageTest();
        UserInfo userInfo =  new UserInfo("2");
    }

    private static long calcMinutes(String startTime, String endTime) throws ParseException {
        final String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        final Date start = sdf.parse(startTime);
        final Date end = sdf.parse(endTime);
        long minute = (end.getTime() - start.getTime()) / (1000 * 60);
        return minute;
    }
}
