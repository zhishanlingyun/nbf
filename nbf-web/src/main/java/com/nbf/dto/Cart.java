package com.nbf.dto;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 15-11-25
 * Time: 下午11:49
 * To change this template use File | Settings | File Templates.
 */
public class Cart {
    private long timestamp;

    public Cart() {
    }

    public Cart(long timestamp) {
        this.timestamp = timestamp;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "cartService{" +
                "timestamp=" + timestamp +
                '}';
    }
}
