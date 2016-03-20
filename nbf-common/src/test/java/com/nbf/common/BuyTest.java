package com.nbf.common;

import com.nbf.common.util.HttpUtil;
import org.apache.http.client.CookieStore;
import org.apache.http.cookie.Cookie;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/8 0008.
 */
public class BuyTest {



    public static void main(String[] args){

        String url = "https://plogin.m.jd.com/cgi-bin/m/domlogin";
        String str_rsaString = "DA6A7EAA790172FD01F25288C2488375DD0808A1100B1ED416ADC7A2A482BD8E1406C6D3C1709E06C90C76BBA85B85350E5F5F8C0784346CE557646D9E6348819A0FBC98A06C80E14E11B33F19FD0C9450D53F0EE08BD2CBF0694024986ADC5420544F3CB11144381527B7061BDD488A8604AF532F12D4D49ABEAC1BB25BF241";

        Map<String,String> map = new HashMap<String,String>();
        map.put("username", "zhishanlingyun");
        map.put("remember","true");
        map.put("s_token","lpxthpna");
        map.put("pwd","RX6zJB4AHgubfpp6BSzIOCTvFntbPGQexyp6Gw7FTWCtF0cxy0vGps50aBGrxvV28+7f2ZC+MOYIDODrvx+qRR0enH3EFPxyErSnXQyXuBfmmvrhizYuB3DIFSh2MF+mk8Ni7CWshGSLLiJ4G40wB4EcI3CnoRnfH1LtWZHqiVY=");
        CookieStore cookie = HttpUtil.getLoginCookie(url,map);
        List<Cookie> cookies = cookie.getCookies();
        for(Cookie c : cookies){
            System.out.println(c.getName());
        }
        HttpUtil.doPost(url,map,null);


    }
}
