package com.example.tolovepy.everywheretrip.ui.activity;

import android.content.Intent;
import android.os.SystemClock;

import com.example.tolovepy.everywheretrip.R;
import com.example.tolovepy.everywheretrip.base.BaseActivity;
import com.example.tolovepy.everywheretrip.base.Constants;
import com.example.tolovepy.everywheretrip.mvp.presenter.EmptyPre;
import com.example.tolovepy.everywheretrip.mvp.view.EmptyView;
import com.example.tolovepy.everywheretrip.util.SpUtil;

public class SplashActivity extends BaseActivity<EmptyView, EmptyPre> implements EmptyView {

    @Override
    protected EmptyPre initPresenter() {
        return new EmptyPre();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        SystemClock.sleep(2000);
        boolean param = (boolean) SpUtil.getParam(Constants.BOOLEAN, false);
        if (param) {
            String param1 = (String) SpUtil.getParam(Constants.TOKEN, "");
            if ((!param1.equals(""))) {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            } else {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                finish();
            }
        } else {
            startActivity(new Intent(SplashActivity.this, GuideActivity.class));
            finish();
        }
    }
}
