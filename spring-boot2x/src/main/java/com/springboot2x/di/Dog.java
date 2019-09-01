package com.springboot2x.di;

import org.springframework.stereotype.Component;

/**
 * @author guofeng
 * @version 1.0
 * @desc
 * @createtime 2019/2/8 5:04 PM
 * @see jdk 1.7
 **/
@Component
public class Dog implements Animal {

    @Override
    public void use() {
        System.out.println("this is dog");
    }
}
