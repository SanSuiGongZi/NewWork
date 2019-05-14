package com.example.tolovepy.everywheretrip.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageButton;

import com.example.tolovepy.everywheretrip.R;
import com.example.tolovepy.everywheretrip.base.BaseActivity;
import com.example.tolovepy.everywheretrip.bean.DetailsBean;
import com.example.tolovepy.everywheretrip.mvp.presenter.EmptyPre;
import com.example.tolovepy.everywheretrip.mvp.view.EmptyView;
import com.example.tolovepy.everywheretrip.ui.adapter.DetailsRecyclerViewAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class DetailsActivityActivity extends BaseActivity<EmptyView, EmptyPre> implements EmptyView {

    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private ArrayList<DetailsBean> recyclerviewList;
    private DetailsRecyclerViewAdapter detailsRecyclerViewAdapter;

    @Override
    protected EmptyPre initPresenter() {
        return new EmptyPre();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_details_activity;
    }

    @Override
    protected void initView() {
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerviewList = new ArrayList<>();
        recyclerviewList.add(new DetailsBean("2017/10/21", "请问日本的西瓜卡怎么办理？", 1));
        recyclerviewList.add(new DetailsBean("2017/10/21", "在个地铁口都可以买到，有一个西瓜卡的自动售卖机", 2));
        detailsRecyclerViewAdapter = new DetailsRecyclerViewAdapter(recyclerviewList, this);
        recyclerview.setAdapter(detailsRecyclerViewAdapter);
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }
}
