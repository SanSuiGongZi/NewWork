package com.example.tolovepy.everywheretrip.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tolovepy.everywheretrip.R;
import com.example.tolovepy.everywheretrip.bean.MainBean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

public class MyAdapterHome extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<MainBean.ResultBean.BannersBean> bannerList = new ArrayList<>();
    private ArrayList<MainBean.ResultBean.RoutesBean> dataList = new ArrayList<>();
    private int ZERO = 0;
    private int ONE = 1;

    public MyAdapterHome(Context context) {
        this.context = context;
    }

    public void addBar(ArrayList<MainBean.ResultBean.BannersBean> banner) {
        if (banner != null) {
            bannerList.clear();
            bannerList.addAll(banner);
        }
        notifyDataSetChanged();
    }

    public void addData(ArrayList<MainBean.ResultBean.RoutesBean> data) {
        if (data != null) {
            dataList.clear();
            dataList.addAll(data);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == ZERO) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_mainbanner, null);
            ViewHolderBann holderBann = new ViewHolderBann(view);
            return holderBann;
        } else {
            View inflate = LayoutInflater.from(context).inflate(R.layout.item_maindata, null);
            ViewHolderData holderData = new ViewHolderData(inflate);
            return holderData;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        int type = getItemViewType(i);
        if (type == ZERO) {

            ViewHolderBann holder = (ViewHolderBann) viewHolder;
            holder.mBar.setImages(bannerList);
            holder.mBar.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    MainBean.ResultBean.BannersBean path1 = (MainBean.ResultBean.BannersBean) path;
                    Glide.with(context).load(path1.getImageURL()).into(imageView);
                }
            }).start();

        } else {
            int position;
            if (bannerList.size() > 0) {
                position = i - 1;
            } else {
                position = i;
            }

            MainBean.ResultBean.RoutesBean bean = dataList.get(position);
            ViewHolderData holder = (ViewHolderData) viewHolder;
            holder.mTv_title.setText(bean.getTitle());
            holder.mTv_area.setText(bean.getCity());
            holder.mTv_str.setText(bean.getIntro());
            holder.mTv_quantity.setText(bean.getPurchasedTimes()+"");
            holder.mBtn_price.setText(bean.getPrice());
            Glide.with(context).load(bean.getCardURL()).into(holder.mImg_back);

        }
    }

    @Override
    public int getItemCount() {
        if (bannerList.size() > 0) {
            return dataList.size() + 1;
        } else {
            return dataList.size();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (bannerList != null) {
            if (position == 0) {
                return ZERO;
            } else {
                return ONE;
            }
        } else {
            return ONE;
        }
    }

    class ViewHolderBann extends RecyclerView.ViewHolder {

        Banner mBar;

        public ViewHolderBann(@NonNull View itemView) {
            super(itemView);
            mBar = itemView.findViewById(R.id.mBar);
        }
    }

    class ViewHolderData extends RecyclerView.ViewHolder {

        ImageView mImg_back;
        TextView mTv_title;
        TextView mTv_area;
        Button mBtn_price;
        TextView mTv_str;
        TextView mTv_quantity;

        public ViewHolderData(@NonNull View itemView) {
            super(itemView);
            mTv_title = itemView.findViewById(R.id.mTv_title);
            mTv_area = itemView.findViewById(R.id.mTv_area);
            mBtn_price = itemView.findViewById(R.id.mBtn_price);
            mTv_str = itemView.findViewById(R.id.mTv_str);
            mTv_quantity = itemView.findViewById(R.id.mTv_quantity);
            mImg_back = itemView.findViewById(R.id.img_back);
        }
    }

}