package com.nbf.dto;

/**
 * User: Administrator
 * Date: 16-1-25
 * Time: 下午10:02
 */
public class TemperatureModel implements ChartModel{
    public enum TimeType{ DAY,MONTH,YEAR}
    private String area;
    private float[] temperature;
    private TimeType timeType;

    @Override
    public Object buildData() {
        return null;
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
}
