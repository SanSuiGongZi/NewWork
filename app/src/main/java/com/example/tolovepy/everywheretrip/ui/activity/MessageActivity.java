package com.example.tolovepy.everywheretrip.ui.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.tolovepy.everywheretrip.R;
import com.example.tolovepy.everywheretrip.base.BaseActivity;
import com.example.tolovepy.everywheretrip.base.Constants;
import com.example.tolovepy.everywheretrip.bean.MessageBean;
import com.example.tolovepy.everywheretrip.mvp.presenter.MyMessagePre;
import com.example.tolovepy.everywheretrip.mvp.view.MyMessageView;
import com.example.tolovepy.everywheretrip.util.SpUtil;
import com.jaeger.library.StatusBarUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class MessageActivity extends BaseActivity<MyMessageView, MyMessagePre> implements MyMessageView {

    private static final String TAG = "MessageActivity";
    @BindView(R.id.img_replace)
    ImageView imgReplace;
    @BindView(R.id.mTool_mess)
    Toolbar mToolMess;
    @BindView(R.id.img_head)
    ImageView imgHead;
    @BindView(R.id.coupon)
    RelativeLayout coupon;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.mTv_names)
    TextView mTvNames;
    @BindView(R.id.mTv_user)
    TextView mTvUser;
    @BindView(R.id.journey)
    RelativeLayout journey;
    @BindView(R.id.view2)
    View view2;
    @BindView(R.id.mTv_sex)
    TextView mTvSex;
    @BindView(R.id.collect)
    RelativeLayout collect;
    @BindView(R.id.view3)
    View view3;
    @BindView(R.id.mTv_sigs)
    TextView mTvSigs;
    @BindView(R.id.mTv_sig)
    TextView mTvSig;
    @BindView(R.id.attention)
    RelativeLayout attention;
    @BindView(R.id.out_psw)
    RelativeLayout outPsw;
    @BindView(R.id.view4)
    View view4;
    @BindView(R.id.out_phone)
    RelativeLayout outPhone;
    @BindView(R.id.mBut_exit)
    Button mButExit;
    private PopupWindow mWindow;
    private TextView mTv_nos;
    private TextView mTv_man;
    private TextView mTv_lady;
    private TextView mTv_secrecy;


    @Override
    protected MyMessagePre initPresenter() {
        return new MyMessagePre();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_message;
    }

    @Override
    protected void initView() {
        StatusBarUtil.setLightMode(this);
        mPresenter.newData();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        initView();
        showLoading();
        mPresenter.newData();
    }


    @OnClick({R.id.img_replace, R.id.attention, R.id.out_psw, R.id.out_phone, R.id.mBut_exit, R.id.img_head, R.id.journey, R.id.collect})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_replace:
                //返回
                startActivity(new Intent(MessageActivity.this, MainActivity.class));
                finish();
                break;
            case R.id.out_psw:
                //修改密码
                break;
            case R.id.out_phone:
                //修改手机号
                break;
            case R.id.mBut_exit:
                //退出登录
                startActivity(new Intent(MessageActivity.this,LoginActivity.class));
                SpUtil.setParam(Constants.TOKEN,"");
                finish();
                break;
            case R.id.img_head:
                //设置头像
                Intent intentImg = new Intent(MessageActivity.this, HeadActivity.class);
                startActivity(intentImg);
                break;
            case R.id.journey:
                //设置昵称
                Intent intentUser = new Intent(MessageActivity.this, Personage_DataActivity.class);
                intentUser.putExtra("data", mTvUser.getText());
                intentUser.putExtra("toolData", mTvNames.getText());
                intentUser.putExtra("isss", "1");
                startActivity(intentUser);
                break;
            case R.id.collect:
                //设置性别
                initpopupSex();
                break;
            case R.id.attention:
                //设置个性签名
                Intent intentPer = new Intent(MessageActivity.this, Personage_DataActivity.class);
                intentPer.putExtra("data", mTvSig.getText());
                intentPer.putExtra("toolData", mTvSigs.getText());
                intentPer.putExtra("isss", "2");
                startActivity(intentPer);
                break;
        }
    }

    private void initpopupSex() {
        View view = LayoutInflater.from(this).inflate(R.layout.item_popup_sex, null);
        mWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        mTv_nos = view.findViewById(R.id.mTv_nos);
        mTv_man = view.findViewById(R.id.mTv_man);
        mTv_lady = view.findViewById(R.id.mTv_lady);
        mTv_secrecy = view.findViewById(R.id.mTv_secrecy);

        mWindow.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.c_60s)));
        mWindow.setOutsideTouchable(true);
        //设置除布局外的点击事件
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //关闭popop
                mWindow.dismiss();
            }
        });
        mWindow.showAtLocation(view, 0, 0, Gravity.BOTTOM);

        mTv_nos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWindow.dismiss();
            }
        });

        mTv_man.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTvSex.setText(mTv_man.getText());
                mWindow.dismiss();
            }
        });
        mTv_lady.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTvSex.setText(mTv_lady.getText());
                mWindow.dismiss();
            }
        });
        mTv_secrecy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTvSex.setText(mTv_secrecy.getText());
                mWindow.dismiss();
            }
        });

        SpUtil.setParam(Constants.GENDER, mTvSex.getText().toString().trim());
        mPresenter.outData();
    }

    @Override
    public void setMessage(MessageBean message) {
        MessageBean.ResultBean bean = message.getResult();
        mTvUser.setText(bean.getUserName());
        mTvSig.setText(bean.getDescription());
        String gender = bean.getGender();
        if (gender.equals("M")){
            mTvSex.setText("男");
        }else if (gender.equals("F")){
            mTvSex.setText("女");
        }else {
            mTvSex.setText("保密");
        }

        RequestOptions options = new RequestOptions().circleCrop().placeholder(R.mipmap.ee);
        Glide.with(this).load(bean.getPhoto()).apply(options).into(imgHead);
        hideLoading();
    }

    @Override
    public void setError(String msg) {
        Log.e(TAG, "setError: "+msg );
    }
}
