package com.nbf.web.controller;

import com.nbf.web.monitor.UserMBean;
import org.directwebremoting.Browser;
import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.annotations.Param;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.directwebremoting.spring.SpringCreator;
import org.springframework.core.style.ToStringCreator;
import org.springframework.stereotype.Controller;

import java.util.Collection;

/**
 * User: root
 * Date: 1/13/16
 * Time: 11:59 PM
 */
@Controller
@RemoteProxy(name="ctController",
        creator = SpringCreator.class)
public class CtController {

    @RemoteMethod
    public String getOnlineUserCount(){
        return String.valueOf(UserMBean.getOnlineUserCount());
    }

    @RemoteMethod
    public void send(){
        Runnable run = new Runnable() {
            ScriptBuffer script = new ScriptBuffer();
            @Override
            public void run() {
                //设置要调用的 js及参数
                script.appendCall("show",UserMBean.getOnlineUserCount());
                //得到所有ScriptSession
                Collection<ScriptSession> sessions = Browser.getTargetSessions();
                //遍历每一个ScriptSession
                for (ScriptSession scriptSession : sessions){
                    scriptSession.addScript( script);
                }
            }
        };
        while(true){
            Browser.withAllSessions(run);
            try {
                Thread.sleep(6000);
            }catch (InterruptedException e){}

        }
    }

}
