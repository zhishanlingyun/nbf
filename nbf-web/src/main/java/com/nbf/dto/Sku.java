package com.nbf.dto;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 15-11-25
 * Time: 下午11:41
 * To change this template use File | Settings | File Templates.
 */
public class Sku {
    private long skuid;
    private String name;

    public Sku() {
    }

    public Sku(long skuid, String name) {
        this.skuid = skuid;
        this.name = name;
    }

    public long getSkuid() {
        return skuid;
    }

    public void setSkuid(long skuid) {
        this.skuid = skuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Sku{" +
                "skuid=" + skuid +
                ", name='" + name + '\'' +
                '}';
    }
}
