package com.netty.example.nio;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/10/22 下午2:56
 * @see jdk 1.7
 **/
public class ByteBufferDemo {

    static ByteBuffer byteBuffer;

    public static void main(String[] args) throws Exception {
//        channelToBuffer();
//        bufferToBuffer();
//        bufferToChannel();
//        testInitBuffer();
//        testPosition();
//        testByte();
//        testChar();
//        testMark();
//        testInt();
//        testFloat();
//        testDouble();
//        testLong();
//        testRemaining();
//        testOverFlow();
//        testReset();
//        clear();
//        testCompact();

//        testHeapByteBufferSlice();
//        testDricterByteBufferSlie();
        testMappedByteBuffer();


    }

    private static void testMappedByteBuffer() throws Exception {
        File file = new File("a.txt");
        RandomAccessFile accessFile = new RandomAccessFile(file, "rw");
        final FileChannel channel = accessFile.getChannel();
        final MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, 20 * 300000);
//        final byte[] bytes = "1111222".getBytes();
//        map.put(bytes);
        for (int i = 0; i < 3; i++) {
            map.putLong(123 + i);
            map.putInt(44 + i);
            map.putLong(555 + i);
        }
        map.flip();
        map.force();
        for (int j = 0; j < 60; j += 20) {
            System.out.println("aa"+j);
//            final long offset = map.getLong();
//            final int anInt = map.getInt();
//            final long mapLong = map.getLong();
//            System.out.println("offset:"+offset+" msgSize "+ anInt + " tagsCode "+ mapLong);
        }

//        final int position = map.position();
//        final int limit = map.limit();
//        System.out.println("position = "+position+" = limit "+limit);
//
//        final ByteBuffer slice = map.slice();
//        slice.position(0);
//        slice.limit(7);
//        byte[] dest =new byte[7];
//        slice.get(dest);
//        System.out.println(new String(dest));
//        slice.clear();
//
//        channel.position(7);
//
//        final byte[] bytes1 = "xxx".getBytes();
//        channel.write(ByteBuffer.wrap(bytes1));
//
//        channel.close();

    }

    public static void testDricterByteBufferSlie() {
        final ByteBuffer allocateDirect = ByteBuffer.allocateDirect(10);
        final int capacity = allocateDirect.capacity();
        for (int i = 0; i < capacity; i++) {
            allocateDirect.put((byte) i);
        }
        final ByteBuffer slice = allocateDirect.slice();
        slice.position(3);
        final ByteBuffer newByteBuffer = slice.slice();
        newByteBuffer.limit(7);
        for (int i = 0; i < newByteBuffer.capacity(); i++) {
            System.out.println(newByteBuffer.get(i));
        }

    }

    public static void testHeapByteBufferSlice() {
        final ByteBuffer allocate = ByteBuffer.allocate(10);
        final int capacity = allocate.capacity();
        for (int i = 0; i < capacity; i++) {
            allocate.put((byte) i);
        }

        allocate.position(3);
        allocate.limit(7);
        final ByteBuffer slice = allocate.slice();

        for (int i = 0; i < slice.capacity(); i++) {
            System.out.println(slice.get(i));
        }
        System.out.println();

        for (int i = 0; i < slice.capacity(); i++) {
            byte b = slice.get(i);
            b *= 11;
            slice.put(i, b);
        }

        allocate.position(0);
        allocate.limit(allocate.capacity());
        while (allocate.hasRemaining()) {
            System.out.println(allocate.get());
        }
    }

    public static void testInitBuffer() {
        byteBuffer = ByteBuffer.allocate(1024);
        System.out.println("====初始化buffer====");
        System.out.println("position:" + byteBuffer.position());
        System.out.println("limit:" + byteBuffer.limit());
        System.out.println("capacity:" + byteBuffer.capacity());
    }

    public static void testPosition() {
        byte[] bytes = ("Hello RocketMQ aa").getBytes();
        byte[] bytes1 = ("Hello RocketMQ ").getBytes();
        for (int i = 0; i < 2; i++) {
            if (i % 2 == 0) {
                byteBuffer.put(bytes);
                System.out.println();
                System.out.println("length: " + bytes.length + " position:" + byteBuffer.position());
            } else {
                byteBuffer.put(bytes1);
                System.out.println();
                System.out.println("length: " + bytes1.length + " position:" + byteBuffer.position());
            }
            System.out.println("limit:" + byteBuffer.limit());
            System.out.println("capacity:" + byteBuffer.capacity());
            System.out.println();
        }
    }

    //byte 占用1个字节
    public static void testByte() {
        System.out.println("====put byte====");
        byte b = 102;
        byteBuffer.put(b);
        System.out.println("position:" + byteBuffer.position());
        System.out.println("limit:" + byteBuffer.limit());
        System.out.println("capacity:" + byteBuffer.capacity());
        System.out.println("== get byte ==" + byteBuffer.get(0));
    }

    //char 占用2个字节
    public static void testChar() {
        System.out.println("====put char====");
        char b = 'a';
        byteBuffer.putChar(b);
        System.out.println("position:" + byteBuffer.position());
        System.out.println("limit:" + byteBuffer.limit());
        System.out.println("capacity:" + byteBuffer.capacity());
        System.out.println("== get char ==" + byteBuffer.getChar(1));
    }

    public static void testMark() {
        System.out.println("====mark====");
        byteBuffer.mark();
        System.out.println("position:" + byteBuffer.position());
        System.out.println("limit:" + byteBuffer.limit());
        System.out.println("capacity:" + byteBuffer.capacity());

    }

    //int 占用4个字节
    public static void testInt() {
        System.out.println("====put int====");
        int b = 4;
        int b1 = 9999;
        int b2 = 218;
        byteBuffer.putInt(b);
//        byteBuffer.putInt(b1);
//        byteBuffer.putInt(b2);
        System.out.println("position:" + byteBuffer.position());
        System.out.println("limit:" + byteBuffer.limit());
        System.out.println("capacity:" + byteBuffer.capacity());
        //为什么要在index=3的获取，因为，添加了1个字节+添加了2个字节=3，所以从3的位置开始获取
        System.out.println("== get int ==" + byteBuffer.getInt(3));
    }

    //Float 占用4个字节
    public static void testFloat() {
        System.out.println("====put Float====");
        float b = 10;
        byteBuffer.putFloat(b);
        System.out.println("position:" + byteBuffer.position());
        System.out.println("limit:" + byteBuffer.limit());
        System.out.println("capacity:" + byteBuffer.capacity());
        //为什么要index=7获取，因为添加1个字节+2个字节+4个字节=7，所有重7开始
        System.out.println("== get Float ==" + byteBuffer.getFloat(7));
    }

    //Double 占用8个字节
    public static void testDouble() {
        System.out.println("====put Double====");
        double b = 12.22;
        byteBuffer.putDouble(b);
        System.out.println("position:" + byteBuffer.position());
        System.out.println("limit:" + byteBuffer.limit());
        System.out.println("capacity:" + byteBuffer.capacity());
        //为什么要index=7获取，因为添加1个字节+2个字节+4个字节+4个字节，所有重11开始
        System.out.println("== get Double ==" + byteBuffer.getDouble(11));

    }

    //Double 占用8个字节
    public static void testLong() {
        System.out.println("====put Long====");
        long b = 15L;
        byteBuffer.putLong(b);
        System.out.println("position:" + byteBuffer.position());
        System.out.println("limit:" + byteBuffer.limit());
        System.out.println("capacity:" + byteBuffer.capacity());
        //为什么要index=7获取，因为添加1个字节+2个字节+4个字节+4个字节+8，所有重11开始
        System.out.println("== get Long ==" + byteBuffer.getLong(19));

    }

    //测试剩余空间大小,5个字节的大小
    public static void testRemaining() {
        System.out.println("===== buffer 剩余空间大小  ===== : " + byteBuffer.remaining());
    }

    //测试添加元素长度，大于空间大小,BufferOverflowException
    public static void testOverFlow() {
        long max = 20;
        try {
            byteBuffer.putLong(max);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //测试重置mark标记
    public static void testReset() {
        System.out.println("======= reset =========");
        byteBuffer.reset();//把标记的索引赋值给position,标记位置是3
        System.out.println("position:" + byteBuffer.position());
        System.out.println("limit:" + byteBuffer.limit());
        System.out.println("capacity:" + byteBuffer.capacity());
        System.out.println("get int from mark = " + byteBuffer.getInt(3));
        int int5 = 5;//覆盖以前的值，重标记位开始更新数据
        byteBuffer.putInt(int5);
        System.out.println("position:" + byteBuffer.position());
        System.out.println("limit:" + byteBuffer.limit());
        System.out.println("capacity:" + byteBuffer.capacity());
        System.out.println("get int from mark after put new int value= " + byteBuffer.getInt(3));
    }

    //重置所有索引位置，position=0，mark=-1,limit = capacity,缓存区的数据不清空
    public static void clear() {
        System.out.println("==== 执行clear操作，开启写模式 ====");
        byteBuffer.clear();
        System.out.println("position:" + byteBuffer.position());
        System.out.println("limit:" + byteBuffer.limit());
        System.out.println("capacity:" + byteBuffer.capacity());
        System.out.println("get int from mark = " + byteBuffer.getInt(3));
    }

    //压缩,移动数据，之后position的位置就是limit-position
    public static void testCompact() {
        System.out.println("==== 执行compact操作====");

        byteBuffer.flip();//开启读模式，如果不开启读模式，System.arraycopy(hb, ix(position()), hb, ix(0), remaining());position就是在写的位置，开始copy
        byteBuffer.compact();//移动数据，position=limit-position,limit=capacity,
        System.out.println("position:" + byteBuffer.position());
        System.out.println("limit:" + byteBuffer.limit());
        System.out.println("capacity:" + byteBuffer.capacity());
        byteBuffer.flip();
        System.out.println("get int from mark = " + byteBuffer.getInt(3));
    }

    public static void bufferToChannel() throws Exception {

        RandomAccessFile raf = new RandomAccessFile("b.txt", "rw");

        final FileChannel channel = raf.getChannel();

        final ByteBuffer allocate = ByteBuffer.allocate(1024);

        //写模式
        allocate.clear();

        allocate.put("hello word".getBytes());
        allocate.put("hello word".getBytes());

        //读模式
        allocate.flip();

        while (allocate.hasRemaining()) {
            channel.write(allocate);
        }
        channel.close();
    }

    public static void bufferToBuffer() {
        final ByteBuffer buffer = ByteBuffer.allocate(10);

        System.out.println("=======初始写模式===========");
        System.out.println("position===>" + buffer.position());
        System.out.println("limit===>" + buffer.limit());
        System.out.println("capactiy==>" + buffer.capacity());

        System.out.println("=======写入值===========");
        buffer.put((byte) 127);
        buffer.put((byte) 22);
        buffer.put((byte) 33);
        System.out.println("position===>" + buffer.position());
        System.out.println("limit===>" + buffer.limit());
        System.out.println("capactiy==>" + buffer.capacity());
        System.out.println();

        System.out.println("=======读模式===========");
        buffer.flip();

        System.out.println("position===>" + buffer.position());
        System.out.println("limit===>" + buffer.limit());
        System.out.println("capactiy==>" + buffer.capacity());
        System.out.println("=======读取第1个===========");
//        System.out.println(buffer.get(0));
        System.out.println(buffer.get());
        System.out.println("position===>" + buffer.position());
        System.out.println("limit===>" + buffer.limit());
        System.out.println("capactiy==>" + buffer.capacity());
        System.out.println("=======读取第2个===========");
//        System.out.println(buffer.get(1));//如果通过索引获取，position 一直为0
        System.out.println(buffer.get());//如果直接调用get()方法position返回下一元素获取的所有
        System.out.println("position===>" + buffer.position());
        System.out.println("limit===>" + buffer.limit());
        System.out.println("capactiy==>" + buffer.capacity());

        //切换成写
//        buffer.clear();
        //如果通过索引获取数据会copy的position是0，如果是通过get()获取position是下一个元素的位置获取,并且把position设置limit-position
        buffer.compact();
        System.out.println("=======clear===========");
        System.out.println("position===>" + buffer.position());
        System.out.println("limit===>" + buffer.limit());
        System.out.println("capactiy==>" + buffer.capacity());

        System.out.println("=======调用clear 添加数据覆盖以前的数据===========");
        buffer.put((byte) 10);
        System.out.println(buffer.get(0));
        System.out.println(buffer.get(1));
        System.out.println(buffer.get(2));
        System.out.println("position===>" + buffer.position());
        System.out.println("limit===>" + buffer.limit());
        System.out.println("capactiy==>" + buffer.capacity());

        buffer.flip();//切换读数据
        System.out.println("=======读模式===========");
        System.out.println("position===>" + buffer.position());
        System.out.println("limit===>" + buffer.limit());
        System.out.println("capactiy==>" + buffer.capacity());

        System.out.println(buffer.get(0));
        //数组越界，因为上面写的position 的0，切换成读模式，limit的限制为0
        System.out.println(buffer.get(1));
//        1,写模式写入127,22,33 字节，position 2,limit 10,capacity 10 存储数组[127,22,33]
//        2,切换成读模式flip() get(0),get(1),get(2) position 2,limit 2,capacity 10  存储数组[127,22,33】
//        3,在切换到写模式clear(),put((byte) 10),position 0 ,limit 10 capacity 10 存储数组【10，22，33】
//        4,切换到读数据flip(),buffer.get(0)，buffer.get(1) 报错indexboundException, position 0 ,limit 0 capacity 10
//        5,如果在切换成写模式为compact()


    }

    public static void channelToBuffer() {
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(new File("a.txt"), "rw");
            final FileChannel channel = raf.getChannel();
            // create buffer with capactiy of 48
            final ByteBuffer buffer = ByteBuffer.allocate(48);
            // read into buffer
            int read = channel.read(buffer);

            while (read != -1) {
                buffer.flip();//把写模式切换成读取模式
                while (buffer.hasRemaining()) {
                    System.out.println((char) buffer.get());//读取一个字节
                }
                buffer.clear();//把读模式切换成写模式
                read = channel.read(buffer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (raf != null) {
                try {
                    raf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
