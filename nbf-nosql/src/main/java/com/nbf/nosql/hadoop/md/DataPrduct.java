package com.nbf.nosql.hadoop.md;

import com.nbf.common.util.CommonUtil;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * User: Administrator
 * Date: 16-2-6
 * Time: 下午9:09
 */
public class DataPrduct {

    public void createData(){
        String[] locales = {"北京","天津","上海","重庆","香港","台湾"};
        StringBuilder sb = new StringBuilder(1024);
        for(int year=1990;year<=2015;year++){
            for(String locale : locales){
                sb.append(year).append(" ").append(locale).append(" ").append(CommonUtil.randomInt(0,20)).append("\r");
            }
        }
        try {
            FileUtils.writeStringToFile(new File("E:\\test\\temperature.txt"),sb.toString(),"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        DataPrduct dp = new DataPrduct();
        dp.createData();
    }

}
