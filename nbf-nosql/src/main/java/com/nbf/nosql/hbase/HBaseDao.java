package com.nbf.nosql.hbase;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.Closeable;
import java.io.IOException;

/**
 * User: root
 * Date: 1/31/16
 * Time: 11:35 PM
 */
@Component("hBaseDao")
public class HBaseDao {

    private static Logger logger = Logger.getLogger(HBaseDao.class);

    private Configuration cfg;

    public HBaseDao(){
        cfg = HBaseConfiguration.create();
        cfg.set("hbase.zookeeper.quorum","ubuntu:2181");
        cfg.set("hbase.master", "ubuntu:60000");
    }

    public void createOrOverwrite(Admin admin,HTableDescriptor table) throws IOException{
        if(admin.tableExists(table.getTableName())){
            admin.disableTable(table.getTableName());
            admin.deleteTable(table.getTableName());
        }
        admin.createTable(table);
    }

    public void close(Closeable ... closeables){
        try {
            for(Closeable closeable : closeables){
                closeable.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
        }
    }

    public void createTable(String tableName,String... columnFamilys){
        try{
            Connection conn = ConnectionFactory.createConnection(cfg);
            Admin admin = conn.getAdmin();
            HTableDescriptor table = new HTableDescriptor(TableName.valueOf(tableName));
            for(String columnFamily:columnFamilys){
                table.addFamily(new HColumnDescriptor(columnFamily));
            }
            logger.info("create table ...");
            this.createOrOverwrite(admin,table);
            logger.info("Done.");
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addColumnFamily(String tableName,String ... columFamilys){
        try{
            Connection conn = ConnectionFactory.createConnection(cfg);
            Admin admin = conn.getAdmin();
            HTableDescriptor table = admin.getTableDescriptor(TableName.valueOf(tableName));
            for(String columnFamily:columFamilys){
                table.addFamily(new HColumnDescriptor(columnFamily));
            }
            logger.info("create table ...");
            this.createOrOverwrite(admin,table);
            logger.info("Done.");
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void insert(String tablename,byte[] rowkey,byte[] family,byte[] qualifier,byte[] value){
        try {
            //Connection conn = ConnectionFactory.createConnection(cfg);
            HConnectionManager.getConnection()
            Table table = conn.getTable(TableName.valueOf(tablename));
            HTable t = new HTable(cfg,"");
            Put put = new Put(rowkey);
            put.addColumn(family,qualifier,value);
            table.put(put);
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
        } finally {
        }
    }

    public Result getRecord(String tablename,byte[] rowkey,byte[] family,byte[] qualifier ){
        Result result = null;
        Table table = null;
        Connection conn = null;
        try {
            conn = ConnectionFactory.createConnection(cfg);
            table = conn.getTable(TableName.valueOf(tablename));
            Get get = new Get(rowkey);
            result = table.get(get);
            //logger.info(Bytes.toString(result.getValue(family, qualifier)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            close(table,conn);
        }
        return result;
    }

    public static void main(String[] args){
        HBaseDao dao = new HBaseDao();
        //dao.createTable("t1","f1","f2","f3");
        //dao.addColumnFamily("t1","f111","f22");
        dao.insert("t1",Bytes.toBytes("rowkey-3"),Bytes.toBytes("f1"),Bytes.toBytes("content"),Bytes.toBytes("f1-content-value"));
        dao.getRecord("t1",Bytes.toBytes("rowkey-3"),Bytes.toBytes("f1"),Bytes.toBytes("content"));
    }


}
