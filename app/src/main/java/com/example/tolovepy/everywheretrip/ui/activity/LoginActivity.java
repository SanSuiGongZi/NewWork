package com.example.tolovepy.everywheretrip.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.example.tolovepy.everywheretrip.R;
import com.example.tolovepy.everywheretrip.base.BaseActivity;
import com.example.tolovepy.everywheretrip.bean.LoginBean;
import com.example.tolovepy.everywheretrip.mvp.presenter.LoginPre;
import com.example.tolovepy.everywheretrip.mvp.view.LoginView;
import com.example.tolovepy.everywheretrip.ui.fragment.FragmentCodes;
import com.example.tolovepy.everywheretrip.ui.fragment.Fragment_Code;
import com.example.tolovepy.everywheretrip.ui.fragment.Fragment_login;

import java.util.ArrayList;

import butterknife.BindView;

public class LoginActivity extends BaseActivity<LoginView, LoginPre> implements LoginView {

    @BindView(R.id.mfl)
    FrameLayout mfl;
    private ArrayList<Fragment> list;
    private Fragment_login fragment_login;
    private Fragment_Code fragment_code;
    private FragmentManager fm;

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
        fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();

        fragment_login = new Fragment_login();
        fragment_code = new Fragment_Code();
        FragmentCodes codes = new FragmentCodes();
        list = new ArrayList<>();
        list.add(fragment_login);
//        list.add(fragment_code);
        list.add(codes);
        transaction.add(R.id.mfl, list.get(0));
        transaction.commit();

    }


    @Override
    public void LoginData(LoginBean loginBean) {

    }
}
