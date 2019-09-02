package com.exercise.designpattern.pipeline.v1;

import lombok.extern.slf4j.Slf4j;

/**
 * @author guofeng
 * @version 1.0
 * @desc
 * @createtime 2019/9/1 11:51 AM
 * @see jdk 1.8
 **/
@Slf4j
public class ValveValidateImpl implements Valve {

    @Override
    public void invoke() {
        log.info("ValveValidate = {}", "校验检查");
    }
}
