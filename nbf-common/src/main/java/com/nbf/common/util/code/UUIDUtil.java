package com.nbf.common.util.code;

import java.util.Random;

/**
 * User: Administrator
 * Date: 16-1-10
 * Time: 下午8:50
 */
public class UUIDUtil {

    public static long timeRandom(){
        long time = System.currentTimeMillis();
        Random random = new Random();
        long r = random.nextLong();
        return Math.abs(time + r);
    }

    public static void main(String[] args){
        for (int i=0;i<1000;i++){
            System.out.println(UUIDUtil.timeRandom());
        }

    }


}
