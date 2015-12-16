package com.nbf.framwork.decorate.freemarker;

import com.nbf.common.PatternUtil;
import com.nbf.framwork.decorate.DecoratePage;
import com.nbf.support.springfreemarker.FtlService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * User: Administrator
 * Date: 15-11-29
 * Time: 上午9:52
 */
@FtlService(name = "FtlSupport")
public class FtlSupport {

    private FreeMarkerConfigurer freeMarkerConfigurer;

    private Configuration configuration;

    public DecoratePage getDecoratePage(String path){
        DecoratePage page = new DecoratePage();
        try {
            if(null!=freeMarkerConfigurer){
                configuration = freeMarkerConfigurer.getConfiguration();
            }
            Map map = (Map)DecorateContext.get(DecorateContext.DECOTATE_MODLE);
            HttpServletRequest request = (HttpServletRequest)DecorateContext.get(DecorateContext.DECORATE_REQUEST);
            Template template = configuration.getTemplate(path,"utf-8");
            try {
                map.putAll(requestToMap(request));
                String content = null;
                java.io.CharArrayWriter  out=new java.io.CharArrayWriter ();
                template.process(map, out);
                content = out.toString();
                page.setTitle(PatternUtil.getHtmlTag(content,"title"));
                page.setHeader(PatternUtil.getHtmlTag(content,"head"));
                page.setBody(PatternUtil.getHtmlTag(content, "body"));
            } catch (TemplateException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        } catch (IOException e) {

        }
        return page;
    }

    public Map requestToMap(HttpServletRequest request){
        Map map = new HashMap();
        Enumeration enu = request.getAttributeNames();
        String key = null;
        Object obj = null;
        while(enu.hasMoreElements()){
            key = (String)enu.nextElement();
            obj = request.getAttribute(key);
            map.put(key,obj);
        }
        return map;
    }

    public FreeMarkerConfigurer getFreeMarkerConfigurer() {
        return freeMarkerConfigurer;
    }

    public void setFreeMarkerConfigurer(FreeMarkerConfigurer freeMarkerConfigurer) {
        this.freeMarkerConfigurer = freeMarkerConfigurer;
    }


}
