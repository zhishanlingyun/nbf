package com.nbf.framwork.decorate.tags;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * User: Administrator
 * Date: 15-11-27
 * Time: 下午9:21
 */
@XStreamAlias("decorate")
public class DecorateXmlTag extends XmlTag {

    public String getTemplet() {
        return templet;
    }

    public void setTemplet(String templet) {
        this.templet = templet;
    }

    public List<String> getPaths() {
        return paths;
    }

    public void setPaths(List<String> paths) {
        this.paths = paths;
    }

    @XStreamAlias("templet")
    private String templet;

    @XStreamImplicit(itemFieldName="path")
    private List<String> paths;


}
