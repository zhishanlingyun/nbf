package com.nbf.nosql.hadoop.hdfs;

import org.junit.Test;

/**
 * User: Administrator
 * Date: 16-2-8
 * Time: 下午2:49
 */
public class HdfsTest {

    @Test
    public void testMkir(){
        HdfsManage manage = new HdfsManage();
        //manage.mkdir("/test-hdfs-folder");
        //manage.createFile("/f/sm/kk.mp4");
        //manage.appendLocalFile("/f/sm/kk.mp4","E:\\BaiduYunDownload\\YD150920品丝阁四主重度踢踹踩踏_(new).mp4");
        //manage.deleteFile("/f/sm/kk.mp4");
        manage.getFoldersName("/hbase");
    }

}
