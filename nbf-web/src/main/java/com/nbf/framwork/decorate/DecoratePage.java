package com.nbf.framwork.decorate;

/**
 * User: Administrator
 * Date: 15-11-27
 * Time: 下午8:17
 * 被装饰页面
 */
public class DecoratePage {

    private String header;
    private String title;
    private String body;

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
