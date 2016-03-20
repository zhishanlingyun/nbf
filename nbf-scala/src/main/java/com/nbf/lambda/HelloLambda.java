package com.nbf.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

/**
 */
public class HelloLambda {

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
        List<Integer> to = Stream.of(asList(1, 2), asList(3, 4),asList(3, 4)).flatMap(num -> num.stream()).collect(toList());
        to.forEach(t -> System.out.print(t + "\t"));
    }

    public static void testReduce(){
        int count = Stream.of(1,2,3,4,5,6,6,6).reduce(0, (a, b) -> a + b);
        System.out.println(count);
    }





    public static void main(String[] args){
        //HelloLambda.compare();
        /*Runnable run = ()->{System.out.println(Thread.currentThread().getName());};
        for(int i=0;i<10;i++)
        new Thread(run).start();*/
        //HelloLambda.getList();
        //HelloLambda.testFlatMap();
        HelloLambda.testReduce();
    }

}
