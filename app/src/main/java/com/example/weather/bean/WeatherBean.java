package com.example.weather.bean;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

 /*"message":"success感谢又拍云(upyun.com)提供CDN赞助",
         "status":200,
         "date":"20230702",
         "time":"2023-07-02 13:00:52",
         "cityInfo":{
         "city":"天津市",
         "citykey":"101030100",
         "parent":"天津",
         "updateTime":"11:16"
         },
         "data":{
         "shidu":"41%",
         "pm25":30,
         "pm10":56,
         "quality":"良",
         "wendu":"37",
         "ganmao":"极少数敏感人群应减少户外活动",
         "forecast":Array[15],
        */
public class WeatherBean implements Serializable {

    @SerializedName("time")
    private String time;

     @SerializedName("cityInfo")
     private cityInfoBean cityInfoBeans;
     @SerializedName("data")
     private DataBean dataBeans;

     public DataBean getDataBeans() {
         return dataBeans;
     }

     @Override
     public String toString() {
         return "WeatherBean{" +
                 "time='" + time + '\'' +
                 ", cityInfoBeans=" + cityInfoBeans +
                 ", dataBeans=" + dataBeans +
                 '}';
     }

     public void setDataBeans(DataBean dataBeans) {
         this.dataBeans = dataBeans;
     }

     public cityInfoBean getCityInfoBeans() {
         return cityInfoBeans;
     }

     public void setCityInfoBeans(cityInfoBean cityInfoBeans) {
         this.cityInfoBeans = cityInfoBeans;
     }

     public String getTime() {
         return time;
     }

     public void setTime(String time) {
         this.time = time;
     }

 }

