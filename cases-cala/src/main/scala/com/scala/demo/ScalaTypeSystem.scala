package com.scala.demo

/**
  * Created with IntelliJ IDEA.
  *
  * @desc
  * @author lgfcxx
  * @createtime 2017/9/11 上午11:34
  * @version 1.0
  * @see jdk 1.7
  **/

// 上边界 scala 限定操作符 T <: A 如果在类限定，T 只能是A的子类或者A本身 和java 里面的 <? extends T> 限定符一样
// 下边界限定符 T >: A T 要么是 A 要么就是A的父类和A的子类， 和java <? super T> 有些不同，java 只能是自己或者T的子类
// View Binds 是上边界和下边界补充版 操作符 <%  例如：T <% Writable 但是T必须Writable 类型的，但是T 没有继承 Writable 这个时候必须通过隐式转换 implicit 关键字
// RDD[T: ClassTag]  T: ClassTag 是通过jvm运行期才能确定的类型，因为Spark 运行和编程区分Driver和Excutor 只有在运行期才能知道完整的信息
// 逆变和协变: -T 和 +T 逆变：子类能去的，父类也能去，协变：父类能去的，子类也能去
// Context Bound T:Ordering 意思就是这种 Ordering[T]

class Engnineer

class Expert extends Engnineer

class Meeting[-T]

class MeetingA[+T]

class Animail[T](val s: T) {
  def getAnimal(sp: T): T = s;
}

class Person(val name: String) {
  def talk(person: Person) = {
    println(this.name + " : " + person.name);
  }
}

class Worker(name: String) extends Person(name)

class Swing(name: String) extends Worker(name)

class Computer[T <: Person](p1: T, p2: T) {
  def cp = p1.talk(p2)
}

//下界限制
class Com[T >: Swing](p1: T, p2: T) {

}

class DogTake[T <% Person](p1: T, p2: T) {
  def cp = p1.talk(p2)
}

class Dog(var name: String)

// T: Ordering 通过隐式转换成 Ordering[T]
class Maxmin[T: Ordering](val x: T, val y: T) {
  def bigger(implicit ord: Ordering[T]) = {
    if (ord.compare(x, y) > 0) {
      x
    } else {
      y
    }
  }
}

object ScalaTypeSystem {
  def main(args: Array[String]): Unit = {
    /** 隐式转换，
      *1.将方法或变量标记为implicit
      *2.将方法的参数列表标记为implicit
      *3.将类标记为implicit
      */
    implicit def dog2Person(dog: Dog) = new Person(dog.name)

    val w = new Worker("Spark");
    val p = new Person("Person");
    val d = new Dog("Dog");

    //上界
    new Computer(p, w).cp;

    //View Binds
    new DogTake[Person](p, d).cp;

    val meeting = new Meeting[Engnineer]
    partMeeting(meeting);

    //逆变 -T 子类能去，父类也能去
    val meeting1 = new Meeting[Engnineer]
    partMeeting(meeting1);

    //协变 +T 父类能去，子类也能去
    val meeting2 = new MeetingA[Expert]
    partMeeting1(meeting2);

    val s = new Swing("Swing")
    //下界
    new Com[Person](p, w);

    println(new Maxmin(2,5).bigger);

  }

  //逆变
  def partMeeting(meet: Meeting[Expert]): Unit = {
    println("well come");
  }

  //协变
  def partMeeting1(meet: MeetingA[Engnineer]): Unit = {
    println("well come");
  }
}
