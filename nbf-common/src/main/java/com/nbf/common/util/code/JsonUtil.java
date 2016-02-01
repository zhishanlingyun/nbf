package com.nbf.common.util.code;

import com.alibaba.fastjson.JSON;

/**
 * User: Administrator
 * Date: 16-1-10
 * Time: 下午9:31
 */
public class JsonUtil {

    public static String obj2Json(Object obj){
        return JSON.toJSONString(obj);
    }

    public static <T> T json2Obj(String json,Class<T> clazz){
        return JSON.parseObject(json,clazz);
    }

    public static String list2Json(){

        return null;
    }

}
