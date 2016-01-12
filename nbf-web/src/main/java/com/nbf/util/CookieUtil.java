package com.nbf.util;

import com.nbf.common.util.code.JsonUtil;
import com.nbf.common.util.redis.RedisAccessException;
import com.nbf.common.util.redis.RedisUtil;
import com.nbf.dto.User;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * User: root
 * Date: 1/12/16
 * Time: 12:45 AM
 */
public class CookieUtil {
    public static final String EXPIRY = "_expiry";

    public static String getCookieValue(HttpServletRequest request,String name){
        if(null!=request){
            Cookie[] cookies = request.getCookies();
            if(null!=cookies && cookies.length>0){
                for(Cookie cookie : cookies){
                    if(cookie.getName().equals(name)){
                        return cookie.getValue();
                    }
                }
            }
            return null;

        }
        return null;
    }

    public static void addCookieValue(HttpServletResponse response,String name,String value){
        Cookie cookie = new Cookie(name,value);
        response.addCookie(cookie);
    }

    public static void deleteCookie(HttpServletResponse response,String name){
        Cookie cookie = new Cookie(name,null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);

    }

    public static boolean checkCookie(HttpServletRequest request,RedisUtil redisUtil){
        if(null==request)   return false;
        try{
            Cookie[] cookies = request.getCookies();
            if(null!=cookies&&cookies.length>0){
                for(Cookie cookie : cookies){
                    if(cookie.getName().equals("sid")){
                        String json = redisUtil.get(cookie.getValue());
                        if(!StringUtils.isEmpty(json)){
                            User user = (User)JsonUtil.json2Obj(json);
                            if(null != user){
                                return true;
                            }
                        }
                    }
                }
            }

        }catch (RedisAccessException e){
            return false;
        }
        return false;
    }

}
