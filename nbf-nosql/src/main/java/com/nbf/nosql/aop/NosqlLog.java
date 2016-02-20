package com.nbf.nosql.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * User: Administrator
 * Date: 16-2-13
 * Time: 上午10:00
 */
@Aspect
@Component
public class NosqlLog {

    private static Logger logger = Logger.getLogger(NosqlLog.class);

    @Around("execution(public * com.nbf.nosql.hbase.*.*(..))")
    public Object writeLog(ProceedingJoinPoint pjp){
        Object obj = null;
        try {
            logger.info("["+pjp.getSignature()+"] start ...");
            obj = pjp.proceed();
            logger.info("["+pjp.getSignature()+"] sucess ...");
        } catch (Throwable throwable) {
            logger.error("["+pjp.getSignature()+"] fail ",throwable);
        } finally {
        }
        return obj;
    }

}
