package com.exercise.packagetest;

import java.util.Objects;

/**
 * @author guofeng
 * @version 1.0
 * @desc
 * @createtime 2019/1/16 10:34 PM
 * @see jdk 1.7
 **/
public class UserInfo {
    protected String name;
    protected String age;
    private String uid;
    private final String z;
    public UserInfo(String z) {
        this.z = z;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
        if (o == null || !(o instanceof UserInfo))
            return false;
        UserInfo userInfo = (UserInfo) o;
        return Objects.equals(name, userInfo.name) &&
                Objects.equals(age, userInfo.age) &&
                Objects.equals(uid, userInfo.getUid());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, uid);
    }
}
