package com.example.tolovepy.everywheretrip.mvp.model;

import com.example.tolovepy.everywheretrip.base.BaseModel;
import com.example.tolovepy.everywheretrip.bean.DemoBean;
import com.example.tolovepy.everywheretrip.bean.LoginInfo;
import com.example.tolovepy.everywheretrip.net.BaseObserver;
import com.example.tolovepy.everywheretrip.net.HttpUtils;
import com.example.tolovepy.everywheretrip.net.ResultCallBack;
import com.example.tolovepy.everywheretrip.net.RxUtils;
import com.example.tolovepy.everywheretrip.ui.api.MyApi;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class MessageModel extends BaseModel {

    public void loginSina(String uid, final ResultCallBack<LoginInfo> callBack) {
        MyApi apiserver = HttpUtils.getInstance().getApiserver(MyApi.mainUrl, MyApi.class);
        apiserver.postWeiboLogin(uid)
                .compose(RxUtils.<LoginInfo>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<LoginInfo>() {
                    @Override
                    public void error(String msg) {

                    }

                    @Override
                    protected void subscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(LoginInfo loginInfo) {
                        if (loginInfo != null) {
                            if (loginInfo.getCode() == MyApi.SUCCESS_CODE) {
                                callBack.onSuccess(loginInfo);
                            } else {
                                callBack.onFail(loginInfo.getDesc());
                            }
                        }
                    }
                });
    }

    public void getVerifyCode(final ResultCallBack<DemoBean> callBack) {

        MyApi myApi = HttpUtils.getInstance().getApiserver(MyApi.sBaseUrl, MyApi.class);
        Observable<DemoBean> code = myApi.getVerifyCode();
        code.compose(RxUtils.<DemoBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<DemoBean>() {
                    @Override
                    public void onNext(DemoBean demoBean) {
                        callBack.onSuccess(demoBean);
                    }

                    @Override
                    public void error(String msg) {

                    }

                    @Override
                    protected void subscribe(Disposable d) {
                        addDisposable(d);
                    }
                });

    }

}
