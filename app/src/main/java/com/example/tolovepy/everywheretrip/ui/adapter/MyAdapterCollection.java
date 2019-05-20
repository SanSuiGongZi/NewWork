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
import com.example.tolovepy.everywheretrip.bean.CollectionList;
import com.example.tolovepy.everywheretrip.util.ImageLoader;

import java.util.ArrayList;

public class MyAdapterCollection extends RecyclerView.Adapter<MyAdapterCollection.ViewHolder> {

    private Context context;
    private ArrayList<CollectionList.ResultBean.CollectedRoutesBean> mList = new ArrayList<>();

    public MyAdapterCollection(Context context) {
        this.context = context;
    }

    public void addList(ArrayList<CollectionList.ResultBean.CollectedRoutesBean> list) {
        if (list != null) {
            mList.clear();
            mList.addAll(list);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_collection, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        CollectionList.ResultBean.CollectedRoutesBean bean = mList.get(i);
        viewHolder.mTv_name_stay.setText(bean.getTitle());
        viewHolder.tv_person.setText(bean.getIntro());
        ImageLoader.setImage(context,bean.getCardURL(),viewHolder.img_stay,R.mipmap.ee);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img_stay;
        TextView mTv_name_stay;
        TextView tv_person;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_stay = itemView.findViewById(R.id.img_stay);
            mTv_name_stay = itemView.findViewById(R.id.mTv_name_stay);
            tv_person = itemView.findViewById(R.id.tv_person);
        }
    }
}
