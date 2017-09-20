package com.scala.demo

import akka.actor.{Actor, ActorSystem, Props};

/**
  * Created with IntelliJ IDEA.
  *
  * @desc
  * @author lgfcxx
  * @createtime 2017/9/14 下午3:44
  * @version 1.0
  * @see jdk 1.7
  **/
object ScalaActor {

  def main(args: Array[String]): Unit = {

  }
}

class SendActor extends Actor {
  override def receive: Receive = {
    case content: String => printf(content)
  }
}
