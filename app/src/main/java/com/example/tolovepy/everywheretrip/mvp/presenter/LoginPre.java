package com.example.tolovepy.everywheretrip.mvp.presenter;

import com.example.tolovepy.everywheretrip.base.BasePresenter;
import com.example.tolovepy.everywheretrip.bean.LoginBean;
import com.example.tolovepy.everywheretrip.mvp.model.LoginModel;
import com.example.tolovepy.everywheretrip.mvp.view.LoginView;
import com.example.tolovepy.everywheretrip.net.ResultCallBack;

public class LoginPre extends BasePresenter<LoginView> {


    LoginModel loginModel;

    @Override
    protected void initModel() {
        this.loginModel = new LoginModel();
        mModels.add(loginModel);
    }

    public void getData(){
        loginModel.getLogin(new ResultCallBack<LoginBean>() {
            @Override
            public void onSuccess(LoginBean bean) {
                if (bean!=null){
                    if (mMvpView!=null){
                        mMvpView.LoginData(bean);
                    }
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }

}
