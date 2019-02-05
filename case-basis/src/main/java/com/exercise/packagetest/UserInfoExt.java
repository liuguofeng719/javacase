package com.exercise.packagetest;

/**
 * @author guofeng
 * @version 1.0
 * @desc
 * @createtime 2019/1/16 10:36 PM
 * @see jdk 1.7
 **/
public class UserInfoExt extends UserInfo {

    public UserInfoExt() {
        super("11");
    }

    protected String address;
    protected String linkPerson;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLinkPerson() {
        return linkPerson;
    }

    public void setLinkPerson(String linkPerson) {
        this.linkPerson = linkPerson;
    }

    public void show() {
        System.out.println("报错");
    }
}
