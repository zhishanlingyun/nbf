package com.nbf.framwork.decorate.freemarker;

import com.nbf.framwork.decorate.DecorateTempletRender;
import com.nbf.framwork.decorate.tagresolver.DecorateTagResolver;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * User: Administrator
 * Date: 15-11-28
 * Time: 下午2:46
 */
public class FreemarkerDecorateRender extends DecorateTempletRender {

    private ConcurrentMap<String,String> cache = new ConcurrentHashMap<String, String>();

    @Override
    public String render(String path, Map<String,String> decorateMap) {
        String url = null;
        url = get(path);
        if(!StringUtils.isEmpty(url)){
            return url;
        }
        if(decorateMap.containsKey(path)){
            url = decorateMap.get(path);
            cache.putIfAbsent(path,url);
            return url;
        }else{
            Set<String> paths = decorateMap.keySet();
            if(!CollectionUtils.isEmpty(paths)){
                String source = null;
                for(String pathKey :paths){
                    source = pathKey;
                    pathKey = pathKey.replace("*","");
                    if(path.startsWith(pathKey)){
                        url = decorateMap.get(source);
                        cache.putIfAbsent(path,url);
                        return url;
                    }
                }
            }
        }
        return path;
    }

    private String get(String path){
        return cache.get(path);
    }
}
