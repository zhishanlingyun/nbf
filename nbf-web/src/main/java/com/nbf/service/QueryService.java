package com.nbf.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 15-11-26
 * Time: 上午9:33
 * To change this template use File | Settings | File Templates.
 */
public class QueryService implements InitializingBean {

    private static Logger logger = Logger.getLogger(QueryService.class);
    private String beanName;
    private Map<String,String> param;

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("QueryService afterPropertiesSet");
        beanName = "kkkkkkkkk";
        param = new HashMap<String,String>();
        param.put("mark","kkkkk");
        addParam(param);
    }

    public void addParam(Map<String,String> map){

    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public Map<String, String> getParam() {
        return param;
    }

    public void setParam(Map<String, String> param) {
        this.param = param;
    }
}
