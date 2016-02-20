package com.nbf.nosql.hadoop.hbase;

import com.nbf.nosql.hbase.HBaseDao;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * User: Administrator
 * Date: 16-2-13
 * Time: 上午9:41
 */
public class HbaseTest {

    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");

    @Test
    public void testHbase(){
        HBaseDao dao = context.getBean(HBaseDao.class);
        dao.insert("t1", Bytes.toBytes("rowkey-6"),Bytes.toBytes("f1"),Bytes.toBytes("content"),Bytes.toBytes("f6-content-value"));
        dao.getRecord("t1",Bytes.toBytes("rowkey-6"),Bytes.toBytes("f1"),Bytes.toBytes("content"));
    }

}
