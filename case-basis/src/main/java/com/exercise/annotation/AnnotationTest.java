package com.exercise.annotation;

/**
 * @author guofeng
 * @version 1.0
 * @desc
 * @createtime 2019/2/16 8:19 PM
 * @see jdk 1.7
 **/
public class AnnotationTest {

    @NeedTest(value = false)
    public void deleteForm(int frmId) {
        System.out.println("删除frm" + frmId);
    }

    public void show() {
        System.out.println("ddd");
    }
}
