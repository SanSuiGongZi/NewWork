package com.example.tolovepy.everywheretrip.ui.fragment;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.tolovepy.everywheretrip.R;
import com.example.tolovepy.everywheretrip.base.BaseFragment;
import com.example.tolovepy.everywheretrip.bean.MainBean;
import com.example.tolovepy.everywheretrip.mvp.presenter.HomePre;
import com.example.tolovepy.everywheretrip.mvp.view.HomeView;
import com.example.tolovepy.everywheretrip.ui.activity.DetailsActivity;
import com.example.tolovepy.everywheretrip.ui.activity.WebViewActivity;
import com.example.tolovepy.everywheretrip.ui.adapter.MyAdapterHome;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Home extends BaseFragment<HomeView, HomePre> implements HomeView {

    @BindView(R.id.mRv_home)
    RecyclerView mRvHome;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    private ArrayList<MainBean.ResultBean.BannersBean> bannerList;
    private ArrayList<MainBean.ResultBean.RoutesBean> dataList;
    private MyAdapterHome adapterHome;
    private int page = 1;

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

        //刷新
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                bannerList.clear();
                dataList.clear();
                mPresenter.getPre(page);
                mRefreshLayout.finishRefresh();
            }
        });

        //加载更多
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                mPresenter.getPre(page);
                mRefreshLayout.finishLoadMore();
            }
        });

    }

    @Override
    protected void initData() {
        mPresenter.getPre(page);
    }

    @Override
    public void getData(MainBean mainBean) {
        List<MainBean.ResultBean.BannersBean> beanList = mainBean.getResult().getBanners();
        List<MainBean.ResultBean.RoutesBean> list = mainBean.getResult().getRoutes();
        bannerList.addAll(beanList);
        dataList.addAll(list);
        adapterHome.addBar(bannerList);
        adapterHome.addData(dataList);

        adapterHome.setOnClickDetails(new MyAdapterHome.OnClickDetails() {
            @Override
            public void onClickDetails(int id) {
                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                intent.putExtra("id", id);
                getActivity().startActivity(intent);
            }
        });

        adapterHome.setOnClickWeb(new MyAdapterHome.OnClickWeb() {
            @Override
            public void onClickWeb(String link, String title) {
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra("ids", link);
                intent.putExtra("data", title);
                getActivity().startActivity(intent);
            }
        });
    }
}
