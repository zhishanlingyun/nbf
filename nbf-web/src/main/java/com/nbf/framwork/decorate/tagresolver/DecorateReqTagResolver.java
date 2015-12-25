package com.nbf.framwork.decorate.tagresolver;

import com.nbf.framwork.decorate.TagResolver;
import com.nbf.framwork.decorate.tags.DecorateReqXmlTag;
import com.nbf.framwork.decorate.tags.XmlTag;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * User: Administrator
 * Date: 15-11-29
 * Time: 下午10:04
 */
public class DecorateReqTagResolver implements TagResolver {

    @Override
    public Class support() {
        return DecorateReqXmlTag.class;
    }

    @Override
    public Map<String, Set<String>> resolve(XmlTag tag) {
        Map<String, Set<String>> map = new HashMap<String, Set<String>>();
        if(tag instanceof DecorateReqXmlTag){
            DecorateReqXmlTag decorateXmlTag = (DecorateReqXmlTag)tag;
            Set<String> reqs = new TreeSet<String>();
            reqs.addAll(decorateXmlTag.getReq());
            map.put(decorateXmlTag.getTemplet(),reqs);
        }
        return map;
    }
}
