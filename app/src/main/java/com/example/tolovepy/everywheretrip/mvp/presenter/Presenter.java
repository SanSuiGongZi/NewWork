package com.example.tolovepy.everywheretrip.mvp.presenter;

import com.example.tolovepy.everywheretrip.base.BasePresenter;
import com.example.tolovepy.everywheretrip.bean.DemoBean;
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
        model.getVerifyCode(new ResultCallBack<DemoBean>() {
            @Override
            public void onSuccess(DemoBean bean) {

            }

            @Override
            public void onFail(String msg) {

            }
        });
    }

}
