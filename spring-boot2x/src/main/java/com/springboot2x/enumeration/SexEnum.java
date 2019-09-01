package com.springboot2x.enumeration;

/**
 * @author guofeng
 * @version 1.0
 * @desc
 * @createtime 2019/2/8 8:51 PM
 * @see jdk 1.7
 **/
public enum SexEnum {
    MALE(1, "男"),
    FEMALE(2, "女");
    private int code;
    private String name;

    SexEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static SexEnum getEnumById(int code) {
        final SexEnum[] values = SexEnum.values();
        for (SexEnum sexEnum : values) {
            if (sexEnum.code == code) {
                return sexEnum;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
