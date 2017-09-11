package com.scala.demo

import scala.util.Sorting


/**
  * Created with IntelliJ IDEA.
  *
  * @desc
  * @author lgfcxx
  * @createtime 2017/9/9 上午11:48
  * @version 1.0
  * @see jdk 1.7
  **/
object ArrrayOps {
  // 数组操作 Array 数组是固定大小的数组，是不可变，scala 的数组底层是java 实现的
  // 使用可变数组，ArrayBuffer,也可以通过toArray,变成不变数组，但是内容是可以变的，在多线程情况下需要考虑，线程安全问题
  // 数组的创建，经典的数组的初始化 val array = new Array[Int](5) 数组里面存储整数类型的值，并且长度为5
  def main(args: Array[String]): Unit = {
    //声明数组对象
    val array = new Array[Int](5);
    //给数组对象赋值
    array(0) = 10;
    //声明并且赋值，类型为Int
    val array1 = Array[Int](1, 2, 3, 5, 6);
    //scala 推断数组里面的类型
    val arrray2 = Array(1, 2, 3, 4, 5, 6);
    //通过Array 对象的工厂方式初始化值
    val array3 = Array.apply(1, 2, 3, 4, 5);

    //打印数组
    for (arr <- array) {
      println(arr)
    }

    import scala.collection.mutable.ArrayBuffer;
    val arrayBuffer = new ArrayBuffer[Int]();
    arrayBuffer += 1;
    arrayBuffer += 2;
    arrayBuffer += 3;
    //添加集合对象，ArrayBuffer 添加对象都是添加对象的末尾，速度非常快
    arrayBuffer ++= Array(4, 5, 6, 7);
    //如果需要添加指定位置
    arrayBuffer.insert(arrayBuffer.length - 1, 100);
    for (ab <- arrayBuffer) {
      println(ab);
    }

    //删除元素
    arrayBuffer.remove(arrayBuffer.length - 1);
    for (ab <- arrayBuffer) {
      println(ab);
    }
    //通过yield 存储值
    val newArray = for (ab <- arrayBuffer) yield ab + 1;

    for (elem <- newArray) {
      println(elem)
    }

    //数组分割打印
    println(newArray.mkString(","));
    //这个人方法说前面和后面分别追加，中间参数通过逗号隔开
    println(newArray.mkString("***", ",", "**"))

    //数据排序
    Sorting.quickSort(array1);
    for (ar1 <- array1) println(ar1)

    println("max", array1.max);
    println("min", array1.min);
    println("sum", array1.sum);

    //until 不包括本身 例如 0- 5 打印的只有0-4
    for (i <- 0 until array1.length) {
      print(i, " ");
    }
    println();
    //反序列循环
    for (i <- (0 until array1.length).reverse) {
      print(i);
    }
    println();
    //数组算子操作，例如filter ,map
    val even = array1.filter(p => p % 2 == 0); //偶数
    println(even.mkString(","))
    //只有一个元素，可以通过 下划线来站位 _  实现简写
    val e1 = array1.filter(_ % 2 == 0);
    //结果集在乘以2，返回新的数组
    println(e1.map(_*2).mkString(","));
  }
}
