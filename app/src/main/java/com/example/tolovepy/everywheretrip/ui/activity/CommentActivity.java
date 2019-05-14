package com.example.tolovepy.everywheretrip.ui.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.example.tolovepy.everywheretrip.R;
import com.example.tolovepy.everywheretrip.base.BaseActivity;
import com.example.tolovepy.everywheretrip.bean.AllComment;
import com.example.tolovepy.everywheretrip.mvp.presenter.AllCommentPer;
import com.example.tolovepy.everywheretrip.mvp.view.AllCommentView;
import com.example.tolovepy.everywheretrip.ui.adapter.MyAdapterAllComment;
import com.jaeger.library.StatusBarUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class CommentActivity extends BaseActivity<AllCommentView, AllCommentPer> implements AllCommentView {

    @BindView(R.id.goBack)
    ImageView goBack;
    @BindView(R.id.mRv)
    RecyclerView mRv;
    @BindView(R.id.mRefreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.mTool)
    Toolbar mTool;
    private MyAdapterAllComment mAllComment;
    private int page = 1;
    private ArrayList<AllComment.ResultBean.ReviewsBean> mList;

    @Override
    protected AllCommentPer initPresenter() {
        return new AllCommentPer();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_comment;
    }

    @Override
    protected void initView() {
        StatusBarUtil.setLightMode(this);
        Intent intent = getIntent();
        final int id = intent.getIntExtra("id", 0);

        mTool.setTitle("");
        setSupportActionBar(mTool);

        mList = new ArrayList<>();
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mAllComment = new MyAdapterAllComment(this);
        mRv.setAdapter(mAllComment);
        mPresenter.getAllData(id, page);

        //刷新
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                mList.clear();
                mPresenter.getAllData(id, page);
                mRefreshLayout.finishRefresh();
            }
        });

        //加载更多
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                mPresenter.getAllData(id, page);
                mRefreshLayout.finishLoadMore();
            }
        });

    }

    @OnClick(R.id.goBack)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void setAllData(AllComment allData) {
        mList.addAll(allData.getResult().getReviews());
        mAllComment.addList(mList);
    }

    @Override
    public void setAllError(String error) {

    }

}
