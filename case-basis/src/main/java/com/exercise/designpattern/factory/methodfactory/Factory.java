package com.exercise.designpattern.factory.methodfactory;

import com.exercise.designpattern.factory.simplefactory.Operation;

/**
 * @author guofeng
 * @version 1.0
 * @desc
 * @createtime 2019/9/20 7:26 AM
 * @see jdk 1.8
 **/
public interface Factory {
    Operation createAddOper();
    Operation createMuliplyOper();
    Operation createDivideOper();
}
