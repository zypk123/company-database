package com.scala.demo

/**
  * Created by zhangyu1 on 2017/6/2.
  */
object TestFunction {

  def add(a: Int, b: Int): Int = {
    var sum: Int = 0
    sum = a + b
    return sum
  }

  def main(args: Array[String]): Unit = {
    var sum: Int = add(10, 20)
    println(sum)
  }


}
