package com.nbf.web.controller;

import com.nbf.dto.User;
import com.nbf.web.common.Result;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Date: 15-11-25
 * Time: 下午7:43
 */
@Controller
public class LoginController {

    private static Logger logger = Logger.getLogger(LoginController.class);

    @RequestMapping("/login")
    public ModelAndView login(){
        ModelAndView mv = new ModelAndView("");
        mv.addObject("username","张三");
        //mv.setViewName("x/login");
        mv.setViewName("q/login");
        //mv.setViewName("m/index");
        logger.info("------login------");
        return mv;
    }

    @RequestMapping(value="/login/req",method =RequestMethod.POST )
    @ResponseBody
    public Result handleReq(HttpServletResponse response,User user){
        logger.info("username is "+user.getUsername());
        logger.info("password is "+user.getPassword());
        Result result = new Result();
        result.setCode(200);
        result.setSuccess(true);
        result.setUrl("/bms/index");
        return result;
    }

}
