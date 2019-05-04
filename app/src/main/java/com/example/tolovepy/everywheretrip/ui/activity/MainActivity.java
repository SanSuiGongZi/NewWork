package com.example.tolovepy.everywheretrip.ui.activity;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.tolovepy.everywheretrip.R;
import com.example.tolovepy.everywheretrip.base.BaseActivity;
import com.example.tolovepy.everywheretrip.mvp.presenter.Presenter;
import com.example.tolovepy.everywheretrip.mvp.view.IView;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<IView, Presenter> implements IView {

    @BindView(R.id.btn)
    Button btn;
    @BindView(R.id.btn_login)
    Button btnLogin;

    @Override
    protected Presenter initPresenter() {
        return new Presenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        mPresenter.getModel();
    }

    @OnClick({R.id.btn,R.id.btn_login})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn:
                showLoading();
                break;
            case R.id.btn_login:
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
                finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                startActivity(new Intent(MainActivity.this,informActivity.class));
                break;
            case R.id.item2:
                startActivity(new Intent(MainActivity.this,advicesActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
