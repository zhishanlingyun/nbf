package com.nbf.framework.decorate;

import com.nbf.common.XmlUtil;
import com.nbf.framwork.decorate.XmlDecorate;
import com.nbf.framwork.decorate.tags.DecorateXmlTag;
import com.nbf.framwork.decorate.tags.XmlTag;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 15-11-27
 * Time: 下午9:40
 * To change this template use File | Settings | File Templates.
 */
public class XmlTest {

    private XStream xstream=new XStream(new DomDriver("utf-8"));

    @Before
    public void setUp(){
        //xstream.processAnnotations(new Class[]{DecorateXmlTag.class,XmlDecorate.class});
        xstream.autodetectAnnotations(true);
    }

    @Test
    public void decorateXmlTest(){
        DecorateXmlTag decorateXmlTag1 = new DecorateXmlTag();
        decorateXmlTag1.setTemplet("/temple1.htm");
        List<String> paths = new ArrayList<String>();
        paths.add("/xxx/*");
        paths.add("/kkk.htm");
        decorateXmlTag1.setPaths(paths);

        DecorateXmlTag decorateXmlTag2 = new DecorateXmlTag();
        decorateXmlTag2.setTemplet("/temple2.htm");
        List<String> paths2 = new ArrayList<String>();
        paths2.add("/xxx/xx/*");
        paths2.add("/kkk2.htm");
        paths2.add("/kkk3.htm");
        decorateXmlTag2.setPaths(paths2);

        XmlDecorate xmlDecorate = new XmlDecorate();
        List<XmlTag> decorateXmlTags = new ArrayList<XmlTag>();
        decorateXmlTags.add(decorateXmlTag1);
        decorateXmlTags.add(decorateXmlTag2);
        xmlDecorate.setXmlTags(decorateXmlTags);
        String xml = XmlUtil.toXml(xmlDecorate);
        System.out.println(xml);
    }

    @Test
    public void XmlToObjTest(){
        URL path = XmlUtil.class.getClassLoader().getResource("decorate.xml");
        System.out.println(path);
        try {
            File file = new File(path.toURI());
            XmlDecorate xmlDecorate = XmlUtil.toObj(file,new Class[]{XmlDecorate.class,DecorateXmlTag.class});
            System.out.println(xmlDecorate);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

}
