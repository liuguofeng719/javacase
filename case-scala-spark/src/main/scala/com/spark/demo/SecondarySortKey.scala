package com.spark.demo

/**
  * Created with IntelliJ IDEA.
  *
  * @desc
  * @author lgfcxx
  * @createtime 2017/10/8 上午9:17
  * @version 1.0
  * @see jdk 1.7
  **/
class SecondarySortKey(val first: Int, val second: Int) extends Ordered[SecondarySortKey] with Serializable {

  override def compare(that: SecondarySortKey): Int = {
    if (this.first - that.first != 0) {
      this.first - that.first
    } else {
      this.second - that.second
    }
  }
}

object SecondarySortKey {

  def apply(first: Int, second: Int): SecondarySortKey = new SecondarySortKey(first, second)
}
