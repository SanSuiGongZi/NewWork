package com.example.tolovepy.everywheretrip.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.tolovepy.everywheretrip.R;
import com.example.tolovepy.everywheretrip.bean.MapCityBean;

import java.util.List;

public class InlandTopRvAdapter extends RecyclerView.Adapter<InlandTopRvAdapter.ViewHolder> {
    List<MapCityBean.ResultBean.ChinaBean.TopCitiesBean> topCities;
    Context mContext;

    public InlandTopRvAdapter(List<MapCityBean.ResultBean.ChinaBean.TopCitiesBean> topCities, Context context) {
        this.topCities = topCities;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_inland_top_rv, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final MapCityBean.ResultBean.ChinaBean.TopCitiesBean topCitiesBean = topCities.get(i);
        viewHolder.city.setText(topCitiesBean.getName());

        viewHolder.city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSendData!=null){
                    mSendData.sendData(topCitiesBean.getId(),topCitiesBean.getName(),topCitiesBean.getLatitude(),topCitiesBean.getLongitude());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return topCities.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private Button city;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            city=itemView.findViewById(R.id.city);
        }
    }

    SendData mSendData;

    public void setSendData(SendData sendData) {
        mSendData = sendData;
    }

    public interface SendData{
        void sendData(int id, String name, double latitude, double longitude);
    }
}
