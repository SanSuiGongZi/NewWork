package com.example.tolovepy.everywheretrip.ui.fragment;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.tolovepy.everywheretrip.R;
import com.example.tolovepy.everywheretrip.base.BaseFragment;
import com.example.tolovepy.everywheretrip.bean.StayBeans;
import com.example.tolovepy.everywheretrip.mvp.presenter.StayPre;
import com.example.tolovepy.everywheretrip.mvp.view.StayView;
import com.example.tolovepy.everywheretrip.ui.activity.WithDetailsActivity;
import com.example.tolovepy.everywheretrip.ui.adapter.MyAdapterStay;
import com.example.tolovepy.everywheretrip.util.ToastUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Stay extends BaseFragment<StayView, StayPre> implements StayView {


    @BindView(R.id.mRv_stay)
    RecyclerView mRvStay;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    private ArrayList<StayBeans.ResultBean.BanmiBean> list;
    private MyAdapterStay adapterStay;
    private int page = 1;

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
        adapterStay = new MyAdapterStay(getActivity(), true);
        mRvStay.setAdapter(adapterStay);

        mPresenter.stayData(page);

        //刷新
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                list.clear();
                mPresenter.stayData(page);
                mRefreshLayout.finishRefresh();
            }
        });

        //加载更多
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                mPresenter.stayData(page);
                mRefreshLayout.finishLoadMore();
            }
        });

        adapterStay.setOnImg(new MyAdapterStay.OnImg() {
            @Override
            public void onItemImg(int position) {
                boolean isFollowed = list.get(position).isIsFollowed();
                int id = list.get(position).getId();
                if (isFollowed) {
                    mPresenter.getDelete(id);
                } else {
                    mPresenter.getInsert(id);
                }
                mPresenter.stayData(page);
            }
        });

       adapterStay.setOnItemClick(new MyAdapterStay.OnItemClick() {
           @Override
           public void onItemsClick(StayBeans.ResultBean.BanmiBean banmiBean, int position) {
               Intent intent = new Intent(getActivity(), WithDetailsActivity.class);
               intent.putExtra("idssss",banmiBean.getId());
               startActivity(intent);
           }
       });

    }

    @Override
    public void setList(StayBeans stayBean) {
            list.clear();
            list.addAll(stayBean.getResult().getBanmi());
            adapterStay.addList(list);
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.stayData(page);
    }

    @Override
    public void setToast(String str) {
        ToastUtil.showShort(str);
    }

    @Override
    public void setError(String error) {
        ToastUtil.showShort(error);
    }
}
