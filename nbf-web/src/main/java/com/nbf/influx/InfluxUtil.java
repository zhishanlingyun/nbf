package com.nbf.influx;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;

import java.util.concurrent.TimeUnit;

/**
 * Created by liuli10 on 2016/3/23.
 */
public class InfluxUtil {

    public static void createDB(String dbname){
        InfluxDB influxDB = InfluxDBFactory.connect("http://192.168.81.137:8086", "root", "root");
        influxDB.createDatabase(dbname);
    }

    public static void insert(String dbname,String sql){

    }

    public static void query(){
        InfluxDB influxDB = InfluxDBFactory.connect("http://192.168.81.137:8086", "root", "root");
        String dbName = "testdb2";
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
        Query query = new Query("SELECT idle FROM cpu", dbName);
        QueryResult result = influxDB.query(query);
        System.out.println(result.getResults());
        //influxDB.deleteDatabase(dbName);
    }

    public static void main(String[] args){
        InfluxUtil.query();
    }



}
