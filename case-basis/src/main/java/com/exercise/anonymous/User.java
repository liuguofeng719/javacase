package com.exercise.anonymous;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * @author guofeng
 * @version 1.0
 * @desc 内部类，匿名内部类
 * @createtime 2019/3/10 10:13 PM
 * @see jdk 1.8
 **/
@NoArgsConstructor
public class User {

    private String name;

    private String idNo;

    public void sayHello() {

    }

    public void createUser() {

    }

    @Getter
    @Setter
    @RequiredArgsConstructor
    public class Address {
        @NonNull
        private String province;
        private String city;
        private String street;

        public void makeUser() {
            createUser();
            sayHello();
        }
    }

    public static void main(String[] args) {
        User u = new User();
        User.Address address = u.new Address("2323");
    }
}
