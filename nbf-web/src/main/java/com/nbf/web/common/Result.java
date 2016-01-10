package com.nbf.web.common;

/**
 * User: Administrator
 * Date: 16-1-9
 * Time: 下午10:03
 */
public class Result{
    private Integer code;
    private String msg;
    private Boolean success;
    private String url;

    public Result() {
    }

    public Result(Integer code, String msg,String url, Boolean success) {
        this.code = code;
        this.msg = msg;
        this.success = success;
        this.url = url;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
