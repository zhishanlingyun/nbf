package com.nbf.support.springfreemarker;

import com.nbf.common.Cache;
import freemarker.template.Configuration;
import freemarker.template.SimpleHash;
import freemarker.template.TemplateException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

/**
 * User: Administrator
 * Date: 15-11-25
 * Time: 下午10:40
 */
public class FreeMarkerConfigurerSupport extends FreeMarkerConfigurer implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    protected void postProcessConfiguration(Configuration config) throws IOException, TemplateException {
        String[] beanNames = applicationContext.getBeanDefinitionNames();
        Map<String,Object> mapObj = null;
        if(null!=beanNames&&beanNames.length>0){
            mapObj = new HashMap<String,Object>();
            for(String beanName:beanNames){
                Object bean = applicationContext.getBean(beanName);
                Annotation annotation = bean.getClass().getAnnotation(FtlService.class);
                if(annotation instanceof FtlService){
                    System.out.println("加载类 "+bean.getClass().getName());
                    mapObj.put(((FtlService) annotation).name(), bean);
                }
            }
        }
        if(!CollectionUtils.isEmpty(mapObj)){
            config.setAllSharedVariables(new SimpleHash(mapObj, config.getObjectWrapper()));
        }
        Cache c = (Cache)applicationContext.getBean("cache");
        System.out.println("Cache is :"+c.get(""));
    }

    /*@Override
    public Configuration createConfiguration() throws IOException, TemplateException {
        Configuration cfg = super.createConfiguration();
        String[] beanNames = applicationContext.getBeanDefinitionNames();
        Map<String,Object> mapObj = null;
        if(null!=beanNames&&beanNames.length>0){
           mapObj = new HashMap<String,Object>();
           for(String beanName:beanNames){
               Object bean = applicationContext.getBean(beanName);
               Annotation annotation = bean.getClass().getAnnotation(FtlService.class);
               if(annotation instanceof FtlService){
                   System.out.println("加载类 "+bean.getClass().getName());
                   mapObj.put(((FtlService) annotation).name(), bean);
               }
           }
        }
        if(!CollectionUtils.isEmpty(mapObj)){
            cfg.setAllSharedVariables(new SimpleHash(mapObj, cfg.getObjectWrapper()));
        }
        return cfg;
    }*/

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
