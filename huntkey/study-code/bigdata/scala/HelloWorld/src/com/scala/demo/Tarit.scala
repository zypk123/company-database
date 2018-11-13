package com.scala.demo

/**
  * Created by zhangyu1 on 2017/6/2.
  */
trait Tarit {

  def isEqual(x: Any): Boolean

  def isNotEqual(x: Any): Boolean = !isEqual(x)

}
