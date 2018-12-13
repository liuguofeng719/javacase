package com.netty.example.ext;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/10/27 4:15 PM
 * @see jdk 1.7
 **/
public class BitDemo {

    public static void main(String[] args) {

        int a = 100;
        System.out.println("实现100*2的结果为" + (a << 1));
        System.out.println("实现100除4的结果为" + (a >> 2));

        int b = 5;
        /**
         *  b=5
         *  0101
         *  0101
         *  1110
         *
         *  b=2
         *  0010
         *  0010
         *  1011
         *
         *  b=4
         *  0100
         *  0100
         *  1101
         *
         *  1*2^0+0*2^1
         *  数为零变为1,若果是1则为0
         *  0011 1100
         *  0*2^0+0*2^1+1*2^2+1*2^3+1*2^4+1*2^5
         *  0+0+4+8+16+32
         */
        System.out.println("b 非的结果是：" + (~b));
        System.out.println("2进制转10结果：" + Integer.valueOf("10000001", 2).toString());
        System.out.println("10进制转2结果：" + Integer.toBinaryString(9999).toString()); //0010 0111 0000 1111
        System.out.println("10进制转2结果：" + Integer.toBinaryString(2).toString());
        System.out.println("或：" + (15 | 2));
        System.out.println("异或：" + (15 ^ 2));
        System.out.println(9999&0Xff);

        int a1 = 15;
        int b1 = 2;
        System.out.println("a 与 b 异或的结果是：" + (a1 ^ b1));

        /**
         * 0010 2
         * 0101 5
         * 0111 7
         */
        idea2(1, 2);

        System.out.println("奇数还是偶数：" + isEven(20));

        final int change = change(2);
        System.out.println(change);
        final byte[] bytes = IntToByteArray(97);
        System.out.println(ByteArrayToInt(bytes));
    }

    public static void demo(){
        int a=129;
        //              第一组         第二组        第三组         第四组
        //2的二进制表示完整为 "[00000000][00000000][00000000][00000010]"[]括号实际没有，为了看起来清楚加的
        byte[] b=new byte[4];
        //向右移位是   低位舍弃，高位补符号位
        //向右移位运算，移动24位后，高8位，被移动到低8位上，二、三、四组都会被丢弃
        b[0]=(byte) (a>>24);
        //向右移动16位，高16位到低16位地方，第三、四组会被舍弃，至于&0xff这里不容易看出来，b[3]那一行能看出来
        b[1]=(byte) ((a>>16)&0xff);
        //移动8位第四组会被丢弃，结果还是0
        b[2]=(byte) ((a>>8)&0xff);
        //不移动直接进行与（&）运算，0xff的二进制是第四位为8个1 其他是0的数，作用就是排除不想要的位
        //这里来个例子 [00000000][00000110][00000100][00000010]假如有这么个二进制的数字
        //如果你想取到[00000100]字节的值 ， 先对其向右移动8位变为
        //[00000000][00000000][00000110][00000100]
        //然后和0xff与运算,0xff二进制[00000000][00000000][00000000][11111111]
        //与运算后的结果就为[00000000][00000000][00000000][00000100]
        //这样需要的字节就拿到了
        b[3]=(byte) (a&0xff);
        for (byte c : b) {
            System.out.print(Integer.toBinaryString(c&0xff)+" ");
        }
        System.out.println();
        //把字节转回Int和上面颠倒下，就不多说了。
        int i=0;
        i+=((b[0]&0xff)<<24);
        i+=((b[1]&0xff)<<16);
        i+=((b[2]&0xff)<<8);
        i+=((b[3]&0xff));
        System.out.println(i);
    }

    /*
        int型32位
        1个byte型8位
        例如：int类型：97
        则二进制表示为：00000000 00000000 00000000 01100001

        将int转为低字节在前，高字节在后的byte数组
        b[0] = 11111111(0xff) & 01100001
        b[1] = 11111111(0xff) & (n >> 8)00000000
        b[2] = 11111111(0xff) & (n >> 16)00000000
        b[3] = 11111111(0xff) & (n >> 24)00000000
    */
    public static byte[] IntToByteArray(int n) {
        byte[] b = new byte[4];
        b[0] = (byte) (n);
        b[1] = (byte) (n >> 8 );
        b[2] = (byte) (n >> 16 );
        b[3] = (byte) (n >> 24 );
        return b;
    }

    /**
     为什么需要&0xff ,因为1个字节8个bit，int4个字节，就是32bit，

     10000000 00000000 00000000 00000001  -1 原码

     11111111 11111111 11111111 11111110  -1 反码

     11111111 11111111 11111111 11111111  -1 补码
     0010 1101 +1

     1110
     */
    //将低字节在前转为int，高字节在后的byte数组(与IntToByteArray1想对应)
    public static int ByteArrayToInt(byte[] bArr) {
        if (bArr.length != 4) {
            return -1;
        }
        return (int) ((((bArr[3] & 0xff) << 24)
                | ((bArr[2] & 0xff) << 16)
                | ((bArr[1] & 0xff) << 8)
                | ((bArr[0] & 0xff))));
    }


    /**
     * 变换符号，正数变负数，负数变正数
     * 例如：8 ，二进制中标识负数通过，最高位为1则是负数，0为正数
     * 0000 1000 反码 1111 0111 取反 1111 1000 = -8
     * 1111 1000 反码 0000 0111 取反 0000 1000 = 8
     *
     * @param x
     * @return
     */
    public static int change(int x) {
        return ~x + 1;
    }

    /**
     * 判断任意非负数的，奇数和偶数
     */
    public static boolean isEven(int x) {
        return (x & 1) == 1 ? false : true;
    }

    /**
     * 交换2个数位置，不使用中间变量
     */
    public static void idea2(int a, int b) {
        a = a ^ b;// 0001 ^ 0010 = 0011 3  = 1+2=3 = a =a+b;
        b = b ^ a;// 0010 ^ 0011 = 0001 1  = 3-2=1 = b =a-b;
        a = a ^ b;// 0010 ^ 0001 = 0011 2  = 3-1=2 = a =a-b;
        System.out.println("a是：" + a);
        System.out.println("b是：" + b);
    }
}
