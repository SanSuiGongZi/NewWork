package com.example.tolovepy.everywheretrip.ui.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.tolovepy.everywheretrip.R;
import com.example.tolovepy.everywheretrip.base.BaseActivity;
import com.example.tolovepy.everywheretrip.bean.LoginBean;
import com.example.tolovepy.everywheretrip.mvp.presenter.LoginPre;
import com.example.tolovepy.everywheretrip.mvp.view.LoginView;
import com.example.tolovepy.everywheretrip.ui.fragment.Fragment_Code;
import com.example.tolovepy.everywheretrip.ui.fragment.Fragment_login;

import java.util.ArrayList;

import butterknife.BindView;

public class LoginActivity extends BaseActivity<LoginView, LoginPre> implements LoginView {

    @BindView(R.id.mTool)
    Toolbar mTool;
    @BindView(R.id.mfl)
    FrameLayout mfl;
    @BindView(R.id.img_CE)
    ImageView imgCE;
    private ArrayList<Fragment> list;
    private Fragment_login fragment_login;
    private Fragment_Code fragment_code;

    @Override
    protected LoginPre initPresenter() {
        return new LoginPre();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        mTool.setTitle("");
        setSupportActionBar(mTool);
        final FragmentManager fm = getSupportFragmentManager();
        final FragmentTransaction transaction = fm.beginTransaction();


        fragment_login = new Fragment_login();
        fragment_code = new Fragment_Code();
        list = new ArrayList<>();
        list.add(fragment_login);
        list.add(fragment_code);
        transaction.add(R.id.mfl, list.get(0));
        transaction.commit();

        fragment_login.setOnClick(new Fragment_login.onClick() {
            @Override
            public void onClickLister(boolean mBoolean) {
                FragmentTransaction beginTransaction = fm.beginTransaction();
                Fragment fragment = list.get(1);
                if (!fragment.isAdded()) {
                    beginTransaction.add(R.id.mfl, fragment);
                }
                beginTransaction.hide(fragment_login);
                beginTransaction.show(fragment_code);
                beginTransaction.commit();

            }
        });

        fragment_code.setOnClick(new Fragment_Code.onClick() {
            @Override
            public void onClickLister(boolean mBoolean) {
                FragmentTransaction beginTransaction = fm.beginTransaction();
                Fragment fragment = list.get(0);
                if (!fragment.isAdded()) {
                    beginTransaction.add(R.id.mfl, fragment);
                }
                beginTransaction.hide(fragment_code);
                beginTransaction.show(fragment_login);
                beginTransaction.commit();
            }
        });

        imgCE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
            }
        });

    }


    @Override
    public void LoginData(LoginBean loginBean) {

    }
}
