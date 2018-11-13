package com.huntkey.rx

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by zhangyu1 on 2017/6/6.
  */
object WordCountTest {
  def main(args: Array[String]) {

    // 待读文件的位置(本地位置)
    //    val inputFile = "file:///usr/hdp/2.6.0.3-8/spark2/mycode/wordcount/file/word.txt"

    // 待读文件的位置(hdfs文件系统)
    val inputFile = "hdfs://192.168.13.31/user/root/word.txt"

    //    val inputFile ="E:\\work\\word.txt";

    // 设置SparkContext对象
    //    val conf = new SparkConf().setAppName("WordCount").setMaster("spark://vm-centeros01.hkgp.net:7077").setJars(List("E:\\work\\idea\\spark\\spark-demo\\out\\artifacts\\spark_demo_jar\\spark-demo.jar"))
    val conf = new SparkConf().setAppName("WordCount").setMaster("local")
    val sc = new SparkContext(conf)

    // 读取文件
    val textFile = sc.textFile(inputFile)
    //     val textFile = sc.parallelize(("a", "b"))
    print(textFile.isInstanceOf[String])

    val wordCount = textFile.flatMap(line => line.split(" ")).map(word => (word, 1)).reduceByKey((a, b) => a + b)

    // 运行 打印结果
    wordCount.foreach(println)

  }
}
