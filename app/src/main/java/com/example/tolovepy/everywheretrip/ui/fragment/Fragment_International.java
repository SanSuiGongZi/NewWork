package com.example.tolovepy.everywheretrip.ui.fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.tolovepy.everywheretrip.R;
import com.example.tolovepy.everywheretrip.base.BaseFragment;
import com.example.tolovepy.everywheretrip.base.Constants;
import com.example.tolovepy.everywheretrip.bean.MapCityBean;
import com.example.tolovepy.everywheretrip.mvp.presenter.EmptyPre;
import com.example.tolovepy.everywheretrip.mvp.view.EmptyView;
import com.example.tolovepy.everywheretrip.ui.adapter.MyAdapterInter;
import com.example.tolovepy.everywheretrip.util.SpUtil;

import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class Fragment_International extends BaseFragment<EmptyView, EmptyPre> implements EmptyView {


    @BindView(R.id.mCurrentCity)
    Button mCurrentCity;
    @BindView(R.id.mRv)
    RecyclerView mRv;
    private List<MapCityBean.ResultBean.CountriesBean.CitiesBeanX> mList;
    private MyAdapterInter mInter;
    private String city;

    @SuppressLint("ValidFragment")
    public Fragment_International(List<MapCityBean.ResultBean.CountriesBean.CitiesBeanX> cities,String orCity) {
        // Required empty public constructor
        this.mList=cities;
        this.city=orCity;
}


    @Override
    protected EmptyPre initPresenter() {
        return new EmptyPre();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_international;
    }

    @Override
    protected void initView() {
        mCurrentCity.setText(city);
        mRv.setLayoutManager(new GridLayoutManager(getActivity(),2));
        mInter = new MyAdapterInter(getActivity(), mList);
        mRv.setAdapter(mInter);
        mInter.setOnItemClick(new MyAdapterInter.OnItemClick() {
            @Override
            public void setOnItemClick(MapCityBean.ResultBean.CountriesBean.CitiesBeanX bean) {
                Intent intent = new Intent();
                SpUtil.setParam(Constants.CITY_ID, bean.getId());
                intent.putExtra("city",bean.getName());
                intent.putExtra(Constants.LATITUDE,bean.getLatitude());
                intent.putExtra(Constants.LONGITUDE,bean.getLongitude());
                getActivity().setResult(2,intent);
                getActivity().finish();
            }
        });

        mCurrentCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();

                intent.putExtra("city","北京");
                getActivity().setResult(2,intent);
                getActivity().finish();
            }
        });

    }
}
