package com.example.tolovepy.everywheretrip.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tolovepy.everywheretrip.R;
import com.example.tolovepy.everywheretrip.bean.StayBeans;
import com.example.tolovepy.everywheretrip.util.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class MyAdapterStay extends RecyclerView.Adapter<MyAdapterStay.ViewHolder> {

    private Context context;
    private ArrayList<StayBeans.ResultBean.BanmiBean> list = new ArrayList<>();
    private boolean mBoolean;

    public MyAdapterStay(Context context, boolean aBoolean) {
        this.context = context;
        this.mBoolean = aBoolean;
    }

    public void addList(List<StayBeans.ResultBean.BanmiBean> lists) {
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
        final StayBeans.ResultBean.BanmiBean bean = list.get(i);
        viewHolder.mTv_name_stay.setText(bean.getName());
        viewHolder.tv_person.setText(bean.getId() + "人关注");
        viewHolder.mTv_region.setText(bean.getLocation());
        viewHolder.mTv_status.setText(bean.getOccupation());

        ImageLoader.setCornerImage(context,bean.getPhoto(),viewHolder.img_stay,15,R.mipmap.ee);

        if (mBoolean) {
            viewHolder.mImg.setVisibility(View.VISIBLE);
        } else {
            viewHolder.mImg.setVisibility(View.INVISIBLE);
        }

        boolean isFollowed = bean.isIsFollowed();

        if (isFollowed) {
            ImageLoader.setImage(context,R.mipmap.follow,viewHolder.mImg,R.mipmap.follow);
        } else {
            ImageLoader.setImage(context,R.mipmap.follow_unselected,viewHolder.mImg,R.mipmap.follow_unselected);
        }

        viewHolder.mImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnImg != null) {
                    mOnImg.onItemImg(i);
                }
            }
        });

        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnClick != null) {
                    mOnClick.onItemClick(bean, i);
                }
                return false;
            }
        });

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClick != null) {
                    mOnItemClick.onItemsClick(bean, i);
                }
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

    public interface OnClick {
        void onItemClick(StayBeans.ResultBean.BanmiBean banmiBean, int position);
    }

    private OnImg mOnImg;

    public void setOnImg(OnImg onImg) {
        mOnImg = onImg;
    }

    public interface OnImg {
        void onItemImg(int position);
    }

    private OnItemClick mOnItemClick;

    public void setOnItemClick(OnItemClick onItemClick) {
        mOnItemClick = onItemClick;
    }

    public interface OnItemClick {
        void onItemsClick(StayBeans.ResultBean.BanmiBean banmiBean, int position);
    }

}
