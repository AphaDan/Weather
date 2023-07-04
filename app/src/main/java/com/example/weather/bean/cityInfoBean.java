package com.example.weather.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

    /*"cityInfo":{
         "city":"天津市",
         "citykey":"101030100",
         "parent":"天津",
         "updateTime":"11:16"
         },*/
    public class cityInfoBean implements Serializable {
        @SerializedName("city")
        private String city;
        @SerializedName("citykey")
        private String citykey;
        @SerializedName("parent")
        private String parent;
        @SerializedName("updateTime")
        private String updateTime;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCitykey() {
            return citykey;
        }

        public void setCitykey(String citykey) {
            this.citykey = citykey;
        }

        public String getParent() {
            return parent;
        }

        public void setParent(String parent) {
            this.parent = parent;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        @Override
        public String toString() {
            return "cityInfoBean{" +
                    "city='" + city + '\'' +
                    ", citykey='" + citykey + '\'' +
                    ", parent='" + parent + '\'' +
                    ", updateTime='" + updateTime + '\'' +
                    '}';
        }
    }
