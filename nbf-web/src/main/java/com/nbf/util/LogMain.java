package com.nbf.util;

import org.apache.log4j.Logger;

/**
 * Created by Administrator on 2016/5/18 0018.
 */
public class LogMain {
    private static Logger logger = Logger.getLogger(LogMain.class);

    public static void main(String[] args){
        logger.info("==============info=============");
        logger.error("=============error===============");
    }


}
