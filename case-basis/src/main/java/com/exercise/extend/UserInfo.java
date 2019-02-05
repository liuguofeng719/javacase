package com.exercise.extend;

import java.util.UUID;

/**
 * @author guofeng
 * @version 1.0
 * @desc
 * @createtime 2019/1/18 11:35 AM
 * @see jdk 1.7
 **/
public class UserInfo {

    protected String name;
    protected int age;
    protected String address;
    protected String idCard;
    protected long salary;
    final String id = UUID.randomUUID().toString();
    public UserInfo() {
    }

    public UserInfo(String name, int age, String address) {
        this(name, age, address, null, 0);
    }

    public UserInfo(String name, String idCard, long salary) {
        this(name, 0, "", idCard, salary);
    }

    public UserInfo(String name, int age, String address, String idCard, long salary) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.idCard = idCard;
        this.salary = salary;
    }

    /**
     * get salary
     */
    public long getSalary() {
        return this.salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }
}
