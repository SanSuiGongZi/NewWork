package com.example.tolovepy.everywheretrip.mvp.model;

import com.example.tolovepy.everywheretrip.base.BaseModel;
import com.example.tolovepy.everywheretrip.bean.WebBean;
import com.example.tolovepy.everywheretrip.net.BaseObserver;
import com.example.tolovepy.everywheretrip.net.HttpUtils;
import com.example.tolovepy.everywheretrip.net.ResultCallBack;
import com.example.tolovepy.everywheretrip.net.RxUtils;
import com.example.tolovepy.everywheretrip.ui.api.MyApi;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class WebModel extends BaseModel {

    public void getWeb(final ResultCallBack<WebBean> callBack){
        MyApi myApi = HttpUtils.getInstance().getApiserver(MyApi.mainUrl, MyApi.class);
        Observable<WebBean> setWeb = myApi.setWeb(MyApi.param);
        setWeb.compose(RxUtils.<WebBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<WebBean>() {
                    @Override
                    public void onNext(WebBean webBean) {
                        callBack.onSuccess(webBean);
                    }

                    @Override
                    public void error(String msg) {
                        callBack.onFail(msg);
                    }

                    @Override
                    protected void subscribe(Disposable d) {
                        addDisposable(d);
                    }
                });
    }

}
