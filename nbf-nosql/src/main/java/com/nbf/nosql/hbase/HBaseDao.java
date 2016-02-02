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
        cfg.set("hbase.zookeeper.quorum","192.168.106.132:2181");
        cfg.set("hbase.master", "ubuntu:60000");
    }

    public void createOrOverwrite(Admin admin,HTableDescriptor table) throws IOException{
        if(admin.tableExists(table.getTableName())){
            admin.disableTable(table.getTableName());
            admin.deleteTable(table.getTableName());
        }
        admin.createTable(table);
    }

    public void createTable(){
        try{
            Connection conn = ConnectionFactory.createConnection(cfg);
            Admin admin = conn.getAdmin();
            HTableDescriptor table = new HTableDescriptor(TableName.valueOf("java_table"));
            table.addFamily(new HColumnDescriptor("f1"));
            System.out.println("create table ...");
            this.createOrOverwrite(admin,table);
            System.out.println("Done.");

        }catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args){
        HBaseDao dao = new HBaseDao();
        dao.createTable();
    }


}
