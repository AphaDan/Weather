package com.example.weather;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weather.adapter.FutureWeatherAdapter;
import com.example.weather.bean.ForecastBean;
import com.example.weather.bean.CollectBean;
import com.example.weather.bean.WeatherBean;
import com.example.weather.db.Collectdp;
import com.example.weather.util.NetUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class CollectActivity extends AppCompatActivity{

    private ForecastBean todayforecast;
    private ListView mList;

    private int mPosition = 0;
    private TextView tvWeather,tvTem,tvTemLowHight,tvWin, tvAir,tvTip,testtime,tvcity;

    private ImageView ivWeather;
    private  RecyclerView rlvSearchFutureWeather;
    private  RecyclerView rlvFutureWeather;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.collect_item_layout);

        mList = (ListView) this.findViewById(R.id.lv_dl);

        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("Range")
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
             search(Collectdp.CONTENT.toString());
            }
        });
    }
    public void initView(){
        tvWeather = findViewById(R.id.tv_weather);
        tvAir = findViewById(R.id.tv_air);
        tvTem = findViewById(R.id.tv_tem);
        tvTemLowHight = findViewById(R.id.tv_tem_low_high);
        tvTip=findViewById(R.id.tv_tip);
        tvWin = findViewById(R.id.tv_win);
        ivWeather = findViewById(R.id.iv_weather);
        testtime = findViewById(R.id.test_time);
        tvcity = findViewById(R.id.tv_city);
        rlvSearchFutureWeather = findViewById(R.id.rlv_future_weather);

    }
    public void search(String citycode){
        //开启子线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                //请求网络
                String weatherofCity = NetUtil.getWeatherofCity(citycode);
                //使用handler将数据传给主线程
                Message message = Message.obtain();//比new Message();效率高
                message.what = 0;
                message.obj = weatherofCity;
                mHandler.sendMessage(message);
            }
        }).start();
    }
    private Handler mHandler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if(msg.what == 0){
                String weather = (String)msg.obj;
                //数据解析
                Gson gson = new Gson();
                WeatherBean weatherBean =gson.fromJson(weather,WeatherBean.class);
                updateUiofWeather(weatherBean);
            }
        }
    };
    private void updateUiofWeather(WeatherBean weatherBean) {
        //将数据填充到界面上
        //判空
        if(weatherBean==null){
            return;
        }

        List<ForecastBean> forecasts = weatherBean.getDataBeans().getForecastBeansts();
        todayforecast  = forecasts.get(0);
        if (todayforecast == null) {
            return;
        }

        tvTem.setText(weatherBean.getDataBeans().getWendu()+"℃");
        tvcity.setText(weatherBean.getCityInfoBeans().getCity()+" "+weatherBean.getCityInfoBeans().getParent());
        tvWeather.setText(todayforecast.getType()+"（"+todayforecast.getYmd()+todayforecast.getWeek()+")");
        tvTemLowHight.setText(todayforecast.getLow()+"~"+todayforecast.getHigh());
        tvWin.setText(todayforecast.getFx()+todayforecast.getFl()+" 湿度"+weatherBean.getDataBeans().getShidu());
        tvAir.setText(weatherBean.getDataBeans().getPm25()+""+weatherBean.getDataBeans().getQuality());
        tvTip.setText("感冒指数："+weatherBean.getDataBeans().getGanmao()+"\nTip:"+todayforecast.getNotice());
        ivWeather.setImageResource(getImageofWeather(todayforecast.getType()));
        forecasts.remove(0);

        // 未来天气的展示
        final  FutureWeatherAdapter mSearchNextWeatherAdapter = new FutureWeatherAdapter(this, forecasts);
        LinearLayoutManager mManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rlvSearchFutureWeather.setLayoutManager(mManager);
        rlvSearchFutureWeather.setAdapter(mSearchNextWeatherAdapter);

    }

    private int getImageofWeather(String weaStr){
        //baoxue,baoyu,dabaoyu,daxue,dayu,duoyun,leizhenyu,leizhenyubingbao,qing,shachenbao,tedabaoyu,
        // wu,xiaoxue,yin,yujiaxue,zhenxue,zhenyu,zhongxue,zhongyu
        int result =0;
        switch (weaStr){
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
            case "霾":
                result = R.drawable.biz_plugin_weather_wu;
                break;

        }
        return result;
    }

    public void showToast(String Message){
        Toast.makeText(CollectActivity.this,Message,Toast.LENGTH_SHORT).show();
    }

}

