package com.example.tolovepy.everywheretrip.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.tolovepy.everywheretrip.R;
import com.example.tolovepy.everywheretrip.base.BaseActivity;
import com.example.tolovepy.everywheretrip.base.BaseFragment;
import com.example.tolovepy.everywheretrip.base.Constants;
import com.example.tolovepy.everywheretrip.mvp.presenter.Presenter;
import com.example.tolovepy.everywheretrip.mvp.view.IView;
import com.example.tolovepy.everywheretrip.ui.adapter.MyAdapter_Vp;
import com.example.tolovepy.everywheretrip.ui.fragment.Fragment_Home;
import com.example.tolovepy.everywheretrip.ui.fragment.Fragment_Stay;
import com.example.tolovepy.everywheretrip.util.SpUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<IView, Presenter> implements IView {

    @BindView(R.id.mTool_main)
    Toolbar mToolMain;
    @BindView(R.id.mVp)
    ViewPager mVp;
    @BindView(R.id.mTab)
    TabLayout mTab;
    @BindView(R.id.mDl)
    DrawerLayout mDl;
    @BindView(R.id.mImg_tool)
    ImageView mImgTool;
    @BindView(R.id.mNv)
    NavigationView mNv;
    private ArrayList<BaseFragment> list;
    private MyAdapter_Vp adapter_vp;

    private static final String TAG = "MainActivity";

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
        setSupportActionBar(mToolMain);
        String name = (String) SpUtil.getParam(Constants.USERNAME, "");
        Log.e(TAG, "initView: " + name);
        String img = (String) SpUtil.getParam(Constants.PHOTO, "");

        RequestOptions options = new RequestOptions().circleCrop().placeholder(R.mipmap.ee);
        Glide.with(this).load(img).apply(options).into(mImgTool);

        list = new ArrayList<>();
        list.add(new Fragment_Home());
        list.add(new Fragment_Stay());
        FragmentManager fm = getSupportFragmentManager();
        adapter_vp = new MyAdapter_Vp(fm, list);
        mVp.setAdapter(adapter_vp);
        mTab.addTab(mTab.newTab().setText("首页").setIcon(R.drawable.photoback));
        mTab.addTab(mTab.newTab().setText("伴你").setIcon(R.drawable.photobacks));
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

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDl, mToolMain, R.string.agentweb_click_open, R.string.srl_content_empty);
        mDl.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(false);
        toggle.syncState();
    }

    @Override
    protected void initData() {
        mPresenter.getModel();
    }

    @Override
    protected void initListener() {
        View view = mNv.getHeaderView(0);
        RelativeLayout mRl = view.findViewById(R.id.rl_tit);
        mRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,MessageActivity.class));
            }
        });
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

    @OnClick(R.id.mImg_tool)
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.mImg_tool:
                mDl.openDrawer(Gravity.LEFT);
                break;
        }
    }
}
