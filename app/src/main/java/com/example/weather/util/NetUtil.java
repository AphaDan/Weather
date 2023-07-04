package com.example.weather.util;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetUtil {
    public static final String URL_SEARCHWEATHER_WITH_FUTURE ="http://t.weather.itboy.net/api/weather/city/";
    public static String doGet(String urlStr){
        String result = "";
        HttpURLConnection connection = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        // 连接网络
        try {
            URL urL = new URL(urlStr);
            connection = (HttpURLConnection) urL.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);//5s

            // 从连接中读取数据(二进制)
            InputStream inputStream = connection.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream);

            // 二进制流送入缓冲区
            bufferedReader = new BufferedReader(inputStreamReader);

            // 从缓存区中一行行读取字符串
            StringBuilder stringBuilder = new StringBuilder();//拼接字符串
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            result = stringBuilder.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }finally {//关闭
            if (connection != null) {
                connection.disconnect();
            }
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public  static String getWeatherofCity(String ctiyid){
        // 拼接出获取天气数据的URL
        //http://t.weather.itboy.net/api/weather/city/
        String weatherUrl = URL_SEARCHWEATHER_WITH_FUTURE + ctiyid;
        String weatherResult = doGet(weatherUrl);
        Log.d("fan", "----weatherResult----" + weatherResult);
        return weatherResult;
    }
}
