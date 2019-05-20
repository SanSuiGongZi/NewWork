package com.example.tolovepy.everywheretrip.mvp.presenter;

import com.example.tolovepy.everywheretrip.base.BasePresenter;
import com.example.tolovepy.everywheretrip.bean.BalanceBean;
import com.example.tolovepy.everywheretrip.bean.MessageBean;
import com.example.tolovepy.everywheretrip.bean.NewVersion;
import com.example.tolovepy.everywheretrip.mvp.model.Model;
import com.example.tolovepy.everywheretrip.mvp.view.IView;
import com.example.tolovepy.everywheretrip.net.ResultCallBack;

public class Presenter extends BasePresenter<IView> {

    Model model;

    @Override
    protected void initModel() {
        this.model = new Model();
        mModels.add(model);
    }

    public void getModel(){
        model.getBalance(new ResultCallBack<BalanceBean>() {
            @Override
            public void onSuccess(BalanceBean bean) {
                    if (mMvpView!=null){
                        mMvpView.setBalance(bean);
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

    public void newData() {
        model.newMessage(new ResultCallBack<MessageBean>() {
            @Override
            public void onSuccess(MessageBean bean) {
                if (bean != null) {
                    if (mMvpView != null) {
                        mMvpView.setMessage(bean);
                    }
                }
            }

            @Override
            public void onFail(String msg) {
                mMvpView.setError(msg);
            }
        });
    }

    //版本信息
    //网络检查版本是否需要更新
    public void setVersion(){
        model.getVersion(new ResultCallBack<NewVersion>() {
            @Override
            public void onSuccess(NewVersion bean) {
                if (bean!=null){
                    if (mMvpView!=null){
                        mMvpView.setVersion(bean);
                    }
                }
            }

            @Override
            public void onFail(String msg) {
                mMvpView.setError(msg);
            }
        });
    }

}
