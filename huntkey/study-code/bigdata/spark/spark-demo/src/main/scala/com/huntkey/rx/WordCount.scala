package com.huntkey.rx

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by zhangyu1 on 2017/6/5.
  */
object WordCount {
  def main(args: Array[String]) {

    // 获取SparkContext对象
    val conf = new SparkConf().setAppName("WordCount")
    val sc = new SparkContext(conf)

    val input = sc.textFile("file:///home/root/soft/helloSpark.txt")
    val lines = input.flatMap(line => line.split(" "))
    val count = lines.map(word => (word, 1)).reduceByKey((a, b) => a + b)

    val output = count.saveAsTextFile("file:///home/root/soft/helloSparkRes")

    count.foreach(println)

  }
}
