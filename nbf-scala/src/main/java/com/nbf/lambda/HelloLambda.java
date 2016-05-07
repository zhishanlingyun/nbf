package com.nbf.lambda;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

/**
 */
public class HelloLambda {

    //private static Log logger = LogFactory.getLog("lambda");//Logger.getLogger("lambda");
    private static Logger logger = Logger.getLogger("lambda");

    public static void compare(){
        List<String> names = asList("peter", "anna", "mike", "xenia");
        Collections.sort(names,(String a,String b)->b.compareTo(a));
        System.out.print(names);
    }

    public static void getList(){
        List<String> strlist = new ArrayList<String>();
        strlist.add("aaa");
        strlist.add("bbb");
        strlist.add("ccc");
        strlist.add("ccc");
        strlist.add("aaa");
        strlist.stream().forEach(str -> System.out.print(str + "\t"));
        System.out.println();
        strlist.stream().distinct().forEach(str -> System.out.print(str + "\t"));
        List<String> newList = strlist.stream().map(item -> item.toUpperCase()).collect(toList());
        System.out.println();
        newList.forEach(str -> System.out.print(str + "\t"));
    }

    public static void testFlatMap(){
        List<Integer> to = Stream.of(asList(1, 2), asList(3, 4), asList(3, 4)).flatMap(num -> num.stream()).collect(toList());
        to.forEach(t -> System.out.print(t + "\t"));
    }

    public static void testReduce() {
        int count = Stream.of(1, 2, 3, 4, 5, 6,6,6).reduce(0, (a, b) -> a + b);
        System.out.println(count);
    }

    public static void outLog(){
        logger.info("kkkkkkkkkkkkkkkkkkkkk");
    }





    public static void main(String[] args){

        outLog();
        //HelloLambda.compare();
        /*Runnable run = ()->{System.out.println(Thread.currentThread().getName());};
        for(int i=0;i<10;i++)
        new Thread(run).start();*/
        //HelloLambda.getList();
        //HelloLambda.testFlatMap();
        //HelloLambda.testReduce();
    }

}
