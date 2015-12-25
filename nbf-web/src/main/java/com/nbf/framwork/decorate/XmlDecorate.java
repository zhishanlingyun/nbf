package com.nbf.framwork.decorate;

import com.nbf.framwork.decorate.tags.DecorateXmlTag;
import com.nbf.framwork.decorate.tags.XmlTag;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * User: Administrator
 * Date: 15-11-27
 * Time: 下午9:37
 */
@XStreamAlias("decorates")
public class XmlDecorate {
    @XStreamImplicit()
    //List<DecorateXmlTag> decorateXmlTagList;
    List<XmlTag> xmlTags;

    public List<XmlTag> getXmlTags() {
        return xmlTags;
    }

    public void setXmlTags(List<XmlTag> xmlTags) {
        this.xmlTags = xmlTags;
    }
}
