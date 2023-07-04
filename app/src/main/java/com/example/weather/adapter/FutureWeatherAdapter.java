package com.example.weather.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weather.R;
import com.example.weather.bean.ForecastBean;


import java.util.List;

public class FutureWeatherAdapter extends RecyclerView.Adapter<FutureWeatherAdapter.WeatherViewHolder> {

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    private Context mContext;
    private List<ForecastBean> mWeatherBeans;

    public FutureWeatherAdapter(Context context, List<ForecastBean> weatherBeans) {
        mContext = context;
        this.mWeatherBeans = weatherBeans;
    }

    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.weather_item_layout, parent, false);
        WeatherViewHolder weatherViewHolder = new WeatherViewHolder(view);
        return weatherViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder holder, int position) {
        ForecastBean weatherBean = mWeatherBeans.get(position);

        holder.tvTianqi.setText(weatherBean.getType());
        holder.tvWendu.setText(weatherBean.getHigh());
        holder.tvFeng.setText(weatherBean.getFx() + weatherBean.getFl());
        holder.ivTianqi.setImageResource(getImgResOfWeather(weatherBean.getType()));
        holder.tvShijian.setText(weatherBean.getYmd()+weatherBean.getWeek());
    }

    @Override
    public int getItemCount() {

        return (mWeatherBeans == null) ? 0 : mWeatherBeans.size();
    }


    class WeatherViewHolder extends RecyclerView.ViewHolder {

        TextView tvTianqi, tvWendu, tvShijian, tvFeng;
        ImageView ivTianqi;

        public WeatherViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTianqi = itemView.findViewById(R.id.tv_tianqi);
            tvWendu= itemView.findViewById(R.id.tv_wendu);
            tvFeng = itemView.findViewById(R.id.tv_feng);
            ivTianqi = itemView.findViewById(R.id.iv_tianqi);
            tvShijian = itemView.findViewById(R.id.tv_riqi);
        }


    }

    private int getImgResOfWeather(String wea) {
        // xue、lei、shachen、wu、bingbao、yun、yu、yin、qing
        int result = 0;
        switch (wea) {
            case "暴雪":
                result = R.drawable.biz_plugin_weather_baoxue;
                break;
            case "暴雨":
                result = R.drawable.biz_plugin_weather_baoyu;
                break;
            case "大暴雨":
                result = R.drawable.biz_plugin_weather_dabaoyu;
                break;
            case "大雪":
                result = R.drawable.biz_plugin_weather_daxue;
                break;
            case "大雨":
                result = R.drawable.biz_plugin_weather_dayu;
                break;
            case "多云":
                result = R.drawable.biz_plugin_weather_duoyun;
                break;
            case "雷阵雨":
                result = R.drawable.biz_plugin_weather_leizhenyu;
                break;
            case "雷阵雨冰雹":
                result = R.drawable.biz_plugin_weather_leizhenyubingbao;
                break;
            case "晴":
                result = R.drawable.biz_plugin_weather_qing;
                break;
            case "沙尘暴":
                result = R.drawable.biz_plugin_weather_shachenbao;
                break;
            case "特大暴雨":
                result = R.drawable.biz_plugin_weather_tedabaoyu;
                break;
            case "雾":
                result = R.drawable.biz_plugin_weather_wu;
                break;
            case "霾":
                result = R.drawable.biz_plugin_weather_wu;
                break;
            case "小雪":
                result = R.drawable.biz_plugin_weather_xiaoxue;
                break;
            case "小雨":
                result = R.drawable.biz_plugin_weather_xiaoyu;
                break;
            case "阴":
                result = R.drawable.biz_plugin_weather_baoxue;
                break;
            case "雨夹雪":
                result = R.drawable.biz_plugin_weather_yin;
                break;
            case "阵雪":
                result = R.drawable.biz_plugin_weather_zhenxue;
                break;
            case "阵雨":
                result = R.drawable.biz_plugin_weather_zhenyu;
                break;
            case "中雪":
                result = R.drawable.biz_plugin_weather_zhongxue;
                break;
            case "中雨":
                result = R.drawable.biz_plugin_weather_zhongyu;
                break;

        }
            return result;


    }
}
