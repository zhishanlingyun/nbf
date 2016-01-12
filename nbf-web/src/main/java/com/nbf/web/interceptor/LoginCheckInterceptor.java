package com.nbf.web.interceptor;

import com.nbf.common.util.redis.RedisUtil;
import com.nbf.util.CookieUtil;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.JedisPoolConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

/**
 * User: root
 * Date: 1/11/16
 * Time: 10:39 PM
 */
public class LoginCheckInterceptor implements HandlerInterceptor {

    private static Logger logger = Logger.getLogger(LoginCheckInterceptor.class);

    private static final Set<String> nologin = new HashSet<String>();

    private static RedisUtil redisUtil;

    static{
        nologin.add("/login");
        nologin.add("/login/req");
        JedisPoolConfig jedisPoolConfig;
        String master = "192.168.106.130:6379,";
        jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(10);
        jedisPoolConfig.setMaxIdle(7);
        redisUtil = new RedisUtil(jedisPoolConfig,master);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String url = request.getServletPath();
        String cxt = request.getContextPath();
        logger.info("url is "+url);
        logger.info("cxt is "+cxt);
        if(nologin.contains(url)){
            return true;
        }else if(CookieUtil.checkCookie(request,redisUtil)){
            return true;
        }
        response.sendRedirect(cxt+"/login");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}
