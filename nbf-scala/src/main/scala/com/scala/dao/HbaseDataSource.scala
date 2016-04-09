package com.scala.dao

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.hadoop.hbase.client.{Connection, ConnectionFactory}

/**
 * Created by liuli10 on 2016/4/5.
 */
class HbaseDataSource private{

  val cfg = HBaseConfiguration.create();
  cfg.set("hbase.zookeeper.quorum", "ubuntu:2181");
  cfg.set("hbase.master", "ubuntu:60000");

  val conn = ConnectionFactory.createConnection(cfg);

  def getCfg():Configuration={
    cfg;
  }

  def getConn():Connection = {
    conn;
  }

  def close(): Unit ={
    if(null!=conn){
      conn.close();
    }
  }

}

object HbaseDataSource {
  val cache = Map[String,HbaseDataSource]("d0" -> new HbaseDataSource)

  def getInstance():HbaseDataSource={
    cache("d0");
  }
}