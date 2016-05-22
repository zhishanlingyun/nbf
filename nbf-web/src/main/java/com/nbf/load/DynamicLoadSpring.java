package com.nbf.load;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Created by Administrator on 2016/5/8 0008.
 */
public class DynamicLoadSpring implements ApplicationContextAware {

    private final Logger log = LoggerFactory.getLogger(DynamicLoadSpring.class);

    private ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public void loadBean(){
        ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext)context;
        ConfigurableListableBeanFactory beanFactory = configurableApplicationContext.getBeanFactory();
        //configurableApplicationContext.get
        log.info(context.toString());
    }

}
