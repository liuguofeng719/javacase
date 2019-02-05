package com.exercise;

import com.exercise.designpattern.decorator.Coffee;
import com.exercise.designpattern.decorator.ComponetCoffee;
import com.exercise.designpattern.decorator.MilkDecorator;
import com.exercise.designpattern.decorator.SugarDecorator;
import com.exercise.packagetest.GoodStudent;
import com.exercise.packagetest.Student;
import com.exercise.packagetest.UserInfo;
import com.exercise.packagetest.UserInfoExt;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.HashMap;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        Coffee coffee = new Coffee("德国", 12L);
        ComponetCoffee milkDecorator = new MilkDecorator(coffee);
        ComponetCoffee sugarDecorator = new SugarDecorator(milkDecorator);
        sugarDecorator.showConffee();
        System.out.println(milkDecorator.coffeePrice());

        Workbook newWorkBook2007 = new XSSFWorkbook();
        final Sheet sheet1 = newWorkBook2007.createSheet("sheet1");


        UserInfo u1 = new UserInfo("11");
        u1.setUid("1");
        UserInfoExt ext = new UserInfoExt();
        ext.setUid("1");
        System.out.println(u1.equals(ext));
//        UserInfoExt ext = new UserInfoExt();
//        final UserInfo ext1 = (UserInfo) ext;
//
//        UserInfo userInfo = new UserInfo();
//        final UserInfoExt info = (UserInfoExt) userInfo;

        GoodStudent son = new GoodStudent();
        Student father = new Student();
        son.setName("test");
        father.setName("test");

        // 采用 name.equals(student.getName())
        // 结果输出 son.equals(father) false
        System.out.println("son.equals(father) " + son.equals(father));
        // 结果输出 father.equals(son) true
        System.out.println("father.equals(son) " + father.equals(son));

        // 测试如果不实现hashcode，获取的值为空
        HashMap<Student,String> map = new HashMap<>();
        Student s1 = new Student();
        s1.setName("12");

        map.put(s1, "jj");

        Student s3= new Student();
        s3.setName("12");
        final String s = map.get(s3);

        System.out.println("=======" + s);
        Student s2 = new Student();
        s2.setName("12");

        System.out.println("=====" + s1.hashCode());
        System.out.println("=====" + s2.hashCode());
    }
}
