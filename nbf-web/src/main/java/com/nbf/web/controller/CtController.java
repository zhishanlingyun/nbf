package com.nbf.web.controller;

import com.nbf.web.monitor.UserMBean;
import org.directwebremoting.*;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.directwebremoting.spring.SpringCreator;
import org.springframework.stereotype.Controller;

import java.util.Collection;
import java.util.Observable;
import java.util.Observer;

/**
 * User: root
 * Date: 1/13/16
 * Time: 11:59 PM
 */
@Controller
@RemoteProxy(name="ctController",
        creator = SpringCreator.class)
public class CtController{

    private UserMBean ub = UserMBean.getInstance();

    @RemoteMethod

    public String getOnlineUserCount(){
        return String.valueOf(ub.getOnlineUserCount());
    }

    @RemoteMethod
    public void send(){
        ServerContext sct = ServerContextFactory.get();
        DwrObserver dwrObserver = new DwrObserver(sct);
        ub.addObserver(dwrObserver);
    }
}

class DwrObserver implements Observer,Runnable{

    ServerContext sct;

    ScriptBuffer script = new ScriptBuffer();
    Long count = 0l;

    DwrObserver(ServerContext sct) {
        this.sct = sct;
    }

    @Override
    public void run() {
        //设置要调用的 js及参数
        script.appendCall("show",count);
        //得到所有ScriptSession
        Collection<ScriptSession> sessions = Browser.getTargetSessions();
        //遍历每一个ScriptSession
        for (ScriptSession scriptSession : sessions){
            scriptSession.addScript( script);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        Long _count = (Long)arg;
        count = _count;
        System.out.println(count);
        Browser.withAllSessions(sct,this);
    }
}
