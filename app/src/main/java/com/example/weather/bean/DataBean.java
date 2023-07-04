package com.example.weather.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/* "shidu":"37%",
        "pm25":24,
        "pm10":45,
        "quality":"轻度",
        "wendu":"36",
        "ganmao":"儿童、老年人及心脏、呼吸系统疾病患者人群应减少长时间或高强度户外锻炼",
        "forecast":[
            {*/
public class DataBean {
    @SerializedName("shidu")
    private String shidu;
    @SerializedName("pm25")
    private String pm25;
    @SerializedName("pm10")
    private String pm10;
    @SerializedName("quality")
    private String quality;
    @SerializedName("wendu")
    private String wendu;
    @SerializedName("ganmao")
    private String ganmao;

    @SerializedName("forecast")
    private List<ForecastBean> forecastBeansts;

    public String getShidu() {
        return shidu;
    }

    public void setShidu(String shidu) {
        this.shidu = shidu;
    }

    public String getPm25() {
        return pm25;
    }

    public void setPm25(String pm25) {
        this.pm25 = pm25;
    }

    public String getPm10() {
        return pm10;
    }

    public void setPm10(String pm10) {
        this.pm10 = pm10;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getWendu() {
        return wendu;
    }

    public void setWendu(String wendu) {
        this.wendu = wendu;
    }

    public String getGanmao() {
        return ganmao;
    }

    public void setGanmao(String ganmao) {
        this.ganmao = ganmao;
    }

    public List<ForecastBean> getForecastBeansts() {
        return forecastBeansts;
    }

    public void setForecastBeansts(List<ForecastBean> forecastBeansts) {
        this.forecastBeansts = forecastBeansts;
    }

    @Override
    public String toString() {
        return "DataBean{" +
                "shidu='" + shidu + '\'' +
                ", pm25='" + pm25 + '\'' +
                ", pm10='" + pm10 + '\'' +
                ", quality='" + quality + '\'' +
                ", wendu='" + wendu + '\'' +
                ", ganmao='" + ganmao + '\'' +
                ", forecastBeansts=" + forecastBeansts +
                '}';
    }
}
