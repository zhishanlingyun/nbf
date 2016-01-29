package com.nbf.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * User: Administrator
 * Date: 16-1-23
 * Time: 上午10:01
 */
@Controller
@RequestMapping("monitor")
public class MonitorController {

    @RequestMapping("index")
    public String index(){
        return "q/monitor";
    }

}
