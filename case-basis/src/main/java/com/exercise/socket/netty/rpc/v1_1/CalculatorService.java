package com.exercise.socket.netty.rpc.v1_1;

public interface CalculatorService {

    double add(double op1, double op2, double op3);

    double substract(double op1, double op2);

    double multiply(double op1, double op2);
}
