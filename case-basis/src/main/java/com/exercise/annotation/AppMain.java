package com.exercise.annotation;


import java.lang.reflect.Method;

/**
 * @author guofeng
 * @version 1.0
 * @desc
 * @createtime 2019/2/16 8:21 PM
 * @see jdk 1.7
 **/
public class AppMain {
    public static void main(String[] args) {
        final Class<AnnotationTest> testClass = AnnotationTest.class;
        final Method[] methods = testClass.getDeclaredMethods();
        for (Method method : methods) {
            final NeedTest annotation = method.getAnnotation(NeedTest.class);
            if (annotation != null) {
                System.out.println("===注解" + method.getName() + " = " + annotation.value());
            } else {
                System.out.println("===未加注解" + method.getName());
            }
        }
    }
}
