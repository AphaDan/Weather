package com.example.weather.bean;

public class CollectBean {
    private String citycode;

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    @Override
    public String toString() {
        return "CollectBean{" +
                "citycode='" + citycode + '\'' +
                '}';
    }
}
