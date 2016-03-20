package com.nbf.common;

import com.nbf.common.util.Ponit;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/2/27 0027.
 */
public class MapTest {

    public static void main(String[] args){
        Ponit p1 = new Ponit(1,2);
        Map<String,Ponit> map = new HashMap<String,Ponit>();
        map.put("p1",p1);
        System.out.println(p1);
        System.out.println(map.get("p1"));
        System.out.println(p1==map.get("p1"));
        p1.setX(10);
        System.out.println("=======================================");
        System.out.println(p1);
        System.out.println(map.get("p1"));
        System.out.println(p1==map.get("p1"));
        map.get("p1").setX(6);
        System.out.println("=======================================");
        System.out.println(p1);
        System.out.println(map.get("p1"));
        System.out.println(p1==map.get("p1"));

    }

}
