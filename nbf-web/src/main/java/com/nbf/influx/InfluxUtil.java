package com.nbf.influx;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by liuli10 on 2016/3/23.
 */
public class InfluxUtil {

    public static String url = "http://192.168.188.133:8086";
    public static String dbusername = "root";
    public static String dbpassword = "root";

    public static InfluxDB createInfluxDB(String url,String username,String password){
        return InfluxDBFactory.connect(url, username, password);
    }

    public static void createDB(String dbname){
        InfluxDB influxDB = createInfluxDB(url,dbusername,dbpassword);
        influxDB.createDatabase(dbname);
    }

    public static void insert(String dbname,String measurement,Map<String, String> tags,Map<String, Object> fields){
        InfluxDB influxDB = createInfluxDB(url,dbusername,dbpassword);
        Point point = Point.measurement(measurement).tag(tags).fields(fields).build();
        influxDB.write(dbname, "default", point);
        System.out.println("insert "+point);
    }

    public static QueryResult query(String dbname,String sql){
        InfluxDB influxDB = createInfluxDB(url,dbusername,dbpassword);
        Query query = new Query(sql, dbname);
        QueryResult result = influxDB.query(query);
        return result;
    }

    public static void query(){
        InfluxDB influxDB = InfluxDBFactory.connect("http://192.168.188.133:8086", "root", "root");
        //influxDB.createDatabase(dbName);

        /*BatchPoints batchPoints = BatchPoints
                .database(dbName)
                .tag("async", "true")
                .retentionPolicy("default")
                .consistency(InfluxDB.ConsistencyLevel.ALL)
                .build();
        Point point1 = Point.measurement("cpu")
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .field("idle", 90L)
                .field("user", 9L)
                .field("system", 1L)
                .build();
        Point point2 = Point.measurement("disk")
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .field("used", 80L)
                .field("free", 1L)
                .build();
        batchPoints.point(point1);
        batchPoints.point(point2);
        influxDB.write(batchPoints);*/
        Query query = new Query("SELECT idle FROM cpu", "");
        QueryResult result = influxDB.query(query);
        System.out.println(result.getResults());
        //influxDB.deleteDatabase(dbName);
    }

    public static void main(String[] args){
        /*QueryResult result = InfluxUtil.query("testdb","select * from cpu");
        System.out.println(result);*/
        //InfluxUtil.query();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Map<String,String> tags = new HashMap<String, String>();
                Map<String,Object> fields = new HashMap<String, Object>();
                while(true){
                    tags.put("hosts","server"+Math.random());
                    fields.put("value",new Random().nextFloat()*10);
                    InfluxUtil.insert("testdb", "cpu", tags, fields);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    tags.clear();
                    fields.clear();
                }

            }
        }).start();

    }



}
