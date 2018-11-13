package com.scala.demo

/**
  * Created by zhangyu1 on 2017/6/2.
  */
object TestList {

  def main(args: Array[String]): Unit = {

    val site = "Runoob" :: ("Google" :: ("Baidu" :: Nil))
    val nums = Nil
    println("第一网站是 : " + site.head)
    println("最后一个网站是 : " + site.tail)
    println("查看列表 site 是否为空 : " + site.isEmpty)
    println("查看 nums 是否为空 : " + nums.isEmpty)


  }

}
