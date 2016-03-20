package com.nbf.nosql.hbase;


import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.pool2.impl.GenericKeyedObjectPool;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.Closeable;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * User: root
 * Date: 1/31/16
 * Time: 11:35 PM
 */
@Component("hBaseDao")
public class HBaseDao {

    private static Logger logger = Logger.getLogger(HBaseDao.class);

    private GenericKeyedObjectPool<Configuration,Connection> pool;

    private Configuration cfg;

    private Connection conn;

    public HBaseDao(){
        //System.setProperty("hadoop.home.dir", "E:\\linux-tool\\hadoop-2.7.1");
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

    public void createTableWithNameSpace(String nameSpace,String tableName,String... columnFamilys){
        try{
            conn = pool.borrowObject(cfg);
            Admin admin = conn.getAdmin();
            if(StringUtils.isNotBlank(nameSpace)){
                admin.createNamespace(NamespaceDescriptor.create(nameSpace).build());
            }
            HTableDescriptor table = new HTableDescriptor(TableName.valueOf(tableName));
            for(String columnFamily:columnFamilys){
                table.addFamily(new HColumnDescriptor(columnFamily));
            }
            System.out.println("create table ...");
            this.createOrOverwrite(admin, table);
            System.out.println("Done.");

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            pool.returnObject(cfg,conn);
        }
    }

    public void createTable(String tableName,String... columnFamilys){
        createTableWithNameSpace(null,tableName,columnFamilys);
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
            this.createOrOverwrite(admin, table);
            logger.info("Done.");
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void insert(String tablename,byte[] rowkey,byte[] family,byte[] qualifier,byte[] value){
        try {
            conn = pool.borrowObject(cfg);
            Table table = conn.getTable(TableName.valueOf(tablename));
            Put put = new Put(rowkey);
            put.addColumn(family,qualifier,value);
            table.put(put);
            logger.info("插入数据成功");
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        } finally {
            pool.returnObject(cfg,conn);
        }
    }

    public void insert(String tablename,String rowkey,String family, Map<String /*qualifier*/,String /*value*/ > qvmap){
        try {
            conn = pool.borrowObject(cfg);
            Table table = conn.getTable(TableName.valueOf(tablename));
            Put put = new Put(Bytes.toBytes(rowkey));
            if(null!=qvmap&&!qvmap.isEmpty()){
                Iterator<String> qualifiers = qvmap.keySet().iterator();
                String qualifier = null;
                String value= null;
                while(qualifiers.hasNext()){
                    qualifier = qualifiers.next();
                    value = qvmap.get(qualifier);
                    put.addColumn(Bytes.toBytes(family),Bytes.toBytes(qualifier),Bytes.toBytes(value));
                }
            }
            table.put(put);
            logger.info("插入数据成功");
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        } finally {
            pool.returnObject(cfg,conn);
        }
    }

    public void insert(String tablename,String rowkey,Map<String/*family*/,Map<String/*qualifier*/,String/*value*/>> fqvmap){
        if(null!=fqvmap&&!fqvmap.isEmpty()){
            Iterator<String> familes = fqvmap.keySet().iterator();
            String family = null;
            Map<String,String> qvmap = null;
            Put put = new Put(Bytes.toBytes(rowkey));
            while(familes.hasNext()){
                family = familes.next();
                qvmap = fqvmap.get(family);
                if(null!=qvmap&&!qvmap.isEmpty()){
                    Iterator<String> qualifiers = qvmap.keySet().iterator();
                    String qualifier = null;
                    String value= null;
                    while(qualifiers.hasNext()){
                        qualifier = qualifiers.next();
                        value = qvmap.get(qualifier);
                        put.addColumn(Bytes.toBytes(family),Bytes.toBytes(qualifier),Bytes.toBytes(value));
                    }
                }
            }
            try {
                conn = pool.borrowObject(cfg);
                Table table = conn.getTable(TableName.valueOf(tablename));
                table.put(put);
                logger.info("插入数据成功");
            } catch (Exception e) {
                logger.error(e.getMessage(),e);
            } finally {
                pool.returnObject(cfg,conn);
            }
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
        /*dao.createTable("table1","f1","f2","f3");
        dao.createTable("table2","f1","f2","f3");
        dao.createTable("table3","f1","f2","f3");*/
        dao.createTableWithNameSpace("mywork","table1","f1","f2","f3");
        final AtomicInteger count = new AtomicInteger(0);

        /*for (int i=0;i<10;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i=0;i<=5;i++){
                        dao.createTable("table-"+Thread.currentThread().getName()+"-"+i+"-"+count.incrementAndGet(),"f1","f2","f3");
                    }
                }
            }).start();
        }*/
        //dao.addColumnFamily("t1","f111","f22");
    }


}
