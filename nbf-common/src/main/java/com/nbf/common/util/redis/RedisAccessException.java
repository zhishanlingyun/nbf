package com.nbf.common.util.redis;

/**
 * User: root
 * Date: 1/5/16
 * Time: 11:28 PM
 */
public class RedisAccessException extends Exception {

    public RedisAccessException(String msg) {
        super(msg);
    }

    /**
     * Constructor for DataAccessException.
     * @param msg the detail message
     * @param cause the root cause (usually from using a underlying
     * data access API such as JDBC)
     */
    public RedisAccessException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public RedisAccessException(Throwable cause) {
        super( cause);
    }
}
