package com.example.tolovepy.everywheretrip.mvp.presenter;

import com.example.tolovepy.everywheretrip.base.BasePresenter;
import com.example.tolovepy.everywheretrip.bean.WebBean;
import com.example.tolovepy.everywheretrip.mvp.model.WebModel;
import com.example.tolovepy.everywheretrip.mvp.view.WebView;
import com.example.tolovepy.everywheretrip.net.ResultCallBack;

public class WebPre extends BasePresenter<WebView> {

    private WebModel mWebModel;

    @Override
    protected void initModel() {
        mWebModel = new WebModel();
        mModels.add(mWebModel);
    }

    //获取数据
    public void getSpecial(){
        mWebModel.getWeb(new ResultCallBack<WebBean>() {
            @Override
            public void onSuccess(WebBean bean) {
                if (bean!=null){
                    if (mMvpView!=null){
                        mMvpView.setWeb(bean);
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
