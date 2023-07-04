package com.example.weather.bean;
import java.io.Serializable;
import com.google.gson.annotations.SerializedName;

/*
   *  "forecast": [//今天+未来14天
           {
               "date": "22",
               "ymd": "2018-09-22",   //年月日  （新增）
               "week": "星期六",       //星期 （新增）
               "sunrise": "05:57",
               "high": "高温 26.0℃",
               "low": "低温 15.0℃",
               "sunset": "18:10",
               "aqi": 55.0,
               "fx": "西北风",
               "fl": "4-5级",
               "type": "晴",
               "notice": "愿你拥有比阳光明媚的心情"
           },
   * */
public class ForecastBean {
    @SerializedName("ymd")
    private String ymd;
    @SerializedName("week")
    private String week;
    @SerializedName("sunrise")
    private String sunrise;
    @SerializedName("high")
    private String high;
    @SerializedName("low")
    private String low;
    @SerializedName("sunset")
    private String sunset;
    @SerializedName("fx")
    private String fx;
    @SerializedName("fl")
    private String fl;
    @SerializedName("type")
    private String type;

    @Override
    public String toString() {
        return "ForecastBean{" +
                "ymd='" + ymd + '\'' +
                ", week='" + week + '\'' +
                ", sunrise='" + sunrise + '\'' +
                ", high='" + high + '\'' +
                ", low='" + low + '\'' +
                ", sunset='" + sunset + '\'' +
                ", fx='" + fx + '\'' +
                ", fl='" + fl + '\'' +
                ", type='" + type + '\'' +
                ", notice='" + notice + '\'' +
                '}';
    }

    @SerializedName("notice")
    private String notice;

    public String getYmd() {
        return ymd;
    }

    public void setYmd(String ymd) {
        this.ymd = ymd;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public String getFx() {
        return fx;
    }

    public void setFx(String fx) {
        this.fx = fx;
    }

    public String getFl() {
        return fl;
    }

    public void setFl(String fl) {
        this.fl = fl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }
}
