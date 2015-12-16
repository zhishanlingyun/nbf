package com.nbf.web.interceptor;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 15-11-26
 * Time: 下午9:41
 * To change this template use File | Settings | File Templates.
 */
public class TwoInterceptor implements HandlerInterceptor {
    private static Logger logger = Logger.getLogger(TwoInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("@@@@@@@@@@ TwoInterceptor postHandle @@@@@@@@");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info("@@@@@@@@@@ TwoInterceptor postHandle @@@@@@@@");
    }
}
