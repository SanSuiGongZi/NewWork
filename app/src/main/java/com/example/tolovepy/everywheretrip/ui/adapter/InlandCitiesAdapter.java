package com.example.tolovepy.everywheretrip.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tolovepy.everywheretrip.R;
import com.example.tolovepy.everywheretrip.bean.CityBean;

import java.util.ArrayList;

public class InlandCitiesAdapter extends BaseAdapter{
    ArrayList<CityBean> list;
    Context context;

    public InlandCitiesAdapter(ArrayList<CityBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_user_friend, null);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.tv_friend_name);
            viewHolder.alpha = (TextView) convertView.findViewById(R.id.alpha);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final CityBean cityBean = list.get(position);
        viewHolder.name.setText(cityBean.getCityName());

        // 根据position获取分类的首字母的Char ascii值
        int section = getSectionForPosition(position);
        // 如果当前位置等于该分类首字母的Char的位置 ，则认为是第一次出现
        if (position == getPositionForSection(section)) {
            viewHolder.alpha.setVisibility(View.VISIBLE);
            viewHolder.alpha.setText(cityBean.getFirstLetter());
        } else {
            viewHolder.alpha.setVisibility(View.GONE);
        }

        viewHolder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSendData!=null){
                    mSendData.sendData(cityBean.getId(),cityBean.getCityName(),cityBean.getLatitude(),cityBean.getLongitude());
                }
            }
        });

        return convertView;
    }

    static class ViewHolder {
        TextView name;
        TextView alpha;;
    }
    //判断当前位置是否是该位置对应的字母第一次出现
    public int getPositionForSection(int section) {
        for (int i = 0; i < getCount(); i++) {
            String sortStr = list.get(i).getFirstLetter();
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (section == firstChar) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 根据ListView的当前位置获取分类的首字母的Char ascii值
     */
    private int getSectionForPosition(int position) {
        return list.get(position).getFirstLetter().charAt(0);
    }

    SendData mSendData;

    public void setSendData(SendData sendData) {
        mSendData = sendData;
    }

    public interface SendData{
        void sendData(int id, String name, double latitude, double longitude);
    }
}
