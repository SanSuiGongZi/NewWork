package com.example.tolovepy.everywheretrip.ui.fragment;


import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.tolovepy.everywheretrip.R;
import com.example.tolovepy.everywheretrip.base.BaseFragment;
import com.example.tolovepy.everywheretrip.bean.WithPath;
import com.example.tolovepy.everywheretrip.bean.WithState;
import com.example.tolovepy.everywheretrip.mvp.presenter.WithPre;
import com.example.tolovepy.everywheretrip.mvp.view.WithView;
import com.example.tolovepy.everywheretrip.ui.adapter.MyAdapterWith;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_State extends BaseFragment<WithView, WithPre> implements WithView {

    @BindView(R.id.mRv)
    RecyclerView mRv;
    @BindView(R.id.mAllState)
    TextView mAllState;
    private int page = 1;
    private int mBanmIId = 1;
    private ArrayList<WithState.ResultBean.ActivitiesBean> mList;
    private MyAdapterWith mAdapter;


    public Fragment_State() {
        // Required empty public constructor
    }

    @Override
    protected WithPre initPresenter() {
        return new WithPre();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment__state;
    }

    public void setban(int banmIIds) {
        mBanmIId = banmIIds;
    }

    @Override
    protected void initView() {
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mList = new ArrayList<>();
        mAdapter = new MyAdapterWith(getActivity());
        mRv.setAdapter(mAdapter);

    }

    @Override
    protected void initData() {
        mPresenter.setTit(page, mBanmIId);
    }

    @Override
    public void getDataTitle(WithState withState) {
        mList.addAll(withState.getResult().getActivities());
        mAdapter.addList(mList);
    }

    @Override
    public void getDataStr(WithPath withPath) {

    }

    @Override
    public void getError(String error) {
    }

    @OnClick(R.id.mAllState)
    public void onViewClicked() {

    }
}
