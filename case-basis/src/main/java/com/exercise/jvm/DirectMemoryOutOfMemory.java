package com.exercise.jvm;

import java.nio.ByteBuffer;

/**
 * Created with IntelliJ IDEA.
 * java启动参数共分为三类；
 * 其一是标准参数（-），所有的JVM实现都必须实现这些参数的功能，而且向后兼容；
 * 其二是非标准参数（-X），默认jvm实现这些参数的功能，但是并不保证所有jvm实现都满足，且不保证向后兼容；
 * 其三是非Stable参数（-XX），此类参数各个jvm实现会有所不同，将来可能会随时取消，需要慎重使用；
 * 标准参数中比较有用的：
 * verbose
 * -verbose:class
 * 输出jvm载入类的相关信息，当jvm报告说找不到类或者类冲突时可此进行诊断。
 * -verbose:gc
 * 输出每次GC的相关情况。
 * -verbose:jni
 * 输出native方法调用的相关情况，一般用于诊断jni调用错误信息。
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2017/9/5 下午4:25
 * @see jdk 1.7
 * -verbose:gc  -Xms48M -Xmx48M -XX:MaxDirectMemorySize=5M -Xss1M -XX:+PrintGCDetails
 * -Xmx Java Heap最大值，默认值为物理内存的1/4，最佳设值应该视物理内存大小及计算机内其他内存开销而定；
 * -Xms java Heap初始值，Server端JVM最好将-Xms和-Xmx设为相同值，开发测试机JVM可以保留默认值；
 * -Xmn Java Heap Young区大小，不熟悉最好保留默认值；
 * -Xss 每个线程的Stack大小，不熟悉最好保留默认值；
 **/
public class DirectMemoryOutOfMemory {
    public static final int ONE_GB = 1024 * 1024 * 1024;

    // Direct buffer memory
    public static void main(String[] args) {
        int i = 0;
        try {
            while (true) {
                ByteBuffer byteBuffer = ByteBuffer.allocateDirect(ONE_GB);
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
