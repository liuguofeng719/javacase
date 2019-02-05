package com.exercise.extend;

/**
 * @author guofeng
 * @version 1.0
 * @desc
 * @createtime 2019/1/18 11:40 AM
 * @see jdk 1.7
 **/
public class ManagerInfo extends UserInfo {

    private long bonus;

    public ManagerInfo() {
    }

    @Override
    public long getSalary() {
        return super.getSalary() + getBonus();
    }

    public long getBonus() {
        return bonus;
    }

    public void setBonus(long bonus) {
        this.bonus = bonus;
    }
}
