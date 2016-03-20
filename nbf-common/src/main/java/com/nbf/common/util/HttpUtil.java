package com.nbf.common.util;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * Created by Administrator on 2016/3/8 0008.
 */
public class HttpUtil {

    final private static Map<String,Object> doPost(String url,String code,Map<String, String> parmas,CookieStore cookie){

        if(null==url||"".equals(url)){
            return null;
        }

        BasicHttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, 10000);
        HttpConnectionParams.setSoTimeout(httpParams, 10000);
        DefaultHttpClient httpclient = new DefaultHttpClient(httpParams);
        HttpPost httpPost = new HttpPost(url);
        ArrayList<BasicNameValuePair> pairs = new ArrayList<BasicNameValuePair>();
        if (parmas != null) {
            Set<String> keys = parmas.keySet();
            for (Iterator<String> i = keys.iterator(); i.hasNext();) {
                String key = (String) i.next();
                pairs.add(new BasicNameValuePair(key, parmas.get(key)));
            }
        }
        UrlEncodedFormEntity p_entity = null;
        try {
            if(null==code)
                p_entity= new UrlEncodedFormEntity(pairs, "utf-8");
            else
                p_entity= new UrlEncodedFormEntity(pairs, code);

            //��POST���ݷ���HTTP����
            httpPost.setEntity(p_entity);
            if(null!=cookie)
                httpclient.setCookieStore(cookie);
            //����ʵ�ʵ�HTTP POST����
            HttpResponse response = httpclient.execute(httpPost);
            System.out.println(response);
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("response", response);
            map.put("httpclient", httpclient);
            return map;
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            httpclient.getConnectionManager().shutdown();
        }

        return null;
    }

    public static Object doPost(String url,Map<String, String> parmas,CookieStore cookie){

        Map<String,Object> map = doPost(url, null, parmas, cookie);
        try {
            InputStream in = ((HttpResponse)map.get("response")).getEntity().getContent();

            return IOUtils.toString(in);
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static CookieStore getLoginCookie(String url,Map<String, String> parmas){

        Map<String,Object> map = doPost(url, null, parmas, null);

        if(null!=map){
            DefaultHttpClient httpclient = (DefaultHttpClient)map.get("httpclient");
            return httpclient.getCookieStore();
        }

        return null;
    }

    public static Map<String,Object> doGet(String url,CookieStore cookie){
        BasicHttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, 10000);
        HttpConnectionParams.setSoTimeout(httpParams, 10000);
        DefaultHttpClient httpclient = new DefaultHttpClient(httpParams);
        HttpGet httpGet = new HttpGet(url);
        try {
            httpGet.setParams(httpParams);
            //����ʵ�ʵ�HTTP GET����
            httpclient.setCookieStore(cookie);
            HttpResponse response = httpclient.execute(httpGet);
            System.out.println(response);
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("response", response);
            map.put("httpclient", httpclient);
            return map;

        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
