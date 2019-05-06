package com.example.tolovepy.everywheretrip.mvp.presenter;

import com.example.tolovepy.everywheretrip.base.BasePresenter;
import com.example.tolovepy.everywheretrip.bean.DemoBean;
import com.example.tolovepy.everywheretrip.mvp.model.Model;
import com.example.tolovepy.everywheretrip.mvp.view.CodeView;
import com.example.tolovepy.everywheretrip.net.ResultCallBack;

public class CodePre extends BasePresenter<CodeView> {

    private Model model;

    @Override
    protected void initModel() {
        model = new Model();
        mModels.add(model);
    }

    public void getCode(){
        model.getVerifyCode(new ResultCallBack<DemoBean>() {
            @Override
            public void onSuccess(DemoBean bean) {
                mMvpView.setCode(bean.getData());
            }

            @Override
            public void onFail(String msg) {
                mMvpView.setErrors(msg);
            }
        });
    }

}
