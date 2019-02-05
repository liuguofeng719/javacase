package com.exercise.packagetest;

import java.util.Objects;

/**
 * @author guofeng
 * @version 1.0
 * @desc
 * @createtime 2019/1/26 11:29 AM
 * @see jdk 1.7
 **/
public class Student {

    private String name;

    @Override
    public boolean equals(Object object) {
        if (object == this)
            return true;
        // 通过instanceof来判断对象是否属于类
//        if (object == null || !(object instanceof Student))
        if (object == null || getClass() != object.getClass())
            return false;
        Student student = (Student) object;
        // 跟上个instanceof版本唯一不同的是，这里使用student.getName()而不是student.name
        return name != null && name.equals(student.getName());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
