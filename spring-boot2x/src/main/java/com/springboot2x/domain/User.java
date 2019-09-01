package com.springboot2x.domain;

import com.springboot2x.enumeration.SexEnum;
import org.apache.ibatis.type.Alias;

/**
 * @author guofeng
 * @version 1.0
 * @desc
 * @createtime 2019/2/8 8:55 PM
 * @see jdk 1.7
 **/
@Alias("user")
public class User {
    private String userName;
    private Long id;
    private String note;

    private SexEnum sexEnum;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public SexEnum getSexEnum() {
        return sexEnum;
    }

    public void setSexEnum(SexEnum sexEnum) {
        this.sexEnum = sexEnum;
    }
}
