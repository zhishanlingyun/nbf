package com.nbf.framwork.decorate;

import com.nbf.framwork.decorate.tags.XmlTag;

import java.util.Map;
import java.util.Set;

/**
 * User: Administrator
 * Date: 15-11-28
 * Time: 上午9:11
 * 标签解析器
 */
public interface TagResolver {

    //解析器可以支持的解析的标签
    public Class support();

    //解析标签
    public Map<String,Set<String>> resolve(XmlTag tag);

}
