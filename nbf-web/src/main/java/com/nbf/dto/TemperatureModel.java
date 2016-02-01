package com.nbf.dto;

import com.nbf.common.util.CommonUtil;

/**
 * User: Administrator
 * Date: 16-1-25
 * Time: 下午10:02
 */
public class TemperatureModel implements ChartModel{
    public enum TimeType{ DAY,MONTH,YEAR}
    private String area;
    private float[] temperature;
    private String[] timeVector;
    private TimeType timeType;

    @Override
    public Object buildData() {
        if(timeType.equals(TimeType.MONTH)){
            temperature = new float[12];
            timeVector = new String[12];
            for(int i=0;i<12;i++){
                temperature[i] = CommonUtil.randomInt(-20,40);
                timeVector[i] = (i+1)+"月";
            }
        }
        return this;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public TimeType getTimeType() {
        return timeType;
    }

    public void setTimeType(TimeType timeType) {
        this.timeType = timeType;
    }

    public float[] getTemperature() {
        return temperature;
    }

    public void setTemperature(float[] temperature) {
        this.temperature = temperature;
    }

    public String[] getTimeVector() {
        return timeVector;
    }

    public void setTimeVector(String[] timeVector) {
        this.timeVector = timeVector;
    }
}
