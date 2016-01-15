package com.nbf.web.monitor;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.atomic.AtomicLong;

/**
 * User: Administrator
 * Date: 16-1-13
 * Time: 下午10:27
 */
public class UserMBean extends Observable {

    // 在线用户数量
    private volatile AtomicLong userCount = new AtomicLong(0);
    private static UserMBean ub = new UserMBean();

    public static UserMBean getInstance(){
       return ub;
    }

    private UserMBean(){}

    // 获取当前在线用户数
    public Long getOnlineUserCount(){
        return userCount.get();
    }

    // 递增当前在线用户数
    public void incrUserCount(){
        userCount.incrementAndGet();
        setChanged();
        notifyObservers(getOnlineUserCount());
    }

    // 递减当前在线用户数
    public void decUserCount(){
        userCount.decrementAndGet();
        setChanged();
        notifyObservers(getOnlineUserCount());
    }

    public void clearUserCount(){
        userCount.set(0);
        setChanged();
        notifyObservers(getOnlineUserCount());
    }



}
