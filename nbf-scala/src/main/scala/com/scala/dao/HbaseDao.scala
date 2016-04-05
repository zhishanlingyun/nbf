package com.scala.dao

import _root_.promo.analysis.utils.config.HbaseFields
import org.apache.hadoop.hbase.{TableName, HColumnDescriptor, HTableDescriptor, NamespaceDescriptor}
import org.apache.hadoop.hbase.client.{ConnectionFactory, Connection}

/**
 * Created by liuli10 on 2016/4/5.
 */
class HbaseDao {

  val namespace = "scala";
  val cfg = HbaseDataSource.getInstance().getCfg();
  val conn = HbaseDataSource.getInstance().getConn();

  def createTable(tableName:String): Unit ={
    val admin  = conn.getAdmin;
    admin.createNamespace(NamespaceDescriptor.create(namespace).build);
    val table: HTableDescriptor = new HTableDescriptor(TableName.valueOf(tableName));
    table.addFamily(new HColumnDescriptor(""));
    admin.createTable(table);
  }

}
