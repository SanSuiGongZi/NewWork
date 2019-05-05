package com.example.tolovepy.everywheretrip.mvp.model;

import com.example.tolovepy.everywheretrip.base.BaseModel;
import com.example.tolovepy.everywheretrip.bean.MainBean;
import com.example.tolovepy.everywheretrip.net.BaseObserver;
import com.example.tolovepy.everywheretrip.net.HttpUtils;
import com.example.tolovepy.everywheretrip.net.ResultCallBack;
import com.example.tolovepy.everywheretrip.net.RxUtils;
import com.example.tolovepy.everywheretrip.ui.api.MyApi;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class HomeModel extends BaseModel {

    public void model(final ResultCallBack<MainBean> callBack) {
        MyApi myApi = HttpUtils.getInstance().getApiserver(MyApi.mainUrl, MyApi.class);
        Observable<MainBean> apiMain = myApi.getMain();
        apiMain.compose(RxUtils.<MainBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<MainBean>() {
                    @Override
                    public void onNext(MainBean mainBean) {
                        callBack.onSuccess(mainBean);
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
