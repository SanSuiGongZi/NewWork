package com.example.tolovepy.everywheretrip.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.tolovepy.everywheretrip.bean.MapCityBean;
import com.example.tolovepy.everywheretrip.ui.fragment.Fragment_International;

import java.util.ArrayList;

public class MyAdapterCity extends FragmentPagerAdapter {

    private ArrayList<MapCityBean.ResultBean.CountriesBean> list;
    private String citys;

    public MyAdapterCity(FragmentManager fm, ArrayList<MapCityBean.ResultBean.CountriesBean> list, String city) {
        super(fm);
        this.list = list;
        this.citys=city;
    }

    @Override
    public Fragment getItem(int i) {
        Fragment_International international = new Fragment_International(list.get(i).getCities(),citys);
        return international;
    }

    @Override
    public int getCount() {
        return list.size();
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position).getName();
    }
}
