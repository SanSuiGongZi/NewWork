package com.example.tolovepy.everywheretrip.ui.fragment;


import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.tolovepy.everywheretrip.R;
import com.example.tolovepy.everywheretrip.base.BaseFragment;
import com.example.tolovepy.everywheretrip.bean.WithPath;
import com.example.tolovepy.everywheretrip.bean.WithState;
import com.example.tolovepy.everywheretrip.mvp.presenter.WithPre;
import com.example.tolovepy.everywheretrip.mvp.view.WithView;
import com.example.tolovepy.everywheretrip.ui.adapter.MyAdapterPath;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentPath extends BaseFragment<WithView, WithPre> implements WithView {


    @BindView(R.id.mRv)
    RecyclerView mRv;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    private ArrayList<WithPath.ResultBean.RoutesBean> mList;
    private MyAdapterPath mPath;
    private int page = 1;
    private int mBanmIId = 1;

    public FragmentPath() {
        // Required empty public constructor
    }

    public void setban(int banmIIds) {
        mBanmIId = banmIIds;
    }

    @Override
    protected WithPre initPresenter() {
        return new WithPre();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_path;
    }

    @Override
    protected void initView() {
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mList = new ArrayList<>();
        mPath = new MyAdapterPath(getActivity());
        mRv.setAdapter(mPath);

        mPresenter.setStr(page, mBanmIId);

    }

    @Override
    public void getDataTitle(WithState withState) {

    }

    @Override
    public void getDataStr(WithPath withPath) {
        mList.clear();
        mList.addAll(withPath.getResult().getRoutes());
        mPath.addData(mList);
    }

    @Override
    public void getError(String error) {

    }
}
