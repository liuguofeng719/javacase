package com.scala.demo

/**
  * Created with IntelliJ IDEA.
  *
  * @desc 函数式编程
  *       函数可以赋值给变量，变量可以赋值给函数，应为函数的背后就是对象和类，背后的类是通过Scala 语音自动生成的，且天然的被序列化和反序列化，
  *       1，序列化和发序列可以在分布式上面传递
  *       2，函数式类和变量，就可以通过参数 传递或者赋值 或者作为返回值
  *       函数的初级入门
  *       1，函数通过def 定义
  *       2，函数如果没有写返回值，def hello={} 默认会推断类型，如果没有等号，则类型推断失效，函数的类型为Unit
  *       3，函数可以通过参数传递，
  *       4, 如果无法推断出类型，必须声明返回值
  *       5, 函数的参数可以调整顺序，可以通过函数参数的名字赋值
  * @author lgfcxx
  * @createtime 2017/9/8 下午2:35
  * @version 1.0
  * @see jdk 1.7
  **/
object FunctionPrograming {

  def main(args: Array[String]): Unit = {
    hello("Spark", 30)
    println(hello("Spark", 30))

    // 通过函数的参数名字赋值
    println(hello(age = 30, name = "zhangs"))

    println("fibonacci of 20 " + fibonacci(20));

    println("Sum = " + sum(1,2,3,4,5,6,7,8,9,10));
    //简写操作 :_* 可变参数收集成Array
    println("Sum = " + sum(1 to 10: _*));
  }

  //使用参数默认值
  def hello(name: String, age: Int = 20) = {
    println("I am " + name + " 现在" + age + "岁");
    age
  }

  //斐波那契数列
  def fibonacci(n: Long): Long = {
    if (n <= 1) 1
    else fibonacci(n - 2) + fibonacci(n - 1);
  }

  //可变参数
  def sum(nums: Int*): Int = {
    var result = 0;
    for(num <- nums) result = result + num;
    result
  }

  //递归操作不能推断，需要指定返回值
  def sumRecursively(numbers: Int*): Int = {
    if (0 == numbers.length) {
      0
    } else {
      //tail 是返回除列表第一个元素之后的列表
      numbers.head + sum(numbers.tail: _*)
    }
  }
}
