package com.nbf.service.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * Created by Administrator on 2016/5/22 0022.
 */
public class CommonAspect {

    private static final Logger log = Logger.getLogger(CommonAspect.class);

    private String ump;

    public void doRund(ProceedingJoinPoint pjp){
        //log.error("CommonAspect.before."+ump);
        System.out.println("CommonAspect.before."+ump);
        try {
            pjp.proceed();
        } catch (Throwable throwable) {
            //throwable.printStackTrace();
            System.out.println("CommonAspect.catch." + ump);
            throw new RuntimeException(throwable);
        }finally {
            //log.error("CommonAspect.end."+ump);
            System.out.println("CommonAspect.end."+ump);
        }
    }

    public String getUmp() {
        return ump;
    }

    public void setUmp(String ump) {
        this.ump = ump;
    }
}
