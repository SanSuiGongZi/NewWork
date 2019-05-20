package com.example.tolovepy.everywheretrip.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.tolovepy.everywheretrip.R;
import com.example.tolovepy.everywheretrip.bean.WebBean;
import com.example.tolovepy.everywheretrip.util.ImageLoader;

import java.util.ArrayList;

public class MyAdapterWeb extends RecyclerView.Adapter<MyAdapterWeb.ViewHolder> {

    private Context context;
    private ArrayList<WebBean.ResultBean.BundlesBean> mList;

    public MyAdapterWeb(Context context, ArrayList<WebBean.ResultBean.BundlesBean> list) {
        this.context = context;
        mList = list;
    }

    public void setList(ArrayList<WebBean.ResultBean.BundlesBean> list) {
        mList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final WebBean.ResultBean.BundlesBean bean = mList.get(i);

        ImageLoader.setImage(context,bean.getCardURL(),viewHolder.mIv_home,R.mipmap.ee);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnClickWeb != null) {
                    mOnClickWeb.onClickWeb(bean.getContentURL(), bean.getTitle());
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView mIv_home;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mIv_home = itemView.findViewById(R.id.mIv_home);
        }
    }

    private OnClickWeb mOnClickWeb;

    public void setOnClickWeb(OnClickWeb onClickWeb) {
        mOnClickWeb = onClickWeb;
    }

    public interface OnClickWeb {
        void onClickWeb(String link, String title);
    }

}
