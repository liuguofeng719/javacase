package com.demo.springboot.validator;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/4/26 上午10:37
 * @see jdk 1.7
 **/
public class ValidatorResult {

    private boolean isError;

    private Map<String,String> messages;

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }
}
