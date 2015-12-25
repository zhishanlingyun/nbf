package com.nbf.framwork.decorate.tagresolver;

import com.nbf.framwork.decorate.TagResolver;
import com.nbf.framwork.decorate.tags.DecorateXmlTag;
import com.nbf.framwork.decorate.tags.XmlTag;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * User: Administrator
 * Date: 15-11-28
 * Time: 上午10:28
 */
public class DecorateTagResolver implements TagResolver {

    @Override
    public Class support() {
        return DecorateXmlTag.class;
    }

    @Override
    public Map<String, Set<String>> resolve(XmlTag tag) {
        Map<String, Set<String>> map = new HashMap<String, Set<String>>();
        if(tag instanceof DecorateXmlTag){
            DecorateXmlTag decorateXmlTag = (DecorateXmlTag)tag;
            Set<String> paths = new TreeSet<String>();
            paths.addAll(decorateXmlTag.getPaths());
            map.put(decorateXmlTag.getTemplet(),paths);
        }
        return map;
    }
}
