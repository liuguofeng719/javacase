package com.exercise.designpattern.factory.methodfactory;

import com.exercise.designpattern.factory.simplefactory.AddOperation;
import com.exercise.designpattern.factory.simplefactory.DivideOperation;
import com.exercise.designpattern.factory.simplefactory.MultiplyOperation;
import com.exercise.designpattern.factory.simplefactory.Operation;

/**
 * @author guofeng
 * @version 1.0
 * @desc
 * @createtime 2019/9/20 7:27 AM
 * @see jdk 1.8
 **/
public class FactoryImpl implements Factory {

    @Override
    public Operation createAddOper() {
        return new AddOperation();
    }

    @Override
    public Operation createMuliplyOper() {
        return new MultiplyOperation();
    }

    @Override
    public Operation createDivideOper() {
        return new DivideOperation();
    }
}
