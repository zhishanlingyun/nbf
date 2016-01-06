package com.nbf.common.com.nbf.common.util.redis;

import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * User: zsly
 * Date: 1/5/16
 * Time: 10:01 PM
 */
public class RedisUtil {

    private ShardedJedisPool wPool;

    private ShardedJedisPool rPool;

    private JedisPoolConfig jedisPoolConfig;

    private String master;

    public RedisUtil(JedisPoolConfig jedisPoolConfig,String master) {
        this.jedisPoolConfig = jedisPoolConfig;
        this.master = master;
        init();
    }

    public void init(){
        List<JedisShardInfo> wShards = null;
        List<JedisShardInfo> rShards = null;
        Assert.isTrue(master != null, "init fail,masterstr is null!");
        List<String> masters = Arrays.asList(master.split(","));
        Assert.isTrue(!CollectionUtils.isEmpty(masters),"init fail,masters is null!");
        wShards = new ArrayList<JedisShardInfo>();
        for(String masteraddr : masters){
            String[] addr = masteraddr.split(":");
            String host = addr[0];
            Integer port = Integer.parseInt(addr[1]);
            JedisShardInfo jedisShardInfo = new JedisShardInfo(host,port,5000);
            //check has password
            if(addr.length==3){
                jedisShardInfo.setPassword(addr[2]);
            }
            wShards.add(jedisShardInfo);
        }
        this.wPool = new ShardedJedisPool(this.jedisPoolConfig,wShards);

    }

    public String setex(String key, String value,int seconds) throws RedisAccessException{
        boolean flag = true;
        ShardedJedis j = null;
        String result = null;
        try {
            j = wPool.getResource();
            result = j.setex(key, seconds, value);
        } catch (Exception ex) {
            flag = false;
            wPool.returnBrokenResource(j);
            throw new RedisAccessException(ex + "," + j.getShardInfo(key).toString());
        } finally {
            if (flag) {
                wPool.returnResource(j);
            }
        }
        return result;
    }

    public Long setnx(String key, String value) throws RedisAccessException {
        boolean flag = true;
        ShardedJedis j = null;
        Long result = null;
        try {
            j = wPool.getResource();
            result = j.setnx(key, value);
        } catch (Exception ex) {
            flag = false;
            wPool.returnBrokenResource(j);
            throw new RedisAccessException(ex + "," + j.getShardInfo(key).toString());
        } finally {
            if (flag) {
                wPool.returnResource(j);
            }
        }
        return result;
    }

    public String get(String key) throws RedisAccessException {
        String result = null;
        ShardedJedis j = null;
        boolean flag = true;
        try {
            /*if (toTryCount > 0) {
                j = readPool.getResource();
            } else {*/
                j = wPool.getResource();
            /*}*/
            result = j.get(key);
        } catch (Exception ex) {
            flag = false;
            /*if (toTryCount > 0) {
                readPool.returnBrokenResource(j);
                result = get(toTryCount - 1, key);
            } else {*/
            wPool.returnBrokenResource(j);
                throw new RedisAccessException(ex + "," + j.getShardInfo(key).toString());
            /*}*/
        } finally {
            /*if (flag) {
                if (toTryCount > 0)
                    readPool.returnResource(j);
                else*/
            wPool.returnResource(j);
            /*}*/
        }
        return result;
    }

    public Boolean exists(String key) throws RedisAccessException {
        Boolean result = null;
        ShardedJedis j = null;
        boolean flag = true;
        try {
            j = wPool.getResource();
            result = j.exists(key);
        } catch (Exception ex) {
            flag = false;
            wPool.returnBrokenResource(j);
            throw new RedisAccessException(ex + "," + j.getShardInfo(key).toString());
        } finally {
            if (flag) {
                wPool.returnResource(j);
            }
        }
        return result;
    }
}
