package com.nbf.common;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.File;
import java.io.InputStream;

/**
 * User: Administrator
 * Date: 15-11-27
 * Time: 下午11:02
 * Xml解析工具类,Xstream实现
 */
public class XmlUtil {

    private static XStream xstream = new XStream(new DomDriver("utf-8"));

    static {
        xstream.autodetectAnnotations(true);
    }

    public static <T> T toObj(String xml){
        return (T)xstream.fromXML(xml);
    }

    public static <T> T toObj(File file){
        return (T)xstream.fromXML(file);
    }

    public static <T> T toObj(InputStream in){
        return (T)xstream.fromXML(in);
    }

    public static String toXml(Object obj){
        return xstream.toXML(obj);
    }

    private static void unAutodetectAnnotations(){
        xstream.autodetectAnnotations(false);
    }

    public static <T> T toObj(String xml,Class[] clazzs){
        unAutodetectAnnotations();
        xstream.processAnnotations(clazzs);
        return (T)xstream.fromXML(xml);
    }

    public static <T> T toObj(File file,Class[] clazzs){
        unAutodetectAnnotations();
        xstream.processAnnotations(clazzs);
        return (T)xstream.fromXML(file);
    }



}
