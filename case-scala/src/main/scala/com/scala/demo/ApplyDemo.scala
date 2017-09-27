package com.scala.demo

/**
  * Created with IntelliJ IDEA.
  *
  * @desc
  * @author lgfcxx
  * @createtime 2017/9/15 上午10:47
  * @version 1.0
  * @see jdk 1.7
  **/
class ApplyDemo(val userName: String, val birth: String) {

  private val age: Int = 20;
  val year: Int = 10;
  var address: String = "成都市";
  var name: String = _;

  //在主构造函数里面执行
  println("this is primary main constructor")
  println(name) //在主构造函数里面执行

  //public void apply(); 对应
  def apply() = {
    println("class hello spark")
    "rert"
  }

  // public void sayHello();
  def sayHello = {
    println("wewe")
  }

  //通过val声明的变量只会生成get方法，而且生成的变量还是 private final String name;
  //通过var声明的变量会生成get/set 方法 字段生成private String year
  def this(name: String) = {
    //    this //相当于 java 默认构造函数 ApplyDemo()
    this(name, "2017") //构造函数重载//调用ApplyDemo(val userName:String,val birth:String)  初始化构造函数
    this.name = name;
  }
}

//object 字节码编译生成的
/** public final class com.scala.demo.ApplyDemo$ {
  * public static final com.scala.demo.ApplyDemo$ MODULE$;
  * public static {};//静态块
  * public com.scala.demo.ApplyDemo apply(); //返回类的对象的引用
  * public void main(java.lang.String[]);
  * private com.scala.demo.ApplyDemo$(); //私有构造函数
  * }
  */
object ApplyDemo {

  try {
    1 / 0
  } catch {
    case e: Exception => println("Exception")
  }

  def apply() = {
    println("object hello spark")
    new ApplyDemo("test", "2017")
  }

  def main(args: Array[String]): Unit = {
    val demo = ApplyDemo()
    demo.sayHello
    println(demo.userName + "= " + demo.birth)
    demo()
  }
}

//public abstract class com.scala.demo.SuperTeacher {
//private final java.lang.String name;
//public java.lang.String name();
//public abstract int age();
//public abstract long id();
//public abstract void id_$eq(long);
//public abstract void teacher();
//public com.scala.demo.SuperTeacher(java.lang.String);
//}
//抽象类
abstract class SuperTeacher(val name: String) {

  //抽象属性
  val age: Int
  var id: Long

  //抽象方法
  def teacher

  def sayHello = {

  }
}

class TeacherMaths(name: String) extends SuperTeacher(name) {
  override val age: Int = 10
  override var id: Long = 20L

  override def teacher: Unit = {

  }
}

trait Loging {
  def log(message: String)

  def log(message: String, format: String) = {

  }
}

class ConcreteLogger extends Loging with Cloneable {

  override def log(message: String) = {

  }

  def increment(x: Int)(y: Int) = x * y
}

case class Order(orderId: String, orderTotal: Long)

//提取器
object Email {
  //组合对象
  def apply(user: String, domain: String) = user + "@" + domain;

  //拆对象
  def unapply(arg: String): Option[(String, String)] = {
    val s = arg split "@"
    if (s.length == 2) Some(s(0), s(1)) else None
  }

  def main(args: Array[String]): Unit = {
    val s = Email.apply("zhangsan", "list")
    println(s);
    val option = Email.unapply("zhang@lisi")
    println(option.get._1)
    println(option.get._2)

    val order = Order("20170920", 2020L)

    val Order(ss, dd) = order
    println(ss + "==" + dd)
  }
}

