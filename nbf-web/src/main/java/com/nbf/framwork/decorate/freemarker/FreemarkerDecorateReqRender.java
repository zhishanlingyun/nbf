package com.nbf.framwork.decorate.freemarker;

import com.nbf.framwork.decorate.DecorateTempletRender;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * User: Administrator
 * Date: 15-11-29
 * Time: 下午10:40
 */
public class FreemarkerDecorateReqRender extends DecorateTempletRender {

    private ConcurrentMap<String,String> cache = new ConcurrentHashMap<String, String>();

    @Override
    public String render(String req, Map<String, String> decorateMapIndex) {
        String url = null;
        req = req.replace("/nbf-web","");
        url = get(req);
        if(!StringUtils.isEmpty(url)){
            return url;
        }
        if(decorateMapIndex.containsKey(req)){
            url = decorateMapIndex.get(req);
            cache.putIfAbsent(req,url);
            return url;
        }else{
            Set<String> paths = decorateMapIndex.keySet();
            if(!CollectionUtils.isEmpty(paths)){
                String source = null;
                for(String pathKey :paths){
                    source = pathKey;
                    pathKey = pathKey.replace("*","");
                    if(req.startsWith(pathKey)){
                        url = decorateMapIndex.get(source);
                        cache.putIfAbsent(req,url);
                        return url;
                    }
                }
            }
        }
        return null;
    }

    private String get(String req){
        return cache.get(req);
    }
}
