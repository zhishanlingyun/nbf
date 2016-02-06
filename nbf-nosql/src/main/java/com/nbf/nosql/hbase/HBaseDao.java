package com.nbf.nosql.hbase;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.io.compress.Compression;

import java.io.IOException;

/**
 * User: root
 * Date: 1/31/16
 * Time: 11:35 PM
 */
public class HBaseDao {

    private Configuration cfg;

    private HTable table;

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

    public void createTable(String tableName,String... columnFamilys){
        try{
            Connection conn = ConnectionFactory.createConnection(cfg);
            Admin admin = conn.getAdmin();
            HTableDescriptor table = new HTableDescriptor(TableName.valueOf(tableName));
            for(String columnFamily:columnFamilys){
                table.addFamily(new HColumnDescriptor(columnFamily));
            }
            System.out.println("create table ...");
            this.createOrOverwrite(admin,table);
            System.out.println("Done.");

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addColumnFamily(String tableName,String ... columFamilys){
        try{
            Connection conn = ConnectionFactory.createConnection(cfg);
            Admin admin = conn.getAdmin();
            HTableDescriptor table = admin.getTableDescriptor(TableName.valueOf(tableName));
            //HTableDescriptor table = conn.getTable(TableName.valueOf(tableName));
            for(String columnFamily:columFamilys){
                table.addFamily(new HColumnDescriptor(columnFamily));
            }
            System.out.println("create table ...");
            this.createOrOverwrite(admin,table);
            System.out.println("Done.");

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        HBaseDao dao = new HBaseDao();
        //dao.createTable("t1","f1","f2","f3");
        dao.addColumnFamily("t1","f111","f22");
    }


}
