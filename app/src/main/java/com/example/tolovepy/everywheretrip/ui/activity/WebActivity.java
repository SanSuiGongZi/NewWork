package com.example.tolovepy.everywheretrip.ui.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tolovepy.everywheretrip.R;
import com.example.tolovepy.everywheretrip.base.BaseActivity;
import com.example.tolovepy.everywheretrip.mvp.presenter.EmptyPre;
import com.example.tolovepy.everywheretrip.mvp.view.EmptyView;
import com.jaeger.library.StatusBarUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class WebActivity extends BaseActivity<EmptyView, EmptyPre> implements EmptyView {

    @BindView(R.id.mTv_Tool)
    TextView mTvTool;
    @BindView(R.id.mTool_Web)
    Toolbar mToolWeb;
    @BindView(R.id.mWeb)
    WebView mWeb;
    @BindView(R.id.mIv)
    ImageView mIv;

    @Override
    protected EmptyPre initPresenter() {
        return new EmptyPre();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    protected void initView() {
        StatusBarUtil.setLightMode(this);

        Intent intent = getIntent();
        String data = intent.getStringExtra("data");
        String link = intent.getStringExtra("ids");
        mTvTool.setText(data);

        WebSettings settings = mWeb.getSettings();
        //支持js交互
        settings.setJavaScriptEnabled(true);

        mWeb.setWebViewClient(new WebViewClient());
        mWeb.loadUrl(link+"?os=android");

        mWeb.addJavascriptInterface(new AndroidJs(WebActivity.this), "android");
    }

    @OnClick(R.id.mIv)
    public void onViewClicked() {
        finish();
    }
}
