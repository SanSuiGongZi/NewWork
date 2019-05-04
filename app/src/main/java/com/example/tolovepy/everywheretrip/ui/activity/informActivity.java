package com.example.tolovepy.everywheretrip.ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tolovepy.everywheretrip.R;
import com.example.tolovepy.everywheretrip.base.BaseActivity;
import com.example.tolovepy.everywheretrip.bean.InformBean;
import com.example.tolovepy.everywheretrip.mvp.presenter.Presenter;
import com.example.tolovepy.everywheretrip.mvp.view.IView;
import com.example.tolovepy.everywheretrip.ui.adapter.MyAdapter;

import java.util.ArrayList;

import butterknife.BindView;

public class informActivity extends BaseActivity<IView, Presenter> implements IView {

    @BindView(R.id.mImg_T)
    ImageView mImgT;
    @BindView(R.id.mTv_Clear)
    TextView mTvClear;
    @BindView(R.id.mTools)
    Toolbar mTools;
    @BindView(R.id.Mrv)
    RecyclerView Mrv;
    private ArrayList<InformBean> list;
    private MyAdapter adapter;

    @Override
    protected Presenter initPresenter() {
        return new Presenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_inform;
    }

    @Override
    protected void initView() {
        mTools.setTitle("");
        setSupportActionBar(mTools);
        Mrv.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            InformBean bean = new InformBean();
            bean.setTitle("系统通知");
            bean.setTime("2017/10/21");
            if (i == 0) {
                bean.setText("用户注册成功");
            } else {
                bean.setText("通过分享获得2米粒");
            }
            list.add(bean);
        }

        adapter = new MyAdapter(this, list);
        Mrv.setAdapter(adapter);

        mImgT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(informActivity.this,MainActivity.class));
            }
        });

    }
}
