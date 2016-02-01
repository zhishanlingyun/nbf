package com.nbf.framework.decorate;

import com.nbf.common.util.code.JsonUtil;
import com.nbf.dto.TemperatureModel;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Administrator
 * Date: 16-1-31
 * Time: 下午9:40
 */
public class JsonTest {

    @Test
    public void testJson(){
        TemperatureModel temperatureModel = new TemperatureModel();
        temperatureModel.setTimeType(TemperatureModel.TimeType.MONTH);
        temperatureModel.setArea("北京");
        temperatureModel = (TemperatureModel)temperatureModel.buildData();
        TemperatureModel temperatureModel2 = new TemperatureModel();
        temperatureModel2.setTimeType(TemperatureModel.TimeType.MONTH);
        temperatureModel2.setArea("甘肃");
        temperatureModel2 = (TemperatureModel)temperatureModel2.buildData();
        List<TemperatureModel> list = new ArrayList<TemperatureModel>();
        list.add(temperatureModel);
        list.add(temperatureModel2);
        String json = JsonUtil.obj2Json(list);
        System.out.println(json);
    }

}
