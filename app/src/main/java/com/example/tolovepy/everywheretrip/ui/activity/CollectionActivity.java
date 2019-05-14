package com.example.tolovepy.everywheretrip.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tolovepy.everywheretrip.R;
import com.example.tolovepy.everywheretrip.base.BaseActivity;
import com.example.tolovepy.everywheretrip.bean.CollectionList;
import com.example.tolovepy.everywheretrip.mvp.presenter.CollectionPre;
import com.example.tolovepy.everywheretrip.mvp.view.CollectionView;
import com.example.tolovepy.everywheretrip.ui.adapter.MyAdapterCollection;
import com.example.tolovepy.everywheretrip.util.ToastUtil;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class CollectionActivity extends BaseActivity<CollectionView, CollectionPre> implements CollectionView {


    @BindView(R.id.mRv_stay)
    RecyclerView mRvStay;
    @BindView(R.id.mTool_attention)
    Toolbar mToolAttention;
    @BindView(R.id.tv_tool)
    TextView tvTool;
    @BindView(R.id.img_replace)
    ImageView imgReplace;
    private ArrayList<CollectionList.ResultBean.CollectedRoutesBean> mList;
    private MyAdapterCollection mCollection;

    @Override
    protected CollectionPre initPresenter() {
        return new CollectionPre();
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
        tvTool.setText("我的收藏");

        mRvStay.setLayoutManager(new LinearLayoutManager(this));
        mList = new ArrayList<>();
        mCollection = new MyAdapterCollection(this);
        mRvStay.setAdapter(mCollection);
        showLoading();
    }

    @Override
    protected void initData() {
        mPresenter.setLists(1);
    }

    @Override
    public void setData(CollectionList collectionList) {
        mList.addAll(collectionList.getResult().getCollectedRoutes());
        mCollection.addList(mList);
        hideLoading();
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
