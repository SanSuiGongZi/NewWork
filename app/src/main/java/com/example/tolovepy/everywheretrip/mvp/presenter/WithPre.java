package com.example.tolovepy.everywheretrip.mvp.presenter;

import com.example.tolovepy.everywheretrip.base.BasePresenter;
import com.example.tolovepy.everywheretrip.bean.WithPath;
import com.example.tolovepy.everywheretrip.bean.WithState;
import com.example.tolovepy.everywheretrip.mvp.model.WithModel;
import com.example.tolovepy.everywheretrip.mvp.view.WithView;
import com.example.tolovepy.everywheretrip.net.ResultCallBack;

public class WithPre extends BasePresenter<WithView> {

    private WithModel mModel;

    @Override
    protected void initModel() {
        mModel = new WithModel();
        mModels.add(mModel);
    }

    /**
     * 伴米动态
     * @param page
     * @param banmiId
     */
    public void setTit(int page , int banmiId){
        mModel.getState(page, banmiId, new ResultCallBack<WithState>() {
            @Override
            public void onSuccess(WithState bean) {
                if (bean!=null){
                    if (mMvpView!=null){
                        mMvpView.getDataTitle(bean);
                    }
                }
            }

            @Override
            public void onFail(String msg) {
                if (mMvpView!=null){
                    mMvpView.getError(msg);
                }
            }
        });
    }

    /**
     * 伴米路线
     * @param page
     * @param banmiId
     */
    public void setStr(int page , int banmiId){
        mModel.getPath(page, banmiId, new ResultCallBack<WithPath>() {
            @Override
            public void onSuccess(WithPath bean) {
                if (bean!=null){
                    if (mMvpView!=null){
                        mMvpView.getDataStr(bean);
                    }
                }
            }

            @Override
            public void onFail(String msg) {
                if (mMvpView!=null){
                    mMvpView.getError(msg);
                }
            }
        });
    }

}
