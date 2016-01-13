package com.nbf.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * User: root
 * Date: 1/12/16
 * Time: 10:40 PM
 */
@Aspect
@Component
public class LogAspect {

    private static Logger logger = Logger.getLogger(LogAspect.class);
    static {
        logger.info("------------------ LogAspect init ---------------");
    }

    @Around("execution(public * com.nbf.web.controller..*(..))")
    public Object writeLog(ProceedingJoinPoint pjp){
        Object obj = null;
        logger.debug("------------------ log aspect before -----------------");
        try {
            obj = pjp.proceed();
        }catch (Throwable e) {

        }
        logger.debug("------------------ log aspect after -----------------");
        return obj;
    }

}
