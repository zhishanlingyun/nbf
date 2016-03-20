package com.nbf.nosql.hbase;

import org.apache.commons.pool2.BaseKeyedPooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

/**
 * User: Administrator
 * Date: 16-2-21
 * Time: 下午8:13
 */
public class ConnectionPool extends BaseKeyedPooledObjectFactory<Configuration,Connection> {
    @Override
    public Connection create(Configuration cfg) throws Exception {
        Connection conn = ConnectionFactory.createConnection(cfg);
        return conn;
    }

    @Override
    public PooledObject<Connection> wrap(Connection conn) {
        return new DefaultPooledObject<Connection>(conn);
    }

    @Override
    public void destroyObject(Configuration key, PooledObject<Connection> p) throws Exception {
        Connection conn = p.getObject();
        conn.close();
    }
}

