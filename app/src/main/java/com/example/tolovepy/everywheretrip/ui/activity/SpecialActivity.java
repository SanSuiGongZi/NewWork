package com.example.tolovepy.everywheretrip.ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tolovepy.everywheretrip.R;
import com.example.tolovepy.everywheretrip.base.BaseActivity;
import com.example.tolovepy.everywheretrip.bean.WebBean;
import com.example.tolovepy.everywheretrip.mvp.presenter.WebPre;
import com.example.tolovepy.everywheretrip.mvp.view.WebView;
import com.example.tolovepy.everywheretrip.ui.adapter.MyAdapterWeb;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class SpecialActivity extends BaseActivity<WebView, WebPre> implements WebView {

    private static final String TAG = "SpecialActivity";
    @BindView(R.id.mIv)
    ImageView mIv;
    @BindView(R.id.mTv_Tool)
    TextView mTvTool;
    @BindView(R.id.mTool_Web)
    Toolbar mToolWeb;
    @BindView(R.id.mRv)
    RecyclerView mRv;
    private ArrayList<WebBean.ResultBean.BundlesBean> mList ;
    private MyAdapterWeb mAdapterWeb;

    @Override
    protected WebPre initPresenter() {
        return new WebPre();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_special;
    }

    @Override
    protected void initView() {
        StatusBarUtil.setLightMode(this);

        mToolWeb.setTitle("");
        setSupportActionBar(mToolWeb);
        mTvTool.setText("旅行专题");

        mRv.setLayoutManager(new LinearLayoutManager(this));
        mList = new ArrayList<>();
        mAdapterWeb = new MyAdapterWeb(this, mList);
        mRv.setAdapter(mAdapterWeb);

        mAdapterWeb.setOnClickWeb(new MyAdapterWeb.OnClickWeb() {
            @Override
            public void onClickWeb(String link, String title) {
                Intent intent = new Intent(SpecialActivity.this, WebActivity.class);
                intent.putExtra("ids", link);
                intent.putExtra("data", title);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void initData() {
        mPresenter.getSpecial();
    }

    @OnClick(R.id.mIv)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void setWeb(WebBean webs) {
        mList.clear();
        mList.addAll(webs.getResult().getBundles());
        mAdapterWeb.setList(mList);

    }

    @Override
    public void setError(String error) {
        Log.e(TAG, "setError: "+error );
    }
}
