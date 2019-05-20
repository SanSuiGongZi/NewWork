package com.example.tolovepy.everywheretrip.ui.fragment;


import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.example.tolovepy.everywheretrip.R;
import com.example.tolovepy.everywheretrip.base.BaseFragment;
import com.example.tolovepy.everywheretrip.bean.MapCityBean;
import com.example.tolovepy.everywheretrip.bean.MapTabBean;
import com.example.tolovepy.everywheretrip.mvp.presenter.MapPre;
import com.example.tolovepy.everywheretrip.mvp.view.MyMapView;
import com.example.tolovepy.everywheretrip.ui.adapter.MyAdapterCity;
import com.example.tolovepy.everywheretrip.util.ToastUtil;
import com.example.tolovepy.everywheretrip.widget.BanViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import q.rorbin.verticaltablayout.VerticalTabLayout;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class Fragment_Rests extends BaseFragment<MyMapView, MapPre> implements MyMapView {


    @BindView(R.id.tablayout)
    VerticalTabLayout tablayout;
    @BindView(R.id.mVp)
    BanViewPager mVp;
    private ArrayList<MapCityBean.ResultBean.CountriesBean> mTab;
    private MyAdapterCity mCity;
    private FragmentManager mFm;
    private String orCity;

    @SuppressLint("ValidFragment")
    public Fragment_Rests(String city) {
        // Required empty public constructor
        this.orCity = city;
    }

    @Override
    protected MapPre initPresenter() {
        return new MapPre();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_rests;
    }

    @Override
    protected void initView() {
        mFm = getChildFragmentManager();
        mTab = new ArrayList<>();

        mPresenter.setCity();
    }

    @Override
    public void getCity(MapCityBean cityBean) {
        List<MapCityBean.ResultBean.CountriesBean> countries = cityBean.getResult().getCountries();
        mTab.addAll(countries);
        mCity = new MyAdapterCity(mFm, mTab,orCity);
        mVp.setAdapter(mCity);
        tablayout.setupWithViewPager(mVp);
    }

    @Override
    public void getOutTab(MapTabBean tabBean) {

    }

    @Override
    public void getTab(MapTabBean tabBean) {

    }

    @Override
    public void setError(String error) {
        ToastUtil.showShort(error);
    }
}
