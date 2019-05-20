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

import com.example.tolovepy.everywheretrip.R;
import com.example.tolovepy.everywheretrip.bean.WithPath;
import com.example.tolovepy.everywheretrip.util.ImageLoader;

import java.util.ArrayList;

public class MyAdapterPath extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<WithPath.ResultBean.RoutesBean> dataList = new ArrayList<>();

    public MyAdapterPath(Context context) {
        this.context = context;
    }

    public void addData(ArrayList<WithPath.ResultBean.RoutesBean> data) {
        if (data != null) {
            dataList.clear();
            dataList.addAll(data);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_with, null);
        ViewHolderData holderData = new ViewHolderData(inflate);
        return holderData;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        final WithPath.ResultBean.RoutesBean bean = dataList.get(position);
        ViewHolderData holder = (ViewHolderData) viewHolder;
        holder.mTv_title.setText(bean.getTitle());
        holder.mTv_area.setText(bean.getCity());
        holder.mTv_str.setText(bean.getIntro());
        holder.mTv_quantity.setText(bean.getPriceInCents() + "人关注");
        holder.mBtn_price.setText("¥" + bean.getPrice());

        ImageLoader.setImage(context,bean.getCardURL(),holder.mImg_back,R.mipmap.ee);

    }

    @Override
    public int getItemCount() {
        return dataList.size();
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

    private OnClickDetails mOnClickDetails;

    public void setOnClickDetails(OnClickDetails onClickDetails) {
        mOnClickDetails = onClickDetails;
    }

    public interface OnClickDetails {
        void onClickDetails(int id);
    }

}
