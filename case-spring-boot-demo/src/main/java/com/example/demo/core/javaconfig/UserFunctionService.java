package com.example.demo.core.javaconfig;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/5/31 下午4:21
 * @see jdk 1.7
 **/
public class UserFunctionService {

    private FunctionSerivce functionSerivce;

    public FunctionSerivce getFunctionSerivce() {
        return functionSerivce;
    }

    public void setFunctionSerivce(FunctionSerivce functionSerivce) {
        this.functionSerivce = functionSerivce;
    }

    public void sayHello() {
        functionSerivce.sayHello();
    }
}
