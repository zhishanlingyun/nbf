package com.nbf.service.impl;

import com.nbf.service.Manager;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;

/**
 * Created by Administrator on 2016/5/22 0022.
 */
public class RedisManager implements Manager {

    private static final Logger log = Logger.getLogger(RedisManager.class);

    @Override
    public void insert(String msg) {
        Assert.isTrue(!"error".equals(msg), "参数错误");
        //log.error("RedisManager.insert "+msg);
        System.out.println("RedisManager.insert "+msg);
    }

    @Override
    public void update(String msg) {
        Assert.isTrue(!"error".equals(msg), "update参数错误");
        log.error("RedisManager.update "+msg);
    }

    @Override
    public void delete(String msg) {
        Assert.isTrue(!"error".equals(msg), "delete参数错误");
        log.error("RedisManager.delete "+msg);
    }
}
