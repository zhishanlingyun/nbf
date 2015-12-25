package com.nbf.framwork.decorate.tags;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * User: Administrator
 * Date: 15-11-29
 * Time: 下午9:52
 */
@XStreamAlias("decorateReq")
public class DecorateReqXmlTag extends XmlTag {

    @XStreamAlias("templet")
    private String templet;

    @XStreamImplicit(itemFieldName="path")
    private List<String> req;

    public String getTemplet() {
        return templet;
    }

    public void setTemplet(String templet) {
        this.templet = templet;
    }

    public List<String> getReq() {
        return req;
    }

    public void setReq(List<String> req) {
        this.req = req;
    }
}
