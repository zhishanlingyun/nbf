package com.scala.dao

import org.apache.hadoop.hbase.util.Bytes
import org.apache.hadoop.hbase.{TableName, HColumnDescriptor, HTableDescriptor, NamespaceDescriptor}
import org.apache.hadoop.hbase.client._

/**
 * Created by liuli10 on 2016/4/5.
 */
class HbaseDao {

  val namespace = "scala-sp";
  val cfg = HbaseDataSource.getInstance().getCfg();
  val conn = HbaseDataSource.getInstance().getConn();

  def createTable(tableName:String): Unit ={
    val admin  = conn.getAdmin;
    //admin.createNamespace(NamespaceDescriptor.create(namespace).build);
    val table: HTableDescriptor = new HTableDescriptor(TableName.valueOf(tableName));
    table.addFamily(new HColumnDescriptor("ttt"));
    admin.createTable(table);
    println();
  }

  def insert(tableName:String,rowkey:String,familys:List[String],qvmap:Map[String,String]): Unit ={
    val table = conn.getTable(TableName.valueOf(tableName));
    try {
      val put = new Put(Bytes.toBytes(rowkey));
      familys.foreach(family => (
        qvmap.keys.foreach(key => (put.addColumn(Bytes.toBytes(family), Bytes.toBytes(key), Bytes.toBytes(qvmap(key)))))
        ));
      table.put(put);
      println("成功插入一条数据");
    }finally {
      table.close();
    };
  }

  def query(tableName:String,rowkey:String): Result={
    val table = conn.getTable(TableName.valueOf(tableName));
    val get = new Get(Bytes.toBytes(rowkey));
    val result = table.get(get);
    table.close();
    result;
  }

  def delete(tableName:String,rowkey:String): Unit ={
    val table = conn.getTable(TableName.valueOf(tableName));
    val delete = new Delete(Bytes.toBytes(rowkey));
    table.delete(delete);
    println("成功删除一条数据");
  }

}
