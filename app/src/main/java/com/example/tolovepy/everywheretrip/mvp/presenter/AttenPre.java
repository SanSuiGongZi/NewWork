package com.example.tolovepy.everywheretrip.mvp.presenter;

import com.example.tolovepy.everywheretrip.base.BasePresenter;
import com.example.tolovepy.everywheretrip.bean.AttenBeanDelete;
import com.example.tolovepy.everywheretrip.bean.AttenBeanInsert;
import com.example.tolovepy.everywheretrip.bean.AttenList;
import com.example.tolovepy.everywheretrip.mvp.model.AttenModel;
import com.example.tolovepy.everywheretrip.mvp.view.AttenView;
import com.example.tolovepy.everywheretrip.net.ResultCallBack;

public class AttenPre extends BasePresenter<AttenView> {

    private AttenModel mModel;

    @Override
    protected void initModel() {
        mModel = new AttenModel();
        mModels.add(mModel);
    }

    //关注
    public void getInsert(final int banmild) {
        mModel.getPay(banmild, new ResultCallBack<AttenBeanInsert>() {
            @Override
            public void onSuccess(AttenBeanInsert bean) {
                if (bean != null) {
                    if (mMvpView != null) {
                        mMvpView.setToast(bean.getResult().getMessage());
                    }
                }
            }

            @Override
            public void onFail(String msg) {
                if (mMvpView != null) {
                    mMvpView.setError(msg);
                }
            }
        });
    }

    //取消关注
    public void getDelete(int banmild) {
        mModel.getOff(banmild, new ResultCallBack<AttenBeanDelete>() {
            @Override
            public void onSuccess(AttenBeanDelete bean) {
                if (bean != null) {
                    if (mMvpView != null) {
                        mMvpView.setToast(bean.getResult().getMessage());
                    }
                }
            }

            @Override
            public void onFail(String msg) {
                if (mMvpView != null) {
                    mMvpView.setError(msg);
                }
            }
        });
    }


    //关注列表
    public void getDataAtten(int page) {
        mModel.getList(page, new ResultCallBack<AttenList>() {
            @Override
            public void onSuccess(AttenList bean) {
                if (bean!=null){
                    if (mMvpView!=null){
                        mMvpView.setAttenList(bean);
                    }
                }
            }

            @Override
            public void onFail(String msg) {
                if (mMvpView!=null){
                    mMvpView.setError(msg);
                }
            }
        });
    }

}
