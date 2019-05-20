package com.example.tolovepy.everywheretrip.ui.fragment;


import android.Manifest;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tolovepy.everywheretrip.R;
import com.example.tolovepy.everywheretrip.base.BaseFragment;
import com.example.tolovepy.everywheretrip.base.Constants;
import com.example.tolovepy.everywheretrip.bean.BalanceBean;
import com.example.tolovepy.everywheretrip.bean.MessageBean;
import com.example.tolovepy.everywheretrip.bean.NewVersion;
import com.example.tolovepy.everywheretrip.mvp.presenter.Presenter;
import com.example.tolovepy.everywheretrip.mvp.view.IView;
import com.example.tolovepy.everywheretrip.net.MyServer;
import com.example.tolovepy.everywheretrip.ui.activity.AttentionActivity;
import com.example.tolovepy.everywheretrip.ui.activity.CollectionActivity;
import com.example.tolovepy.everywheretrip.ui.activity.MessageActivity;
import com.example.tolovepy.everywheretrip.util.ImageLoader;
import com.example.tolovepy.everywheretrip.util.SpUtil;
import com.example.tolovepy.everywheretrip.util.ToastUtil;
import com.example.tolovepy.everywheretrip.util.Tools;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Personage extends BaseFragment<IView, Presenter> implements IView {

    private static final String TAG = "Fragment_Personage";
    @BindView(R.id.mIv)
    ImageView mIv;              //头像
    @BindView(R.id.mTv_name)
    TextView mTvName;           //用户名
    @BindView(R.id.mTv_signature)
    TextView mTvSignature;      //个性签名
    @BindView(R.id.rl_tit)
    RelativeLayout rlTit;       //头像父布局
    @BindView(R.id.mTv_money)
    TextView mTvMoney;          //余额
    @BindView(R.id.rl_money)
    CardView rlMoney;
    @BindView(R.id.img_kaquan)
    ImageView imgKaquan;
    @BindView(R.id.coupon)
    RelativeLayout coupon;
    @BindView(R.id.img_journey)
    ImageView imgJourney;
    @BindView(R.id.journey)
    RelativeLayout journey;
    @BindView(R.id.img_collect)
    ImageView imgCollect;
    @BindView(R.id.collect)
    RelativeLayout collect;
    @BindView(R.id.img_attention)
    ImageView imgAttention;
    @BindView(R.id.attention)
    RelativeLayout attention;
    @BindView(R.id.m_card)
    CardView mCard;
    @BindView(R.id.img_service)
    ImageView imgService;
    @BindView(R.id.service)
    LinearLayout service;
    @BindView(R.id.img_feedback)
    ImageView imgFeedback;
    @BindView(R.id.feedback)
    LinearLayout feedback;
    @BindView(R.id.img_versions)
    ImageView imgVersions;
    @BindView(R.id.versions)
    LinearLayout versions;
    @BindView(R.id.mTv_version)
    TextView mTv_version;
    private MyServer.XiaZai binder;
    private File sd;
    private ProgressDialog mDialog;

    public Fragment_Personage() {
        // Required empty public constructor
    }


    @Override
    protected Presenter initPresenter() {
        return new Presenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_personage;
    }


    private ServiceConnection connection = new ServiceConnection() {
        //绑定服务调用
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder = (MyServer.XiaZai) service;

            //开启下载
            binder.setVersionDownload(getActivity(), sd, mDialog);

        }

        //解绑服务调用
        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


    @Override
    protected void initView() {
        initSD();
        mPresenter.newData();
        mPresenter.getModel();

        String balance = (String) SpUtil.getParam(Constants.BALANCE, "0");
        if (TextUtils.isEmpty(balance)) {
            mTvMoney.setText(balance);
        }

    }

    @OnClick({R.id.rl_tit, R.id.collect, R.id.attention,R.id.versions})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_tit:
                //头像父布局
                startActivity(new Intent(getActivity(), MessageActivity.class));
                break;
            case R.id.collect:
                //收藏
                startActivity(new Intent(getActivity(), CollectionActivity.class));
                break;
            case R.id.attention:
                //关注
                startActivity(new Intent(getActivity(), AttentionActivity.class));
                break;
            case R.id.versions:
                //获取版本信息
                forceUpdate();
                break;
        }
    }


    @Override
    public void setBalance(BalanceBean balance) {
        SpUtil.setParam(Constants.BALANCE, balance.getResult().getBalance());
        mTvMoney.setText(balance.getResult().getBalance());
    }

    @Override
    public void setError(String msg) {
        ToastUtil.showShort("出现错误,抱歉");
    }

    @Override
    public void setMessage(MessageBean message) {
        MessageBean.ResultBean bean = message.getResult();
        mTvName.setText(bean.getUserName());
        mTvSignature.setText(bean.getDescription());
        String photo = bean.getPhoto();
        ImageLoader.setCircleImage(getActivity(), photo, mIv, R.mipmap.ee);

       /* RequestOptions options = new RequestOptions().circleCrop().placeholder(R.mipmap.ee);
        Glide.with(this).load(bean.getPhoto()).apply(options).into(mIv);*/
    }

    private void initSD() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_GRANTED) {
            openSD();
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
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
    public void setVersion(NewVersion version) {
        NewVersion.ResultBean.InfoBean infoBean = version.getResult().getInfo();
        String infoBeanVersion = infoBean.getVersion();

        mTv_version.setText("当前版本:"+infoBeanVersion);

        String url = infoBean.getDownload_url();//apk下载地址
        String name = Tools.getVersionName();
        if (!name.equals(infoBeanVersion)) {//更新

            initService(url);
            Log.e(TAG, "setVersion: " + url + "......././././" + name);
        } else {
            ToastUtil.showShort("不需要更新");
        }

    }

    private void forceUpdate() {
       /* new AlertDialog.Builder(getActivity())
                .setTitle("又有重大更新了!")
                .setMessage("是否要更新呢?")
                .setIcon(R.mipmap.ee)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setVersionDownload(url);
                    }
                })
                .setNegativeButton("取消",null)
                .show();*/

        mDialog = new ProgressDialog(getActivity());
        mDialog.setTitle("又有重大更新了!");
        mDialog.setMessage("是否要更新呢?");
        mDialog.setIcon(R.mipmap.ee);
        mDialog.setIndeterminate(false);

        mDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

        mDialog.setButton(DialogInterface.BUTTON_POSITIVE, "立即更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //setVersionDownload(url);
                mPresenter.setVersion();
            }
        });
        mDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "我再想想", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //dialog.dismiss();
            }
        });
        mDialog.show();

    }

    private void initService(String url) {
        Intent intent = new Intent(getActivity(), MyServer.class);
        intent.putExtra("url", url);
        getActivity().startService(intent);//启动服务
        getActivity().bindService(intent, connection, Context.BIND_AUTO_CREATE);//绑定服务

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (connection != null) {
            getActivity().stopService(new Intent(getActivity(), MyServer.class));
            getActivity().unbindService(connection);
            connection = null;
        }
    }

    /*@Override
    public void onPause() {
        super.onPause();
        if (connection != null) {
            getActivity().unbindService(connection);
            connection = null;
        }
    }*/

    /*@Override
    public void setBalance(BalanceBean balance) {
        SpUtil.setParam(Constants.BALANCE, balance.getResult().getBalance());
        mTv_balance.setText(balance.getResult().getBalance());
    }

    @Override
    public void setMessage(MessageBean message) {
        MessageBean.ResultBean bean = message.getResult();
        mMTv_name.setText(bean.getUserName());
        mMTv_signature.setText(bean.getDescription());

        RequestOptions options = new RequestOptions().circleCrop().placeholder(R.mipmap.ee);
        Glide.with(this).load(bean.getPhoto()).apply(options).into(mMIv);
    }*/

}
