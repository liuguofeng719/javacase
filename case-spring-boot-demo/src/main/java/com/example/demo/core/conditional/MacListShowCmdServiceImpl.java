package com.example.demo.core.conditional;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/6/13 下午6:08
 * @see jdk 1.7
 **/
public class MacListShowCmdServiceImpl implements ListShowCmdService {

    @Override
    public String listShowCmd() {
        return "ls";
    }
}
