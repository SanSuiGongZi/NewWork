package com.example.tolovepy.everywheretrip.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.example.tolovepy.everywheretrip.R;
import com.example.tolovepy.everywheretrip.base.BaseActivity;
import com.example.tolovepy.everywheretrip.bean.AttenList;
import com.example.tolovepy.everywheretrip.mvp.presenter.AttenPre;
import com.example.tolovepy.everywheretrip.mvp.view.AttenView;
import com.example.tolovepy.everywheretrip.ui.adapter.MyAdapterAtten;
import com.example.tolovepy.everywheretrip.util.ToastUtil;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class AttentionActivity extends BaseActivity<AttenView, AttenPre> implements AttenView {


    @BindView(R.id.mRv_stay)
    RecyclerView mRvStay;
    @BindView(R.id.mTool_attention)
    Toolbar mToolAttention;
    @BindView(R.id.img_replace)
    ImageView imgReplace;
    private MyAdapterAtten mAdapterAtten;
    private ArrayList<AttenList.ResultBean.BanmiBean> mList;

    @Override
    protected AttenPre initPresenter() {
        return new AttenPre();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_stay;
    }

    @Override
    protected void initView() {
        StatusBarUtil.setLightMode(this);
        mToolAttention.setVisibility(View.VISIBLE);
        mToolAttention.setTitle("");
        setSupportActionBar(mToolAttention);

        mRvStay.setLayoutManager(new LinearLayoutManager(this));
        mList = new ArrayList<>();
        mAdapterAtten = new MyAdapterAtten(this, true);
        mRvStay.setAdapter(mAdapterAtten);

        mAdapterAtten.setOnImg(new MyAdapterAtten.OnImg() {
            @Override
            public void onItemImg(int position) {
                boolean isFollowed = mList.get(position).isIsFollowed();
                int id = mList.get(position).getId();
                if (isFollowed) {
                    mPresenter.getDelete(id);

                } else {
                    mPresenter.getInsert(id);
                }
                mPresenter.getDataAtten(1);
            }
        });

        mPresenter.getDataAtten(1);
    }

    @Override
    public void setAttenList(AttenList bean) {
        if (bean != null) {
            mList.clear();
            mList.addAll(bean.getResult().getBanmi());
            mAdapterAtten.addList(mList);
        }
    }

    @Override
    public void setToast(String str) {
        ToastUtil.showShort(str);
    }

    @Override
    public void setError(String error) {
        ToastUtil.showShort(error);
    }

    @OnClick(R.id.img_replace)
    public void onViewClicked() {
        finish();
    }
}
