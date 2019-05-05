package com.example.tolovepy.everywheretrip.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.tolovepy.everywheretrip.R;
import com.example.tolovepy.everywheretrip.base.BaseActivity;
import com.example.tolovepy.everywheretrip.base.BaseFragment;
import com.example.tolovepy.everywheretrip.mvp.presenter.Presenter;
import com.example.tolovepy.everywheretrip.mvp.view.IView;
import com.example.tolovepy.everywheretrip.ui.adapter.MyAdapter_Vp;
import com.example.tolovepy.everywheretrip.ui.fragment.Fragment_Home;
import com.example.tolovepy.everywheretrip.ui.fragment.Fragment_Stay;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends BaseActivity<IView, Presenter> implements IView {

    @BindView(R.id.mTool_main)
    Toolbar mToolMain;
    @BindView(R.id.mVp)
    ViewPager mVp;
    @BindView(R.id.mTab)
    TabLayout mTab;
    private ArrayList<BaseFragment> list;
    private MyAdapter_Vp adapter_vp;


    @Override
    protected Presenter initPresenter() {
        return new Presenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    public static void startAct(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initView() {
        mToolMain.setTitle("");
        mToolMain.setNavigationIcon(R.mipmap.back_white);
        setSupportActionBar(mToolMain);

        mToolMain.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
                finish();
            }
        });

        list = new ArrayList<>();
        list.add(new Fragment_Home());
        list.add(new Fragment_Stay());
        FragmentManager fm = getSupportFragmentManager();
        adapter_vp = new MyAdapter_Vp(fm, list);
        mVp.setAdapter(adapter_vp);
        mTab.addTab(mTab.newTab().setText("首页"));
        mTab.addTab(mTab.newTab().setText("伴你"));
        mVp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTab));
        mTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mVp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    protected void initData() {
        mPresenter.getModel();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                startActivity(new Intent(MainActivity.this, informActivity.class));
                break;
            case R.id.item2:
                startActivity(new Intent(MainActivity.this, advicesActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
