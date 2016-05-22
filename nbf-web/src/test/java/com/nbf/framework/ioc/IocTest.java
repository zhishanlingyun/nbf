package com.nbf.framework.ioc;

import com.nbf.service.Manager;
import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2016/5/8 0008.
 */
public class IocTest {

    @Test
    public void test1(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-bean.xml");
        /*String[] names = context.getBeanDefinitionNames();
        for(String name : names)
            System.out.println(name);
        context.getBean("dynamicLoadSpring");*/

        try {
            Manager m = (Manager)context.getBean("redisManager");
            long start = System.currentTimeMillis();
            for(int i=0;i<1000000;i++){
                m.insert("更新");
            }
            long end = System.currentTimeMillis();
            System.out.println("耗时: "+(end-start)/1000.0+"秒");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //耗时: 293.64秒   使用aop
        //耗时: 29.398秒   不使用aop
    }



}
