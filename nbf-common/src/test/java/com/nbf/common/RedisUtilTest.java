package com.nbf.common;

import com.nbf.common.util.redis.RedisAccessException;
import com.nbf.common.util.redis.RedisUtil;
import org.junit.Before;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created with IntelliJ IDEA.
 * User: root
 * Date: 1/5/16
 * Time: 11:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class RedisUtilTest {

    String master;
    JedisPoolConfig jedisPoolConfig;

    @Before
    public void setup(){
        master = "192.168.81.133:6379,";
        jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(10);
        jedisPoolConfig.setMaxIdle(7);
    }

    //@Test
    public void teststr(){
        try {
            RedisUtil redisUtil = new RedisUtil(jedisPoolConfig,master);
            //redisUtil.setex("k","mm",10);
            System.out.println(redisUtil.get("k"));

        } catch (RedisAccessException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

}
