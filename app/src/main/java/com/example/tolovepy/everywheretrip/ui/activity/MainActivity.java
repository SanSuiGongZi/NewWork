package com.example.tolovepy.everywheretrip.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tolovepy.everywheretrip.R;
import com.example.tolovepy.everywheretrip.base.BaseActivity;
import com.example.tolovepy.everywheretrip.base.BaseFragment;
import com.example.tolovepy.everywheretrip.mvp.presenter.EmptyPre;
import com.example.tolovepy.everywheretrip.mvp.view.EmptyView;
import com.example.tolovepy.everywheretrip.ui.adapter.MyAdapter_Vp;
import com.example.tolovepy.everywheretrip.ui.fragment.Fragment_Discover;
import com.example.tolovepy.everywheretrip.ui.fragment.Fragment_Home;
import com.example.tolovepy.everywheretrip.ui.fragment.Fragment_Personage;
import com.example.tolovepy.everywheretrip.ui.fragment.Fragment_Stay;
import com.example.tolovepy.everywheretrip.widget.BanViewPager;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;

import butterknife.BindView;

//develop分支
public class MainActivity extends BaseActivity<EmptyView, EmptyPre> implements EmptyView {

    @BindView(R.id.mTool_main)
    Toolbar mToolMain;
    @BindView(R.id.mVp)
    BanViewPager mVp;
    @BindView(R.id.mTab)
    TabLayout mTab;
    @BindView(R.id.mTool_tv)
    TextView mToolTv;
    @BindView(R.id.mTv_city)
    TextView mTvCity;
    @BindView(R.id.mll)
    LinearLayout mll;
    private ArrayList<BaseFragment> list;
    private MyAdapter_Vp adapter_vp;

    @Override
    protected EmptyPre initPresenter() {
        return new EmptyPre();
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
        StatusBarUtil.setLightMode(this);

        mToolMain.setTitle("");
        setSupportActionBar(mToolMain);
        mToolTv.setText("线路");
        mll.setVisibility(View.GONE);

        Fragment_Discover discover = new Fragment_Discover();

        list = new ArrayList<>();
        list.add(new Fragment_Home());
        list.add(new Fragment_Stay());
        list.add(discover);
        list.add(new Fragment_Personage());


        FragmentManager fm = getSupportFragmentManager();
        adapter_vp = new MyAdapter_Vp(fm, list);
        mVp.setAdapter(adapter_vp);
        mTab.addTab(mTab.newTab().setText("怎么玩").setIcon(R.drawable.photoback));
        mTab.addTab(mTab.newTab().setText("关注").setIcon(R.drawable.photoback2));
        mTab.addTab(mTab.newTab().setText("发现").setIcon(R.drawable.photoback3));
        mTab.addTab(mTab.newTab().setText("我的").setIcon(R.drawable.photobacks));
        // 修改样式，主要是调近了图标与文字之间的距离
        mTab.getTabAt(0).setCustomView(getTabView("怎么玩", R.drawable.photoback));
        mTab.getTabAt(1).setCustomView(getTabView("关注", R.drawable.photoback2));
        mTab.getTabAt(2).setCustomView(getTabView("发现", R.drawable.photoback3));
        mTab.getTabAt(3).setCustomView(getTabView("我的", R.drawable.photobacks));

        mVp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTab));
        mTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mVp.setCurrentItem(tab.getPosition());
                mToolTv.setText(tab.getText());
                if (tab.getPosition()==2){
                    mll.setVisibility(View.VISIBLE);
                    mToolTv.setVisibility(View.GONE);
                }else {
                    mll.setVisibility(View.GONE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    // Tab自定义view
    public View getTabView(String title, int image_src) {
        View v = LayoutInflater.from(getApplicationContext()).inflate(R.layout.tab_item_view, null);
        TextView textView = (TextView) v.findViewById(R.id.textview);
        textView.setText(title);
        ImageView imageView = (ImageView) v.findViewById(R.id.imageview);
        imageView.setImageResource(image_src);
        return v;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            /*case R.id.item1:
                startActivity(new Intent(MainActivity.this, informActivity.class));
                break;
            case R.id.item2:
                startActivity(new Intent(MainActivity.this, advicesActivity.class));
                break;*/
        }
        return super.onOptionsItemSelected(item);
    }

}
