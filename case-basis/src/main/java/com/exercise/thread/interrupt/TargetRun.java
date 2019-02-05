package com.exercise.thread.interrupt;


/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/4/9 下午4:57
 * @see jdk 1.7
 **/
public class TargetRun implements Runnable {

    @Override
    public void run() {
        try {
            final boolean interrupted = Thread.interrupted();
            System.out.println("-----------"+ interrupted);
            while (!interrupted) {
                System.out.println("没有调用中断线程操作" + Thread.interrupted());

//                Thread.sleep(1000);
            }
            System.out.println(Thread.interrupted());
            System.out.println("Exit normal");
        } catch (Exception e) {
            System.out.println("线程中断的操作==  " + Thread.interrupted() + " = " + e.getMessage());
            e.printStackTrace();
        }
    }
}
