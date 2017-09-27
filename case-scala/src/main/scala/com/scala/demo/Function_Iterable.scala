package com.scala.demo

import scala.collection.mutable


/**
  * Created with IntelliJ IDEA.
  *
  * @desc
  * @author lgfcxx
  * @createtime 2017/9/11 上午10:04
  * @version 1.0
  * @see jdk 1.7
  **/
object Function_Iterable {

  def main(args: Array[String]): Unit = {

    //集合分为可变和不可变集合
    //scala.collection.immutable 不可变 scala.collection.mutable 可变
    val range = 0 to 10;
    val list = List(1, 2, 3, 4, 5, 6)
    println(list.head) //获取第一个元素 1
    println(list.tail) //除第一个元素，剩下的元素  List(2, 3, 4, 5, 6)
    // 使用:: 操作符 把元素组拼新的集合,把元素添加到最前面
    println(0 :: list)
    //List(0, 1, 2, 3, 4, 5, 6)
    //如果List 为空返回Nill
    val list1 = List()

    //可变集合
    var linkList = scala.collection.mutable.LinkedList(1, 2, 3, 4, 5);
    println(linkList.elem) //返回第1个元素
    println(linkList.tail) // 返回除第一个元素，之后的元素 LinkedList(2, 3, 4, 5)

    while (linkList != Nil) {
      println(linkList.elem);
      linkList = linkList.tail;
    }

    println(linkList)

    //这里的修改不是在以前的元素修改，而是重新生成信的元素返回
    var copyLinkedList = linkList.+:(9)
    //添加元素
    var copyLinkedList2 = copyLinkedList.:+(0);
    println(copyLinkedList)
    println(copyLinkedList2)

    //元素不可重复,且是无序的 HashSet不可变不可重复，LinkedHashSet 可维护插入的顺序
    val set = Set(1, 2, 3);
    println(set);
    //Set(1, 2, 3)
    val set1 = set + 4; //增加元素
    println(set1)
    //Set(1, 2, 3, 4)
    val set2 = set + 1; //增加元素
    println(set2) //Set(1, 2, 3) 不能插入重复的元素

    // 无序并且不能重复
    val hashSet = mutable.HashSet(2, 3, 4, 5)
    hashSet += 2;
    hashSet += 5;
    hashSet += 15;
    println(hashSet) // Set(15, 5, 2, 3, 4)

    //保证数据的插入顺序，但是不能重复
    val linkedHashSet = mutable.LinkedHashSet(2, 3, 4)
    linkedHashSet += 2;
    linkedHashSet += 22;
    linkedHashSet += 32;
    println(linkedHashSet) //Set(2, 3, 4, 22, 32)

    //排序 但是不能重复
    val sortedSet = mutable.SortedSet(5, 2, 6, 1);
    sortedSet += 5;
    println(sortedSet) //TreeSet(1, 2, 5, 6)

    val listString = List[String]("I am into spark so much", "Scala is powerful")
    // flatMap 就是把每个元素，分割成单个集合，在把多个集合，合并成一个集合
    val flatMap = listString.flatMap { s => s.split(" ") };
    println(listString.flatMap { s => s.split(" ") }); // List(I, am, into, spark, so, much, Scala, is, powerful)

    val listTuple = flatMap.map { s => (s, 1) };
    println(flatMap.map { s => (s, 1) }) //返回集合元组 List((I,1), (am,1), (into,1), (spark,1), (so,1), (much,1), (Scala,1), (is,1), (powerful,1))

    println(listTuple.map { s => s._2 }); //这里返回单词出现的次数 List(1, 1, 1, 1, 1, 1, 1, 1, 1)
    // reduce 表示上2个数相加的和在与下一个数相加
    println(listTuple.map { s => s._2 }.reduce { (x, y) => x + y }); //9 打印出总的是9个单词

    //占位符操作_ 只有一个元素操作
    //flatMap.map { s => (s, 1) }
    //这里表示每次循环只是输入一个参数可以用占位符代替,这里的reduce 应为每次相加之后，输入的也是2个相加的和，所以也是一个参数
    flatMap.map((_, 1)).map(_._2).reduce(_ + _);
  }
}
