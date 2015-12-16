package com.nbf.framwork.decorate;

import com.nbf.common.XmlUtil;
import com.nbf.framwork.decorate.tags.DecorateReqXmlTag;
import com.nbf.framwork.decorate.tags.DecorateXmlTag;
import com.nbf.framwork.decorate.tags.XmlTag;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * User: Administrator
 * Date: 15-11-27
 * Time: 下午8:27
 * 装饰模板渲染
 */
public abstract class DecorateTempletRender implements InitializingBean {

    private static Logger logger = Logger.getLogger(DecorateTempletRender.class);

    //装饰规则集合，该集合在类初始化时构建，之后只读不做修改
    private Map<String/*装饰模板*/,Set<String>/*被装饰页面或路径的正则表达式*/> decorateMap = new ConcurrentHashMap<String,Set<String>>();

    private Map<String,String> decorateMapIndex = new ConcurrentHashMap<String, String>();

    private Map<String/*装饰模板*/,Set<String>/*排除的被装饰页面*/> exclueMap = new ConcurrentHashMap<String,Set<String>>();

    //标签解析器
    private List<TagResolver> tagResolvers;

    private List<Class> tagClass = new ArrayList<Class>();

    private Map<Class,TagResolver> xmlTagTagResolverMap = new HashMap<Class,TagResolver>();

    private XmlDecorate xmlDecorate;

    public Class[] getTagClass(){
        tagClass.add(XmlDecorate.class);
        tagClass.add(DecorateXmlTag.class);
        tagClass.add(DecorateReqXmlTag.class);
        Class[] clazz = new Class[tagClass.size()];
        clazz = tagClass.toArray(clazz);
        return clazz;
    }

    public void initDecorate(){
        URL path = XmlUtil.class.getClassLoader().getResource("decorate.xml");
        URI path_uri = null;
        try {
            path_uri = path.toURI();
        } catch (URISyntaxException e) {
            logger.error("找不到装饰器的配置文件 path: "+path);
        }
        File file = new File(path_uri);
        xmlDecorate = XmlUtil.toObj(file,getTagClass());
        if(null!=xmlDecorate){
            List<XmlTag> xmlTags = xmlDecorate.getXmlTags();
            if(!CollectionUtils.isEmpty(xmlTags)){
                for(XmlTag xmlTag : xmlTags){
                    TagResolver tagResolver = xmlTagTagResolverMap.get(xmlTag.getClass());
                    Map<String,Set<String>> map = tagResolver.resolve(xmlTag);
                    decorateMap.putAll(map);
                }
            }
        }
    }

    void initDecorateMapIndex(){
        if(!decorateMap.isEmpty()){
            Set<String> keys = decorateMap.keySet();
            for(String key : keys){
                Set<String> paths = decorateMap.get(key);
                if(!paths.isEmpty()){
                    for(String path : paths){
                        decorateMapIndex.put(path,key);
                    }
                }
            }
        }
    }

    public void initTagResolver(){
        if(!CollectionUtils.isEmpty(tagResolvers)){
            for(TagResolver resolver : tagResolvers){
                xmlTagTagResolverMap.put(resolver.support(),resolver);
            }
        }
    }

    public String renderPage(String path){
        return render(path,decorateMapIndex);
    }

    public abstract String render(String path,Map<String,String> decorateMapIndex);

    public Map<String, Set<String>> getDecorateMap() {
        return decorateMap;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        initTagResolver();
        initDecorate();
        initDecorateMapIndex();
    }

    public List<TagResolver> getTagResolvers() {
        return tagResolvers;
    }

    public void setTagResolvers(List<TagResolver> tagResolvers) {
        this.tagResolvers = tagResolvers;
    }
}
