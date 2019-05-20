package com.example.tolovepy.everywheretrip.ui.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.tolovepy.everywheretrip.R;
import com.example.tolovepy.everywheretrip.base.BaseActivity;
import com.example.tolovepy.everywheretrip.base.BaseFragment;
import com.example.tolovepy.everywheretrip.mvp.presenter.EmptyPre;
import com.example.tolovepy.everywheretrip.mvp.view.EmptyView;
import com.example.tolovepy.everywheretrip.ui.fragment.Fragment_Chana;
import com.example.tolovepy.everywheretrip.ui.fragment.Fragment_Rests;

import java.util.ArrayList;

import butterknife.BindView;

public class CityActivity extends BaseActivity<EmptyView, EmptyPre> implements EmptyView {

    @BindView(R.id.mTab)
    TabLayout mTab;
    @BindView(R.id.mVp)
    ViewPager mVp;
    private ArrayList<BaseFragment> mFragments;

    @Override
    protected EmptyPre initPresenter() {
        return new EmptyPre();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_city;
    }

    @Override
    protected void initView() {

        Intent intent = getIntent();
        String city = intent.getStringExtra("city");

        mTab.addTab(mTab.newTab().setText("国内"));
        mTab.addTab(mTab.newTab().setText("国际"));

        mFragments = new ArrayList<>();
        mFragments.add(new Fragment_Chana(city));
        mFragments.add(new Fragment_Rests(city));

        FragmentManager fm = getSupportFragmentManager();
        mVp.setAdapter(new FragmentPagerAdapter(fm) {
            @Override
            public Fragment getItem(int i) {
                return mFragments.get(i);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        });

        mVp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTab));
        mTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mVp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

}
