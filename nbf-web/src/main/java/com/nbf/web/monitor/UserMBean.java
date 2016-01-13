package com.nbf.web.monitor;

import java.util.concurrent.atomic.AtomicLong;

/**
 * User: Administrator
 * Date: 16-1-13
 * Time: 下午10:27
 */
public class UserMBean {

    // 在线用户数量
    private static volatile AtomicLong userCount = new AtomicLong(0);

    // 获取当前在线用户数
    public static Long getOnlineUserCount(){
        return userCount.get();
    }

    // 递增当前在线用户数
    public static void incrUserCount(){
        userCount.incrementAndGet();
    }

    // 递减当前在线用户数
    public static void decUserCount(){
        userCount.decrementAndGet();
    }

    public static void clearUserCount(){
        userCount.set(0);
    }

}
