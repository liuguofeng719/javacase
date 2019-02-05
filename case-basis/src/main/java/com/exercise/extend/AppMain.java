package com.exercise.extend;

/**
 * @author guofeng
 * @version 1.0
 * @desc
 * @createtime 2019/1/18 11:45 AM
 * @see jdk 1.7
 **/
public class AppMain {
    public static void main(String[] args) {

        ManagerInfo managerInfo = new ManagerInfo();
        managerInfo.setName("Mrs Liu");
        managerInfo.setAge(20);
        managerInfo.setAddress("Si Chuan Chengdu");
        managerInfo.setSalary(20);
        managerInfo.setBonus(10);
        System.out.println(managerInfo.getSalary());


        ManagerInfo[] managerInfos = new ManagerInfo[2];
        UserInfo[] userInfos = managerInfos;
        userInfos[0] = new UserInfo();
        userInfos[0].setSalary(222);
    }
}
