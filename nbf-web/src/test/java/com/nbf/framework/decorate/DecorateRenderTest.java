package com.nbf.framework.decorate;

import com.nbf.framwork.decorate.DecorateTempletRender;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 15-11-28
 * Time: 下午2:05
 * To change this template use File | Settings | File Templates.
 */
public class DecorateRenderTest {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");

    @Test
    public void decorateTest(){
        DecorateTempletRender render = (DecorateTempletRender)context.getBean("decorateTempletRender");
        Map<String, Set<String>> map = render.getDecorateMap();
        System.out.println(map);
    }
}
