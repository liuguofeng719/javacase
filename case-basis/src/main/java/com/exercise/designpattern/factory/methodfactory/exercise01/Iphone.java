package com.exercise.designpattern.factory.methodfactory.exercise01;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author guofeng
 * @version 1.0
 * @desc 手机对象
 * @createtime 2019/9/25 10:39 PM
 * @see jdk 1.8
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Iphone implements IphoneFunction {

    private String name;
    private String dimension;
    private String description;

    @Override
    public void call() {
        System.out.println(this.getName() + "手机 打电话");
    }

    @Override
    public void accept() {
        System.out.println(this.getName() + "手机 接听电话");
    }
}
