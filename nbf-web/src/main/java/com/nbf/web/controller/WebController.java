package com.nbf.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * User: Administrator
 * Date: 16-1-9
 * Time: 下午9:43
 */
@Controller
@RequestMapping("/bms")
public class WebController {

    @RequestMapping("/index")
    public String index(){
        return "q/index";
    }

}
