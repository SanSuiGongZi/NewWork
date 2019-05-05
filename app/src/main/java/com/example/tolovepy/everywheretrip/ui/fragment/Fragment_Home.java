package com.example.tolovepy.everywheretrip.ui.fragment;


import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.tolovepy.everywheretrip.R;
import com.example.tolovepy.everywheretrip.base.BaseFragment;
import com.example.tolovepy.everywheretrip.bean.MainBean;
import com.example.tolovepy.everywheretrip.mvp.presenter.HomePre;
import com.example.tolovepy.everywheretrip.mvp.view.HomeView;
import com.example.tolovepy.everywheretrip.ui.adapter.MyAdapterHome;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Home extends BaseFragment<HomeView, HomePre> implements HomeView {

    @BindView(R.id.mRv_home)
    RecyclerView mRvHome;
    private ArrayList<MainBean.ResultBean.BannersBean> bannerList;
    private ArrayList<MainBean.ResultBean.RoutesBean> dataList;
    private MyAdapterHome adapterHome;

    public Fragment_Home() {
        // Required empty public constructor
    }

    @Override
    protected HomePre initPresenter() {
        return new HomePre();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment__home;
    }

    @Override
    protected void initView() {
        mRvHome.setLayoutManager(new LinearLayoutManager(getActivity()));
        bannerList = new ArrayList<>();
        dataList = new ArrayList<>();
        adapterHome = new MyAdapterHome(getActivity());
        mRvHome.setAdapter(adapterHome);

    }

    @Override
    protected void initData() {
        mPresenter.getPre();
    }

    @Override
    public void getData(MainBean mainBean) {
        List<MainBean.ResultBean.BannersBean> beanList = mainBean.getResult().getBanners();
        List<MainBean.ResultBean.RoutesBean> list = mainBean.getResult().getRoutes();
        bannerList.addAll(beanList);
        dataList.addAll(list);
        adapterHome.addBar(bannerList);
        adapterHome.addData(dataList);
    }
}
