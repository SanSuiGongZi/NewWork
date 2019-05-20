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

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyAdapterInter extends RecyclerView.Adapter<MyAdapterInter.ViewHolder> {

    private Context context;
    private List<MapCityBean.ResultBean.CountriesBean.CitiesBeanX> mList;

    public MyAdapterInter(Context context, List<MapCityBean.ResultBean.CountriesBean.CitiesBeanX> list) {
        this.context = context;
        mList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_city, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final MapCityBean.ResultBean.CountriesBean.CitiesBeanX beanX = mList.get(i);

        viewHolder.mHotCity.setText(beanX.getName());

        viewHolder.mHotCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClick != null) {
                    mOnItemClick.setOnItemClick(beanX);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mList == null) {
            return 0;
        } else {

            return mList.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.mHotCity)
        Button mHotCity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private OnItemClick mOnItemClick;

    public void setOnItemClick(OnItemClick onItemClick) {
        mOnItemClick = onItemClick;
    }

    public interface OnItemClick {
        void setOnItemClick(MapCityBean.ResultBean.CountriesBean.CitiesBeanX bean);
    }

}
