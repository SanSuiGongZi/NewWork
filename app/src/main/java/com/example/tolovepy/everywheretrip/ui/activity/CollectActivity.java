package com.example.tolovepy.everywheretrip.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.tolovepy.everywheretrip.R;
import com.example.tolovepy.everywheretrip.base.BaseActivity;
import com.example.tolovepy.everywheretrip.bean.BanmiBean;
import com.example.tolovepy.everywheretrip.mvp.presenter.EmptyPre;
import com.example.tolovepy.everywheretrip.mvp.view.EmptyView;
import com.example.tolovepy.everywheretrip.ui.adapter.MyAdapterStay;
import com.example.tolovepy.everywheretrip.ui.api.MyGreenDao;

import java.util.List;

import butterknife.BindView;

public class CollectActivity extends BaseActivity<EmptyView, EmptyPre> implements EmptyView {


    @BindView(R.id.mRv_stay)
    RecyclerView mRvStay;
    private MyAdapterStay mAdapterStay;

    @Override
    protected EmptyPre initPresenter() {
        return new EmptyPre();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_stay;
    }

    @Override
    protected void initView() {
        mRvStay.setLayoutManager(new LinearLayoutManager(this));
        mAdapterStay = new MyAdapterStay(this,false);
        mRvStay.setAdapter(mAdapterStay);
        List<BanmiBean> list = MyGreenDao.queryAll();
        mAdapterStay.addList(list);

        mAdapterStay.setOnClick(new MyAdapterStay.OnClick() {
            @Override
            public void onItemClick(BanmiBean banmiBean, int position) {
                MyGreenDao.delete(banmiBean);
                List<BanmiBean> beans = MyGreenDao.queryAll();
                mAdapterStay.addList(beans);
            }
        });
    }
}
