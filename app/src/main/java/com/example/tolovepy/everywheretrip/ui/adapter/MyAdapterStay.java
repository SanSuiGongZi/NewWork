package com.example.tolovepy.everywheretrip.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.tolovepy.everywheretrip.R;
import com.example.tolovepy.everywheretrip.bean.BanmiBean;
import com.example.tolovepy.everywheretrip.ui.api.MyGreenDao;
import com.example.tolovepy.everywheretrip.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class MyAdapterStay extends RecyclerView.Adapter<MyAdapterStay.ViewHolder> {

    private Context context;
    private ArrayList<BanmiBean> list = new ArrayList<>();
    private boolean mBoolean;

    public MyAdapterStay(Context context, boolean aBoolean) {
        this.context = context;
        this.mBoolean = aBoolean;
    }

    public void setAdapterStay(){
        notifyDataSetChanged();
    }

    public void addList(List<BanmiBean> lists) {
        if (lists != null) {
            list.clear();
            list.addAll(lists);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_stay, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        final BanmiBean bean = list.get(i);
        viewHolder.mTv_name_stay.setText(bean.getName());
        viewHolder.tv_person.setText(bean.getId() + "人关注");
        viewHolder.mTv_region.setText(bean.getLocation());
        viewHolder.mTv_status.setText(bean.getOccupation());
        RequestOptions options = RequestOptions.placeholderOf(R.mipmap.ee);
        Glide.with(context).load(bean.getPhoto()).apply(options).into(viewHolder.img_stay);

        if (mBoolean) {
            viewHolder.mImg.setVisibility(View.VISIBLE);
        } else {
            viewHolder.mImg.setVisibility(View.INVISIBLE);
        }

        if (MyGreenDao.queryOnly(bean) != null) {
            Glide.with(context).load(R.mipmap.follow).into(viewHolder.mImg);
        } else {
            Glide.with(context).load(R.mipmap.follow_unselected).into(viewHolder.mImg);
        }

        viewHolder.mImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyGreenDao.insert(bean);
                if (MyGreenDao.queryOnly(bean) != null) {
                    ToastUtil.showShort("收藏成功");
                    Glide.with(context).load(R.mipmap.follow).into(viewHolder.mImg);
                } else {
                    Glide.with(context).load(R.mipmap.follow_unselected).into(viewHolder.mImg);
                }
                notifyDataSetChanged();
            }
        });

        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnClick!=null){
                    mOnClick.onItemClick(bean,i);
                }
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img_stay;
        TextView mTv_name_stay;
        TextView tv_person;
        TextView mTv_region;
        TextView mTv_status;
        ImageView mImg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_stay = itemView.findViewById(R.id.img_stay);
            mTv_name_stay = itemView.findViewById(R.id.mTv_name_stay);
            tv_person = itemView.findViewById(R.id.tv_person);
            mTv_region = itemView.findViewById(R.id.mTv_region);
            mTv_status = itemView.findViewById(R.id.mTv_status);
            mImg = itemView.findViewById(R.id.mImg);
        }
    }

    private OnClick mOnClick;

    public void setOnClick(OnClick onClick) {
        mOnClick = onClick;
    }

    public interface OnClick{
        void onItemClick(BanmiBean banmiBean , int position);
    }

}
