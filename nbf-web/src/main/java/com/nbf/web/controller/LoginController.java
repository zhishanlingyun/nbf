package com.nbf.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import java.util.Map;

/**
 * Date: 15-11-25
 * Time: 下午7:43
 */
@Controller
public class LoginController {

    @RequestMapping("/login")
    public ModelAndView handleLogin(){
        ModelAndView mv = new ModelAndView("");
        mv.addObject("username","张三");
        //mv.setViewName("x/login");
        mv.setViewName("q/form");
        //mv.setViewName("m/index");
        return mv;
    }

    @RequestMapping("/login/req")
    public ModelAndView handleReq(){
        ModelAndView mv = new ModelAndView("");
        mv.addObject("username","张三");
        //mv.setViewName("x/login");
        mv.setViewName("x/bms/user/index");
        return mv;
    }

}
