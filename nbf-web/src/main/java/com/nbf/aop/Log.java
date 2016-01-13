package com.nbf.aop;

import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: root
 * Date: 1/13/16
 * Time: 12:43 AM
 * To change this template use File | Settings | File Templates.
 */
@Component
public class Log {

    public String dolog(String n){
        return "log "+ n;
    }
}
