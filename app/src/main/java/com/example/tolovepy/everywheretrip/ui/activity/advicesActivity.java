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

public class advicesActivity extends BaseActivity<IView, Presenter> implements IView {

    @BindView(R.id.mImg_T1)
    ImageView mImgT1;
    @BindView(R.id.mTv_Clear1)
    TextView mTvClear1;
    @BindView(R.id.mTools1)
    Toolbar mTools1;
    @BindView(R.id.Mrv1)
    RecyclerView Mrv1;
    private ArrayList<InformBean> list1;
    private MyAdapter adapter;

    @Override
    protected Presenter initPresenter() {
        return new Presenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_advices;
    }

    @Override
    protected void initView() {
        mTools1.setTitle("");
        setSupportActionBar(mTools1);
        Mrv1.setLayoutManager(new LinearLayoutManager(this));
        list1 = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            InformBean bean = new InformBean();

            bean.setTime("2017/10/21");
            if (i == 0) {
                bean.setTitle("消息回复");
                bean.setText("阚锁回复了你的留言");
            } else {
                bean.setTitle("系统通知");
                bean.setText("林竹回复了你的留言");
            }
            list1.add(bean);
        }

        adapter = new MyAdapter(this, list1);
        Mrv1.setAdapter(adapter);


        mImgT1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(advicesActivity.this,MainActivity.class));
            }
        });

    }
}
