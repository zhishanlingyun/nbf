package com.nbf.web.controller;

import com.nbf.common.util.code.JsonUtil;
import com.nbf.common.util.code.UUIDUtil;
import com.nbf.common.util.redis.RedisAccessException;
import com.nbf.common.util.redis.RedisUtil;
import com.nbf.dto.User;
import com.nbf.util.CookieUtil;
import com.nbf.web.common.OnlineUser;
import com.nbf.web.common.Result;
import com.nbf.web.monitor.UserMBean;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.JedisPoolConfig;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Date: 15-11-25
 * Time: 下午7:43
 */
@Controller
public class LoginController implements InitializingBean{

    private static Logger logger = Logger.getLogger(LoginController.class);

    private static final Map<String,User> users = new HashMap <String,User>();

    private RedisUtil redisUtil;

    private UserMBean ub = UserMBean.getInstance();

    //TODO 增加注册机制

    @Override
    public void afterPropertiesSet() throws Exception {
        User u1 = new User("admin","123",100);
        User u2 = new User("admin2","123",100);
        User u3 = new User("admin3","123",100);
        users.put(u1.getUsername(),u1);
        users.put(u2.getUsername(),u2);
        users.put(u3.getUsername(),u3);
        JedisPoolConfig jedisPoolConfig;
        String master = "192.168.106.130:6379,";
        jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(10);
        jedisPoolConfig.setMaxIdle(7);
        redisUtil = new RedisUtil(jedisPoolConfig,master);
        new Thread(new Runnable(){
            @Override
            public void run() {
                while(true){
                    ub.incrUserCount();
                    try {
                        Thread.sleep(3000);
                    }catch (InterruptedException e){}

                }
            }
        }).start();
    }

    @RequestMapping("/login")
    public ModelAndView login(){
        ModelAndView mv = new ModelAndView("");
        mv.addObject("username","张三");
        mv.setViewName("q/login");
        logger.info("------login------");
        return mv;
    }

    @RequestMapping(value="/login/req",method =RequestMethod.POST )
    @ResponseBody
    public Result handleReq(HttpServletRequest request,HttpServletResponse response,User user){
        logger.info("username is "+user.getUsername());
        logger.info("password is "+user.getPassword());
        String dispather = "/bms/index";
        if(null!=user&&!StringUtils.isEmpty(user.getUsername())){
            if(!user.equals(users.get(user.getUsername()))){
                dispather = "/login";
            }else if(CookieUtil.checkCookie(request,redisUtil)){

            }else{
                String sid = UUIDUtil.timeRandom()+"";
                Cookie cookie = new Cookie("sid",sid);
                cookie.setMaxAge(3600);
                cookie.setPath("/");
                Cookie ck = new Cookie("hello","nbf");
                ck.setPath("/");
                response.addCookie(cookie);
                response.addCookie(ck);
                try {
                    redisUtil.setnx(sid, JsonUtil.obj2Json(user));
                } catch (RedisAccessException e) {
                    e.printStackTrace();
                }
                ub.incrUserCount();
            }
        }

        Result result = new Result();
        result.setCode(200);
        result.setSuccess(true);
        result.setUrl("/bms/index");
        return result;
    }

    @RequestMapping(value="/login/usercount",method =RequestMethod.GET )
    @ResponseBody
    public Result getOnlineUserCount(){
        Result result = new Result();
        OnlineUser ou = new OnlineUser(String.valueOf(ub.getOnlineUserCount()));
        result.setMsg(JsonUtil.obj2Json(ou));
        result.setSuccess(true);
        return result;
    }

}
