package com.example.tolovepy.everywheretrip.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.tolovepy.everywheretrip.base.BaseFragment;

import java.util.ArrayList;

public class MyAdapter_Vp extends FragmentPagerAdapter {

    private ArrayList<BaseFragment> list;

    public MyAdapter_Vp(FragmentManager fm, ArrayList<BaseFragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int i) {
        return list.get(i);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
