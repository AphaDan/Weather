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
import com.example.weather.bean.CollectBean;
import com.example.weather.bean.ForecastBean;

import java.util.List;

public class collectAdapter {
    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }
    
    protected int onBindLayout() {
        return R.layout.collect_item_layout;
    }
    private Context mContext;
    
    private List<CollectBean> mCollectBean;
    public collectAdapter(Context context, List<CollectBean> mCollectBean) {
        mContext = context;
    }

    public collectViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.collect_item_layout, parent, false);
        collectViewHodler ViewHolder = new collectViewHodler(view);
        return ViewHolder;
    }
    protected void onBindData(collectAdapter.collectViewHodler holder, int positon) {
        holder.tvCollectList.getText();
    }
    
    public int getItemCount() {

        return (mCollectBean == null) ? 0 :mCollectBean.size();
    }
    
    
    static class collectViewHodler extends RecyclerView.ViewHolder {

        
        TextView tvCollectList;

        public collectViewHodler(@NonNull View itemView) {
            super(itemView);
            tvCollectList = itemView.findViewById(R.id.lv_dl);
        }


    }
}
