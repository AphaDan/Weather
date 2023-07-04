package com.example.weather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weather.adapter.FutureWeatherAdapter;
import com.example.weather.bean.CollectBean;
import com.example.weather.bean.ForecastBean;
import com.example.weather.bean.WeatherBean;
import com.example.weather.db.Collectdp;
import com.example.weather.util.NetUtil;
import com.google.gson.Gson;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private AppCompatSpinner mSpinner;
    private ArrayAdapter<String> mSpAdapter;
    private String[] mCities;

    private ForecastBean todayforecast;
    private TextView tvWeather,tvTem,tvTemLowHight,tvWin, tvAir,tvTip,testtime,tvcity;

    private ImageView ivWeather;

    private  RecyclerView rlvFutureWeather;
   // private List<CollectAdaper> starList;

    private static String citycodenow = null;

    private Collectdp mDb;
    private SQLiteDatabase mSqldb;

    public static String getCitycodenow() {
        return citycodenow;
    }

    public static void setCitycodenow(String citycodenow) {
        MainActivity.citycodenow = citycodenow;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        mSpinner = findViewById(R.id.sp_city);
        mCities = getResources().getStringArray(R.array.cities);
        mSpAdapter = new ArrayAdapter<>(this, R.layout.sp_item_layout, mCities);
        mSpinner.setAdapter(mSpAdapter);
        mDb = new Collectdp(this);
        mSqldb = mDb.getWritableDatabase();//获取可读写SQLiteDatabase对象
        ImageView collect = (ImageView) findViewById(R.id.collect_button);
        ImageView search = (ImageView) findViewById(R.id.search_button);
        ImageView refresh = (ImageView)findViewById(R.id.refresh_button);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCity = mCities[position];
                getWeatherofCity(selectedCity);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        refresh.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //刷新
                refresh(citycodenow);
                Log.d("fan", "-citycodenow---" +getCitycodenow());
                //这是一个假的刷新❤企图只更新时间骗人❤
               // Date date = new Date();
                //SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
               // testtime.setText("数据更新时间："+dateFormat.format(date));
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //转至搜索页面
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivityForResult(intent, 1);
            }
        });
       collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //短按收藏城市
                Log.d("fan", "-citycodenow---" +getCitycodenow());
                DbAdd();
                Log.d("fan", "-cv---" +Collectdp.CONTENT.toString());
            }
        });
        collect.setOnLongClickListener(new View.OnLongClickListener() {
            //长按进入收藏列表
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent(MainActivity.this, CollectActivity.class);
                startActivityForResult(intent, 1);
                return false;
            }
        });
        tvWeather = findViewById(R.id.tv_weather);
        tvAir = findViewById(R.id.tv_air);
        tvTem = findViewById(R.id.tv_tem);
        tvTemLowHight = findViewById(R.id.tv_tem_low_high);
        tvTip=findViewById(R.id.tv_tip);
        tvWin = findViewById(R.id.tv_win);
        ivWeather = findViewById(R.id.iv_weather);
        testtime = findViewById(R.id.test_time);
        tvcity = findViewById(R.id.tv_city);
        rlvFutureWeather = findViewById(R.id.rlv_future_weather);
    }

    private Handler mHandler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            Log.d("fan", "-msg---" +msg);
            if(msg.what == 0){
                String weather = (String)msg.obj;
                //数据解析
               Gson gson = new Gson();
               WeatherBean  weatherBean =gson.fromJson(weather,WeatherBean.class);
                Log.d("fan", "--解析后的数据-weather---" + weatherBean);
              updateUiofWeather(weatherBean);
            }
            Toast.makeText(MainActivity.this, "更新天气~", Toast.LENGTH_SHORT).show();
        }
    };
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
        testtime.setText("数据更新时间："+weatherBean.getTime());

        forecasts.remove(0);

        // 未来天气的展示
        final  FutureWeatherAdapter mNextWeatherAdapter = new FutureWeatherAdapter(this, forecasts);
        LinearLayoutManager Manager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rlvFutureWeather.setLayoutManager(Manager);
        rlvFutureWeather.setAdapter(mNextWeatherAdapter);
    }




    public void getWeatherofCity(String selectedCity) {
        //开启子线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                //请求网络
                String weatherofCity = NetUtil.getWeatherofCity(selectedCity);
                setCitycodenow(selectedCity);
                //使用handler将数据传给主线程
                Message message = Message.obtain();//比new Message();效率高
                message.what = 0;
                message.obj = weatherofCity;
                mHandler.sendMessage(message);
            }
        }).start();
    }
    public void refresh(String citycode){
        //开启子线程
        getWeatherofCity(citycode);

    }
    public void DbAdd() {
        ContentValues cv = new ContentValues();
        cv.put(Collectdp.CONTENT,citycodenow);
        cv.put(Collectdp.TIME,getTime());
        mSqldb.insert(Collectdp.TABLE_NAME,null,cv);
    }

    public String getTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date date = new Date();
        String str = sdf.format(date);
        return str;
    }
}