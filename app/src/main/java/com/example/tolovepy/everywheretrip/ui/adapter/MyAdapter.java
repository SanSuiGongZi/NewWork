package com.example.tolovepy.everywheretrip.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tolovepy.everywheretrip.R;
import com.example.tolovepy.everywheretrip.bean.InformBean;
import com.example.tolovepy.everywheretrip.ui.activity.DetailsActivityActivity;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private Context context;
    private ArrayList<InformBean> list;

    public MyAdapter(Context context, ArrayList<InformBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_inform, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        InformBean bean = list.get(i);
        if (bean != null) {
            viewHolder.tv_title_inform.setText(bean.getTitle());
            viewHolder.tv_time_inform.setText(bean.getTime());
            viewHolder.tv_text_inform.setText(bean.getText());
        }

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context,DetailsActivityActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_title_inform;
        TextView tv_time_inform;
        TextView tv_text_inform;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title_inform = itemView.findViewById(R.id.tv_title_inform);
            tv_time_inform = itemView.findViewById(R.id.tv_time_inform);
            tv_text_inform = itemView.findViewById(R.id.tv_text_inform);
        }
    }
}
