package com.nbf.common.util;

import java.util.Random;

/**
 * User: Administrator
 * Date: 16-1-31
 * Time: 下午9:28
 */
public abstract class CommonUtil {

    public static int randomInt(int min,int max){
        Random rdm = new Random();
        return rdm.nextInt(max)%(max-min+1) + min;
    }


}
