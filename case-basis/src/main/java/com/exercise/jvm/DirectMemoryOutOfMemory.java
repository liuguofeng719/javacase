package com.exercise.jvm;

import java.nio.ByteBuffer;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2017/9/5 下午4:25
 * @see jdk 1.7
 * -verbose:gc  -Xms48M -Xmx48M -XX:MaxDirectMemorySize=5M -Xss1M -XX:+PrintGCDetails
 **/
public class DirectMemoryOutOfMemory {
    public static final int ONE_G = 1024 * 1024 * 1024;

    // Direct buffer memory
    public static void main(String[] args) {
        int i = 0;
        try {
            while (true) {
                ByteBuffer byteBuffer = ByteBuffer.allocateDirect(ONE_G);
                i++;
            }
        } catch (Exception e) {
            System.out.println("Exception= " + i);
            e.printStackTrace();
        } catch (Error error) {
            System.out.println("Error= " + i);
            error.printStackTrace();
        }
    }
}
