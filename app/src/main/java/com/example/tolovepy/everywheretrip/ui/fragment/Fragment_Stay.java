package com.example.tolovepy.everywheretrip.ui.fragment;


import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.tolovepy.everywheretrip.R;
import com.example.tolovepy.everywheretrip.base.BaseFragment;
import com.example.tolovepy.everywheretrip.bean.StayBean;
import com.example.tolovepy.everywheretrip.mvp.presenter.StayPre;
import com.example.tolovepy.everywheretrip.mvp.view.StayView;
import com.example.tolovepy.everywheretrip.ui.adapter.MyAdapterStay;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Stay extends BaseFragment<StayView, StayPre> implements StayView {


    @BindView(R.id.mRv_stay)
    RecyclerView mRvStay;
    private ArrayList<StayBean.ResultBean.BanmiBean> list;
    private MyAdapterStay adapterStay;

    public Fragment_Stay() {
        // Required empty public constructor
    }

    @Override
    protected StayPre initPresenter() {
        return new StayPre();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_stay;
    }

    @Override
    protected void initView() {
        mRvStay.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = new ArrayList<>();
        adapterStay = new MyAdapterStay(getActivity());
        mRvStay.setAdapter(adapterStay);
    }

    @Override
    protected void initData() {
        mPresenter.stayData(1);
    }

    @Override
    public void setList(StayBean stayBean) {
        list.addAll(stayBean.getResult().getBanmi());
        adapterStay.addList(list);
    }
}
