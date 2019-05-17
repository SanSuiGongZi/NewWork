package com.example.tolovepy.everywheretrip.ui.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.tolovepy.everywheretrip.R;
import com.example.tolovepy.everywheretrip.base.BaseActivity;
import com.example.tolovepy.everywheretrip.base.BaseFragment;
import com.example.tolovepy.everywheretrip.base.Constants;
import com.example.tolovepy.everywheretrip.bean.BalanceBean;
import com.example.tolovepy.everywheretrip.bean.MessageBean;
import com.example.tolovepy.everywheretrip.bean.NewVersion;
import com.example.tolovepy.everywheretrip.mvp.presenter.Presenter;
import com.example.tolovepy.everywheretrip.mvp.view.IView;
import com.example.tolovepy.everywheretrip.net.MyServer;
import com.example.tolovepy.everywheretrip.ui.adapter.MyAdapter_Vp;
import com.example.tolovepy.everywheretrip.ui.fragment.Fragment_Home;
import com.example.tolovepy.everywheretrip.ui.fragment.Fragment_Stay;
import com.example.tolovepy.everywheretrip.util.SpUtil;
import com.example.tolovepy.everywheretrip.util.ToastUtil;
import com.example.tolovepy.everywheretrip.util.Tools;
import com.jaeger.library.StatusBarUtil;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

//主分支
//develop分支
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
    private MyServer.XiaZai binder;
    private static final String TAG = "MainActivity";
    private TextView mMTv_name;
    private ImageView mMIv;
    private TextView mMTv_signature;
    private RelativeLayout mMTv_attention;
    private TextView mTv_balance;
    private LinearLayout mVersions;
    private File sd;
    private ProgressDialog mDialog;


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

    private ServiceConnection connection = new ServiceConnection() {
        //绑定服务调用
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder = (MyServer.XiaZai) service;

            //开启下载
            binder.setVersionDownload(MainActivity.this, sd);

        }

        //解绑服务调用
        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void initView() {
        StatusBarUtil.setLightMode(this);
        initSD();

        int versionCode = Tools.getVersionCode();
        String name = Tools.getVersionName();
        Log.e(TAG, "initView: " + versionCode + "..." + name);

        mToolMain.setTitle("");
        setSupportActionBar(mToolMain);

        list = new ArrayList<>();
        list.add(new Fragment_Home());
        list.add(new Fragment_Stay());
        FragmentManager fm = getSupportFragmentManager();
        adapter_vp = new MyAdapter_Vp(fm, list);
        mVp.setAdapter(adapter_vp);
        mTab.addTab(mTab.newTab().setText("首页").setIcon(R.drawable.photoback));
        mTab.addTab(mTab.newTab().setText("伴你").setIcon(R.drawable.photobacks));
        // 修改样式，主要是调近了图标与文字之间的距离
        mTab.getTabAt(0).setCustomView(getTabView("首页", R.drawable.photoback));
        mTab.getTabAt(1).setCustomView(getTabView("伴你", R.drawable.photobacks));

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

        String balance = (String) SpUtil.getParam(Constants.BALANCE, "0");
        if (TextUtils.isEmpty(balance)) {
            mTv_balance.setText(balance);
        }
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
    protected void onRestart() {
        super.onRestart();
        set();
        mPresenter.getModel();
    }

    private void set() {
        mPresenter.newData();
    }

    @Override
    protected void initData() {
        mPresenter.getModel();
    }

    @Override
    protected void initListener() {
        //头像父布局
        RelativeLayout mRl = findViewById(R.id.rl_tit);
        //收藏
        RelativeLayout mRls = findViewById(R.id.collect);
        //用户名
        mMTv_name = findViewById(R.id.mTv_name);
        //头像
        mMIv = findViewById(R.id.mIv);
        //个性签名
        mMTv_signature = findViewById(R.id.mTv_signature);
        //关注
        mMTv_attention = findViewById(R.id.attention);
        //余额
        mTv_balance = findViewById(R.id.mTv_money);
        //版本检测
        mVersions = findViewById(R.id.versions);

        set();

        mRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MessageActivity.class));
                mDl.closeDrawer(Gravity.LEFT);
            }
        });
        mRls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CollectionActivity.class));
                mDl.closeDrawer(Gravity.LEFT);
            }
        });
        mMTv_attention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AttentionActivity.class));
                mDl.closeDrawer(Gravity.LEFT);
            }
        });

        mVersions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.setVersion();
            }
        });

    }


    private void initSD() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_GRANTED) {
            openSD();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            openSD();
        }
    }

    private void openSD() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            sd = Environment.getExternalStorageDirectory();
        }
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

    @OnClick(R.id.mImg_tool)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mImg_tool:
                mDl.openDrawer(Gravity.LEFT);
                break;
        }
    }

    @Override
    public void setBalance(BalanceBean balance) {
        SpUtil.setParam(Constants.BALANCE, balance.getResult().getBalance());
        mTv_balance.setText(balance.getResult().getBalance());
    }

    @Override
    public void setError(String msg) {
        ToastUtil.showShort("出现错误,抱歉");
    }

    @Override
    public void setMessage(MessageBean message) {
        MessageBean.ResultBean bean = message.getResult();
        mMTv_name.setText(bean.getUserName());
        mMTv_signature.setText(bean.getDescription());

        RequestOptions options = new RequestOptions().circleCrop().placeholder(R.mipmap.ee);
        Glide.with(this).load(bean.getPhoto()).apply(options).into(mImgTool);
        Glide.with(this).load(bean.getPhoto()).apply(options).into(mMIv);
    }

    @Override
    public void setVersion(NewVersion version) {
        NewVersion.ResultBean.InfoBean infoBean = version.getResult().getInfo();
        String infoBeanVersion = infoBean.getVersion();
        String url = infoBean.getDownload_url();//apk下载地址
        String name = Tools.getVersionName();
        if (!name.equals(infoBeanVersion)) {//更新
            //forceUpdate(url);

            initService(url);
            Log.e(TAG, "setVersion: " + url + "......././././" + name);
        } else {
            ToastUtil.showShort("不需要更新");
        }

    }

    private void initService(String url) {
        Intent intent = new Intent(this, MyServer.class);
        intent.putExtra("url", url);
        startService(intent);//启动服务
        bindService(intent, connection, Context.BIND_AUTO_CREATE);//绑定服务

    }

    @Override
    public void onPause() {
        super.onPause();
        unbindService(connection);
        connection = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopService(new Intent(this, MyServer.class));

        unbindService(connection);
        connection = null;
    }


    private void forceUpdate(final String url) {
//        new AlertDialog.Builder(this)
//                .setTitle("又有重大更新了!")
//                .setMessage("是否要更新呢?")
//                .setIcon(R.mipmap.ee)
//                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        setVersionDownload(url);
//                    }
//                })
//                .setNegativeButton("取消",null)
//                .show();

//        mDialog = new ProgressDialog(this);
//        mDialog.setTitle("又有重大更新了!");
//        mDialog.setMessage("是否要更新呢?");
//        mDialog.setIcon(R.mipmap.ee);
//        mDialog.setIndeterminate(false);
//        mDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//        mDialog.setButton(DialogInterface.BUTTON_POSITIVE, "立即更新", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                setVersionDownload(url);
//
//            }
//        });
//        mDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "我再想想", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        });
//        mDialog.show();

    }

}
