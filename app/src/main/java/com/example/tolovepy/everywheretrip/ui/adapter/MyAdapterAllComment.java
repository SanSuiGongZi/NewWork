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
import com.example.tolovepy.everywheretrip.bean.AllComment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyAdapterAllComment extends RecyclerView.Adapter<MyAdapterAllComment.ViewHolder> {

    private Context context;
    private ArrayList<AllComment.ResultBean.ReviewsBean> mList = new ArrayList<>();

    public MyAdapterAllComment(Context context) {
        this.context = context;
    }

    //添加数据
    public void addList(List<AllComment.ResultBean.ReviewsBean> list) {
        if (list != null) {
            mList.clear();
            mList.addAll(list);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_allcomment, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        AllComment.ResultBean.ReviewsBean bean = mList.get(i);
        viewHolder.mTv_name.setText(bean.getUserName());
        viewHolder.mTv_day.setText(bean.getCreatedAt());
        viewHolder.mTv_str.setText(bean.getContent());

        RequestOptions options = RequestOptions.circleCropTransform().placeholder(R.mipmap.ee);
        Glide.with(context).load(bean.getUserPhoto()).apply(options).into(viewHolder.mImg);

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.mImg)
        ImageView mImg;
        @BindView(R.id.mTv_name)
        TextView mTv_name;
        @BindView(R.id.mTv_day)
        TextView mTv_day;
        @BindView(R.id.mTv_str)
        TextView mTv_str;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
