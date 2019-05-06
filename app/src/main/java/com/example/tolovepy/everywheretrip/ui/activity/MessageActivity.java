package com.example.tolovepy.everywheretrip.ui.activity;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.tolovepy.everywheretrip.R;
import com.example.tolovepy.everywheretrip.base.BaseActivity;
import com.example.tolovepy.everywheretrip.mvp.presenter.EmptyPre;
import com.example.tolovepy.everywheretrip.mvp.view.EmptyView;

import butterknife.BindView;
import butterknife.OnClick;

public class MessageActivity extends BaseActivity<EmptyView, EmptyPre> implements EmptyView {

    @BindView(R.id.img_replace)
    ImageView imgReplace;
    @BindView(R.id.mTool_mess)
    Toolbar mToolMess;
    @BindView(R.id.out_psw)
    RelativeLayout outPsw;
    @BindView(R.id.out_phone)
    RelativeLayout outPhone;
    @BindView(R.id.mBut_exit)
    Button mButExit;

    @Override
    protected EmptyPre initPresenter() {
        return new EmptyPre();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_message;
    }

    @OnClick({R.id.img_replace, R.id.out_psw, R.id.out_phone, R.id.mBut_exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_replace:

                finish();

                break;
            case R.id.out_psw:
                break;
            case R.id.out_phone:
                break;
            case R.id.mBut_exit:
                break;
        }
    }
}
