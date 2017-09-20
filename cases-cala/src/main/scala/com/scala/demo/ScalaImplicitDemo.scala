package com.scala.demo

import java.io.File

/**
  * Created with IntelliJ IDEA.
  *
  * @desc
  * @author lgfcxx
  * @createtime 2017/9/13 下午6:18
  * @version 1.0
  * @see jdk 1.7
  **/

/**
  *1.将方法或变量标记为implicit
  *2.将方法的参数列表标记为implicit
  *3.将类标记为implicit
  * *
  * Scala支持两种形式的隐式转换：
  * 隐式值：用于给方法提供参数
  * 隐式视图：用于类型间转换或使针对某类型的方法能调用成功
  */
class Main(var name: String) {}

object Main {
  //隐式转换 方法
  //  implicit def man2SuperMain(main: Main) = new SuperMain(main.name);
}

class SuperMain(val name: String) {
  def sayHello = println(this.name + " Wow,wow,wo")
}

class RichFile(val file: File) {
  def show = println("23243")

  def bigger[T](a: T, b: T)(implicit ordered: T => Ordered[T]) = {
    if (a > b) a else b
  }
  println(bigger(1,2))
}

//隐式转换类
object implicits {
  implicit def man2SuperMain(main: Main) = new SuperMain(main.name);

  implicit def intToString(i: Int) = i.toString

  //这里声明2个互相转换的隐式转换
  implicit def file2RichFile(file: File) = new RichFile(file);

  implicit def richFile2File(richFile: RichFile) = richFile.file;

}

object ScalaImplicitDemo {
  try{
    1/0
  }catch {
    case e:Exception => println(e.getMessage)
  }
  def main(args: Array[String]): Unit = {

    //导入隐式对象
    import com.scala.demo.implicits._

    // Main 对象里面没有sayHello 方法，通过隐式转换，Main 对象里面有了 sayHello
    val main = new Main("Scala");
    main.sayHello

    implicit val s = "Wow,wow,wow";
    //这里的隐式值且类型相同只能有一个，不然匹配会出错
    implicit val s1 = 12;
    talk("Scala");
    //方法参数隐私转换
    foo("zhansan")
    foo(20)

    val file = new File(".")
    file.show

  }

  //隐私转换参数 ，如果content 不传，会在上下文查找
  def talk(name: String)(implicit content: String) = println(name + " = " + content);

  def foo(name: String) = println("Scala=" + name)

}

//隐式类，构造函数必须有参数，且只能有一个
//隐式类必须被定义在类，伴生对象和包对象里
//隐式类不能是case class（case class在定义会自动生成伴生对象与2矛盾）
//作用域内不能有与之相同名称的标示符
//隐式声明 占位符 没有 导入全路径优先级高， 但是全路径没当前穿入值高
object Hello {

  implicit class StringImprovement(val name: String) {
    def incrment = name.map { name => (name + 1).toChar }
  }

}

object Animal {
  def main(args: Array[String]): Unit = {
    import com.scala.demo.Hello._;

    println("test".incrment)
  }
}

/**
  * 隐式转换的时机：
  *1.当方法中的参数的类型与目标类型不一致时
  *2.当对象调用类中不存在的方法或成员时，编译器会自动将对象进行隐式转换
  */