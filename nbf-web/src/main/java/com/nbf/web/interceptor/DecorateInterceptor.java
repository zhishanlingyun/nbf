package com.nbf.web.interceptor;

import com.nbf.framwork.decorate.DecorateTempletRender;
import com.nbf.framwork.decorate.freemarker.DecorateContext;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * User: Administrator
 * Date: 15-11-26
 * Time: 下午9:03
 */
public class DecorateInterceptor implements HandlerInterceptor {
    private static Logger logger = Logger.getLogger(DecorateInterceptor.class);

    private DecorateTempletRender decorateTempletRender;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mv) throws Exception {
        //logger.info("@@@@@@@@@@postHandle@@@@@@@@");
        DecorateContext.add(DecorateContext.DECOTATE_MODLE,mv.getModel());
        DecorateContext.add(DecorateContext.DECORATE_REQUEST,request);
        DecorateContext.add(DecorateContext.DECORATE_RESPONSE,response);
        DecorateContext.add(DecorateContext.DECORATE_REAL_PATH,mv.getViewName()+".htm");
        mv.addObject(DecorateContext.DECORATE_REAL_PATH,mv.getViewName()+".htm");
        String path = decorateTempletRender.renderPage(mv.getViewName()+".htm");
        path = path.substring(0,path.lastIndexOf(".htm"));
        mv.setViewName(path);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }

    public DecorateTempletRender getDecorateTempletRender() {
        return decorateTempletRender;
    }

    public void setDecorateTempletRender(DecorateTempletRender decorateTempletRender) {
        this.decorateTempletRender = decorateTempletRender;
    }
}
