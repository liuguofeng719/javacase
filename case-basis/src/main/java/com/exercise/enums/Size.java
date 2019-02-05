package com.exercise.enums;

/**
 * @author guofeng
 * @version 1.0
 * @desc
 * @createtime 2019/1/26 8:39 PM
 * @see jdk 1.7
 **/
public enum Size {
    SMALL("S"), MEDIUM("M"), LARGE("L");
    private String addr;

    Size(String addr) {
        this.addr = addr;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public static void main(String[] args) {
        final Size small = Enum.valueOf(Size.class, "SMALL");
        System.out.println(small.ordinal());
        final Size[] values = Size.values();
        for (Size value : values) {

        }
    }
}
