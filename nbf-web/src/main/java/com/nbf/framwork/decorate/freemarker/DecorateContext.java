package com.nbf.framwork.decorate.freemarker;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * User: Administrator
 * Date: 15-11-29
 * Time: 上午11:28
 * 装饰器上下文，非线程安全对象，放在ThreadLocal中，避免使用锁
 */
public class DecorateContext {

    public static final String FREEMARE_RESOLVER = "freemarker_resolver";

    public static final String DECORATE_REQUEST = "decorate_request";

    public static final String DECORATE_RESPONSE = "decorate_response";

    public static final String DECOTATE_MODLE = "decorate_modle";

    public static final String DECORATE_REAL_PATH = "decorate_info_path";

    private static ThreadLocal<Content> contentThreadLocal = new ThreadLocal<Content>();

    public static void add(String key,Object obj){
        Content content = contentThreadLocal.get();
        if(null == content){
            content = new Content();
        }
        content.add(key,obj);
        contentThreadLocal.set(content);
    }

    public static Object get(String key){
        Object result = null;
        Content content = contentThreadLocal.get();
        if(null!=content){
            result = content.get(key);
        }
        return result;
    }

    public static class Content{

        private ConcurrentHashMap<String,Object> map = new ConcurrentHashMap<String,Object>();

        public void add(String key,Object obj){
            map.putIfAbsent(key,obj);
        }

        public Object get(String key){
            return map.get(key);
        }
    }
}
