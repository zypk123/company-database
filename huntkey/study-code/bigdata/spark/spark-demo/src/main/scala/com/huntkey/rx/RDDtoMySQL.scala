package com.huntkey.rx

import java.sql.{DriverManager, PreparedStatement, Connection}
import org.apache.spark.{SparkContext, SparkConf}

/**
  * Created by zhangyu1 on 2017/6/8.
  * spark从hdfs读取数据，并写到MySQL
  *
  */
object RDDtoMySQL {

  /**
    * 插数据到MySQL
    *
    * @param iterator
    */
  def insertMySQL(iterator: Iterator[(String, Int)]): Unit = {
    var conn: Connection = null
    var ps: PreparedStatement = null
    val database = "spark"
    val user = "root"
    val password = "hk123"
    val conn_str = "jdbc:mysql://192.168.13.52:3306/" + database + "?user=" + user + "&password=" + password
    val sql = "INSERT INTO spark (username,number) VALUES (?, ?)"
    try {
      conn = DriverManager.getConnection(conn_str)
      iterator.foreach(data => { // data为saprk从hdfs读取的数据
        ps = conn.prepareStatement(sql)
        ps.setString(1, data._1) //
        ps.setInt(2, data._2)
        ps.executeUpdate()
      })
    } catch {
      case e: Exception => println("Mysql Exception")
    } finally {
      if (ps != null) {
        ps.close()
      }
      if (conn != null) {
        conn.close()
      }
    }
  }

  def main(args: Array[String]): Unit = {
    // 初始化SparkContext对象
    val conf = new SparkConf().setAppName("RDDtoMySQL").setMaster("local")
    val sc = new SparkContext(conf)

    // hdfs文件路径
    val lines = sc.textFile("hdfs://192.168.13.31/user/root/word.txt")

    // RDD操作
    val words = lines.flatMap { line => line.split(" ") } // 每行数据按照空格分割,words同样是RDD类型
    val pairs = words.map { word => (word, 1) } // 转化成keyValue对的格式
    val wordCounts = pairs.reduceByKey(_ + _) // 对相同的key，进行value的累加(包括Local和Reducer级别同时Reduce)
    wordCounts.foreachPartition(insertMySQL)
  }
}
