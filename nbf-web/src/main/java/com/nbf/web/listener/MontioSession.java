package com.nbf.web.listener;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * User: Administrator
 * Date: 15-12-12
 * Time: 下午11:08
 */
public class MontioSession implements HttpSessionListener {

    private static Logger logger = Logger.getLogger(MontioSession.class);

    private AtomicInteger count = new AtomicInteger(0);

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        count.incrementAndGet();
        logger.info("create session,current count : "+count);
        HttpSession session = httpSessionEvent.getSession();
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        count.decrementAndGet();
        logger.info("destroyed session,current count : "+count);
    }
}
