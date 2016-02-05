package com.nbf.nosql.hadoop.md;

import com.google.common.collect.Collections2;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.*;

/**
 * User: liuli10
 * Date: 16-2-2
 * Time: 下午6:21
 * 1990
 */
public class MD {

    static class MaxTemperatureMap extends Mapper<LongWritable,Text,Text,IntWritable>{
        private static Logger logger = Logger.getLogger(MaxTemperatureMap.class);
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            String[] data = line.split(" ");
            String year = data[0];
            String locale = data[1];
            int temperature = Integer.parseInt(data[2]);
            logger.info("year-locale : "+year+"-"+locale+"\ttemperature :"+temperature);
            context.write(new Text(year+"-"+locale), new IntWritable(temperature));
            //super.map(key, value, context);
        }
    }

    static class MaxTemperatureReduce extends Reducer<Text,IntWritable,Text,IntWritable>{
        private static Logger logger = Logger.getLogger(MaxTemperatureReduce.class);
        @Override
        protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            int max = Integer.MIN_VALUE;

            Iterator<IntWritable> it = values.iterator();
            List<Integer> list = new ArrayList<Integer>();
            int value = 0;
            while(it.hasNext()){
                value = it.next().get();
                list.add(value);
                max = Math.max(max,value);
            }
            logger.info("reduce: key = "+key+" values = "+list);
            logger.info("year : "+key+"\tMax temperature :"+max);
            context.write(new Text(key),new IntWritable(max));
        }
    }

    public static class SortTemperatureMap extends Mapper<LongWritable,Text,Text,Text>{
        private static Logger logger = Logger.getLogger(SortTemperatureMap.class);

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            logger.info("LongWritable-key is "+key);
            String line = value.toString();
            String[] data = line.split(" ");
            String year = data[0];
            String localetemperature = data[1]+"-"+data[2];
            logger.info("year is "+year+"\t value is "+localetemperature);
            context.write(new Text(year),new Text(localetemperature));

        }
    }

    public static class SortTemperatureReduce extends Reducer<Text,Text,Text,Text>{
        private static Logger logger = Logger.getLogger(SortTemperatureReduce.class);

        @Override
        protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            String year = key.toString();
            Iterator<Text> it = values.iterator();
            //List<Integer> list = new ArrayList<Integer>();
            //int value = 0;
            String line = null;
            TreeMap<Integer,String> sortMap = new TreeMap<Integer,String>();
            while(it.hasNext()){
                line = it.next().toString();
                String[] data = line.split("-");
                Integer temperature = Integer.parseInt(data[1]);
                String locale = data[0];
                sortMap.put(temperature,locale);
            }
            List<String> result = new ArrayList<String>();
            Set<Integer> keyset = sortMap.keySet();
            Iterator<Integer> keyIt = keyset.iterator();
            Integer k = null;
            while(keyIt.hasNext()){
               k =  keyIt.next();
               result.add(sortMap.get(k)+"-"+k);
            }
            logger.info("year is "+year+"\t sort is "+result.toString());
            context.write(new Text(year),new Text(result.toString()));

        }
    }


    public static void main(String[] args) throws Exception{

        /*Configuration conf = new Configuration();
        conf.set("mapred.jar", "./test/nosql.jar"); //运行前程序要被打成JAR包
        conf.set("fs.default.name", "hdfs://ubuntu:9000");
        conf.set("mapred.job.tracker", "ubuntu:9001");*/

        Job job = new Job();
        job.setJarByClass(MD.class);
        FileInputFormat.addInputPath(job,new Path("hdfs://ubuntu:9000/test/temperature.txt"));
        FileOutputFormat.setOutputPath(job,new Path("hdfs://ubuntu:9000/test/out2"));
        //job.setMapperClass(MaxTemperatureMap.class);
        //job.setReducerClass(MaxTemperatureReduce.class);
        job.setMapperClass(SortTemperatureMap.class);
        job.setReducerClass(SortTemperatureReduce.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        System.exit(job.waitForCompletion(true)?0:1);

    }

}
