package com.example.tolovepy.everywheretrip.ui.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
    @BindView(R.id.img_head)
    ImageView imgHead;
    @BindView(R.id.coupon)
    RelativeLayout coupon;
    @BindView(R.id.mTv_user)
    TextView mTvUser;
    @BindView(R.id.journey)
    RelativeLayout journey;
    @BindView(R.id.mTv_sex)
    TextView mTvSex;
    @BindView(R.id.collect)
    RelativeLayout collect;
    @BindView(R.id.mTv_sig)
    TextView mTvSig;
    @BindView(R.id.attention)
    RelativeLayout attention;
    private int PHOTO = 1;
    private int DATA = 2;
    private int USER = 5;
    private int SIG = 15;
    private PopupWindow mWindow;
    private TextView mTv_nos;
    private TextView mTv_man;
    private TextView mTv_lady;
    private TextView mTv_secrecy;

    @Override
    protected EmptyPre initPresenter() {
        return new EmptyPre();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_message;
    }

    @OnClick({R.id.img_replace, R.id.attention, R.id.out_psw, R.id.out_phone, R.id.mBut_exit, R.id.img_head, R.id.journey, R.id.collect})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_replace:
                //返回
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
                break;
            case R.id.img_head:
                //设置头像
                Intent intentImg = new Intent(MessageActivity.this, HeadActivity.class);
                startActivity(intentImg);
                break;
            case R.id.journey:
                //设置昵称
                Intent intentUser = new Intent(MessageActivity.this, Personage_DataActivity.class);
                intentUser.putExtra("data", mTvUser.getText().toString().trim());
                startActivityForResult(intentUser, USER);
                break;
            case R.id.collect:
                //设置性别
                initpopupSex();
                break;
            case R.id.attention:
                //设置个性签名
                Intent intentPer = new Intent(MessageActivity.this, Personage_DataActivity.class);
                intentPer.putExtra("data", mTvSig.getText().toString().trim());
                startActivityForResult(intentPer, SIG);
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

        mWindow.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.c_60)));
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

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == USER && resultCode == DATA) {
            String name = data.getStringExtra("trim");
            mTvUser.setText(name);
        } else if (requestCode == SIG && resultCode == DATA) {
            String name = data.getStringExtra("trim");
            mTvSig.setText(name);
        }
    }
}
