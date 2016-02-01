package com.nbf.web.controller;

import com.nbf.common.util.code.JsonUtil;
import com.nbf.dto.TemperatureModel;
import com.nbf.web.common.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Administrator
 * Date: 16-1-23
 * Time: 上午10:01
 */
@Controller
//@RequestMapping("monitor")
public class MonitorController {

    @RequestMapping("/monitor/index")
    public String index(){
        return "q/monitor";
    }

    @RequestMapping(value="/getcharts",method = RequestMethod.GET )
    @ResponseBody
    public Result getCharts(){
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
        Result result = new Result();
        result.setSuccess(true);
        result.setMsg(json);
        return result;
    }

}
