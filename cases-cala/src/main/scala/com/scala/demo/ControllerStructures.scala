package com.scala.demo

/**
  * Created with IntelliJ IDEA.
  *
  * @desc 控制结构
  * @author lgfcxx
  * @createtime 2017/9/8 上午11:00
  * @version 1.0
  * @see jdk 1.7
  **/
object ControllerStructures {


  def main(args: Array[String]): Unit = {

    println("Scala");

    /**
      * if else 结构体有返回值
      */
    val age = 20;
    val result = if (age > 19) "worker" else "student"; //自动推断为String 类型
    println(result);

    val result1 = if (age > 18) "adult" else 1; //类型推断为Any 类型
    println(result1);

    //如果if没有else推断类型为Any类型 ，没有else的默认结构 if(...) else () 这样的表达式,如果需要返回None if() else { None }，这样为了确认返回值，None 为Option类型
    /**
      * 来源sparkContext 类 535 行
      * _eventLogger = if (isEventLogEnabled) { Some(logger) } else { None }
      */
    /**
      * if{ 多条语句，返回值为最后一条语句 } else { 多条语句，返回值为最后一条语句 } 返回类型也是最后一条值得类型
      */
    val result2 = if (age > 16) "adult" else ();
    println(result2);

    var x, y = 0;
    val result3 = if (age > 18) {
      x = x + 1;
      y = y + 1;
      x + y
    } else 0

    println(result3)

    //跳出for 循环可以通过 if 守卫跳出
    var sum = 0;
    var flag = true;
    for (i <- 0 to 6 if flag) {
      sum = sum + i;
      if (sum == i) flag = false;
    }
    println(sum);

    var sum1 = 0;
    for (i <- 0 to 6) {
      sum1 = sum1 + i;
//      if (sum1 == i) return; //return 返回方法体,所以下面的for 循环不会打印
    }
    println(sum1);

    //推断成char数组
    for (item <- "Hello Spark") {
      println(item)
    }

    //推断成集合
    for (item <- "Hello Spark".split(" ")) {
      println(item)
    }

    import scala.util.control.Breaks._;//这种方式不常用，一般使用守卫方式跳出循环

    //breakable 和 break 必须这样使用
    breakable{
      // while 循环
      while (flag) {
        for (item <- "Spark") {
          println(item);
          if (item == "r") {
            flag = flag
            break
          }
        }
      }
    }
    println("while is finished !!!")
  }
}
