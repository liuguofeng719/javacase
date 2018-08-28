package com.boot.starter.domain;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/7/12 下午5:55
 * @see jdk 1.7
 **/
public class ResultResponse<T> {

    private String code;
    private T data;
    private String msg;

    private static ResultResponse response = new ResultResponse();

    public static <T> ResultResponse success(T data) {
        response.setData(data);
        response.setCode("200");
        return response;
    }

    public String getCode() {
        return code;
    }

    public ResultResponse setCode(String code) {
        this.code = code;
        return this;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public ResultResponse setMsg(String msg) {
        this.msg = msg;
        return this;
    }

}
