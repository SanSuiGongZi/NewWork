package com.example.tolovepy.everywheretrip.ui.fragment;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
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
import com.example.tolovepy.everywheretrip.mvp.presenter.Presenter;
import com.example.tolovepy.everywheretrip.mvp.view.IView;
import com.example.tolovepy.everywheretrip.ui.activity.AttentionActivity;
import com.example.tolovepy.everywheretrip.ui.activity.CollectionActivity;
import com.example.tolovepy.everywheretrip.ui.activity.MessageActivity;
import com.example.tolovepy.everywheretrip.util.ImageLoader;
import com.example.tolovepy.everywheretrip.util.SpUtil;
import com.example.tolovepy.everywheretrip.util.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Personage extends BaseFragment<IView, Presenter> implements IView {

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

    @Override
    protected void initView() {

        mPresenter.newData();
        mPresenter.getModel();

        String balance = (String) SpUtil.getParam(Constants.BALANCE, "0");
        if (TextUtils.isEmpty(balance)) {
            mTvMoney.setText(balance);
        }

    }

    @OnClick({R.id.rl_tit, R.id.collect, R.id.attention})
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
        ImageLoader.setCircleImage(getActivity(),photo,mIv,R.mipmap.ee);

//        RequestOptions options = new RequestOptions().circleCrop().placeholder(R.mipmap.ee);
//        Glide.with(this).load(bean.getPhoto()).apply(options).into(mIv);
    }

}
