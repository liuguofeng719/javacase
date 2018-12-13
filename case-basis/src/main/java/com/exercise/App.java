package com.exercise;

import com.exercise.designpattern.decorator.Coffee;
import com.exercise.designpattern.decorator.ComponetCoffee;
import com.exercise.designpattern.decorator.MilkDecorator;
import com.exercise.designpattern.decorator.SugarDecorator;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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


    }
}
