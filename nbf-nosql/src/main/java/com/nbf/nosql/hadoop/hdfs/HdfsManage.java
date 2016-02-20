package com.nbf.nosql.hadoop.hdfs;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.URI;
import java.net.URL;

/**
 * User: Administrator
 * Date: 16-2-8
 * Time: 下午1:45
 */
public class HdfsManage {
    private static Logger logger = Logger.getLogger(HdfsManage.class);
    private Configuration configuration;

    public void init(){
        configuration = new Configuration();
        //configuration.addResource("");
        configuration.set("fs.defaultFS","hdfs://ubuntu:9000");
        configuration.set("dfs.support.append","true");
        configuration.set("dfs.replication","1");
       /* configuration.set("dfs.client.block.write.replace-datanode-on-failure.policy",
                "NEVER"
        );
        configuration.set("dfs.client.block.write.replace-datanode-on-failure.enable",
                "true"
        );*/
    }

    public HdfsManage(){
        init();
    }

    public boolean mkdir(String folder){
        boolean success = false;
        try {
            FileSystem fs = FileSystem.get(URI.create(folder),configuration);
            if(!fs.exists(new Path(folder))){
                success = fs.mkdirs(new Path(folder));
            }
        } catch (IOException e) {
            logger.error("hdfs mkdir fail,reason is "+e.getMessage());
        } finally {
            logger.info("hdfs mkdir statu is "+success);
        }
        return success;
    }

    public void createFile(String filename){
        try {
            FileSystem fs = FileSystem.get(configuration);
            if(!fs.exists(new Path(filename))){
                FSDataOutputStream out = fs.create(new Path(filename),new Progressable(){
                    @Override
                    public void progress() {
                        logger.info(".");
                    }
                });
            }

        } catch (IOException e) {
            logger.error("hdfs createFile fail,reason is "+e.getMessage());
        } finally {
            logger.info("hdfs createFile done");
        }
    }

    public void appendLocalFile(String hdfsfile,String appendlocalfile){
        InputStream in = null;
        OutputStream out = null;
        try {
            FileSystem fs = FileSystem.get(URI.create(hdfsfile),configuration);
            //要追加的文件流，inpath为文件
            in = new BufferedInputStream(new FileInputStream(appendlocalfile));
            out = fs.append(new Path(hdfsfile),4089,new Progressable(){
                @Override
                public void progress() {
                    //logger.info(".");
                    System.out.print(".");
                }
            });
            IOUtils.copyBytes(in,out,4096,true);
        } catch (IOException e) {
            logger.error("hdfs appendLocalFile fail,reason is "+e.getMessage());
        } finally {
            IOUtils.closeStream(out);
            IOUtils.closeStream(in);
            logger.error("hdfs appendLocalFile done");
        }
    }

    public void getFoldersName(String path){
        try {
            FileSystem fs = FileSystem.get(configuration);
            FileStatus[] status = fs.listStatus(new Path(path));
            StringBuilder sb = new StringBuilder();
            for(FileStatus statu : status){
                if(statu.isDirectory()){
                    sb.append("文件夹 : ").append(FilenameUtils.getName(statu.getPath().getName())).append("\r");
                }
                if(statu.isFile()){
                    sb.append("文  件 : ").append(FilenameUtils.getName(statu.getPath().getName())).append("\r");
                }
                System.out.println(sb.toString());
                //FileUtils.
                /*System.out.println(FilenameUtils.getBaseName(statu.getPath().getName()));
                logger.info(statu);*/
            }
        } catch (IOException e) {

        } finally {
        }
    }

    public boolean isExist(URL url){
        return false;
    }

    public boolean deleteFile(String filename){
        boolean success = false;
        try {
            FileSystem fs = FileSystem.get(configuration);
            success = fs.delete(new Path(filename),true);
        } catch (IOException e) {
            logger.error("hdfs deleteFile fail,reason is "+e.getMessage());
        } finally {
            logger.info("hdfs deleteFile is "+success);
        }
        return success;
    }

}
