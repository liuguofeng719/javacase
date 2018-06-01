package com.demo.springboot.validator;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/4/25 下午10:55
 * @see jdk 1.7
 **/
public class UserVo {

    @NotBlank(message = "{user.name}")
    @Length(min = 2,max = 5,message = "{user.length}")
    public String name;
    @NotNull(message = "密码不能为空")
    private String pwd;
    @Min(value = 2,message = "最小年龄不能小于2岁")
    private int age;

    private String adddress;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAdddress() {
        return adddress;
    }

    public void setAdddress(String adddress) {
        this.adddress = adddress;
    }
}
