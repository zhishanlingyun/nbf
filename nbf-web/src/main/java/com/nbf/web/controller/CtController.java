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
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * User: root
 * Date: 1/13/16
 * Time: 11:59 PM
 */
@Controller
@RemoteProxy(name="ctController",
        creator = SpringCreator.class)
public class CtController /*implements Observer*/ {

    UserMBean ub = UserMBean.getInstance();
    Long count = 0L;

    @RemoteMethod
    public String getOnlineUserCount(){
        return String.valueOf(ub.getOnlineUserCount());
    }

    @RemoteMethod
    public void send(){
        /*Runnable run = new Runnable() {
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
        };*/
        /*while(true){
            Browser.withAllSessions(run);
            try {
                Thread.sleep(6000);
            }catch (InterruptedException e){}

        }*/
        //Browser.
        DwrObserver dwrObserver = new DwrObserver(null);
        ub.addObserver(dwrObserver);
        /*Runnable run = new Runnable() {
            ScriptBuffer script = new ScriptBuffer();
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
        };
        Browser.withAllSessions(run);*/


    }

    /*@Override
    public void update(Observable o, Object arg) {
        Long _count = (Long)arg;
        count = _count;
        System.out.println(_count);
    }*/

}

class DwrObserver implements Observer,Runnable{

    Browser browser = null;
    ScriptBuffer script = new ScriptBuffer();
    Long count = 0l;

    DwrObserver(Browser browser) {
        this.browser = browser;
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
        browser.withAllSessions(this);
    }
}
