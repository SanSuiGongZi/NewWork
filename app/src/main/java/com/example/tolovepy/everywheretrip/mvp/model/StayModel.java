package com.example.tolovepy.everywheretrip.mvp.model;

import com.example.tolovepy.everywheretrip.base.BaseModel;
import com.example.tolovepy.everywheretrip.bean.StayBean;
import com.example.tolovepy.everywheretrip.net.BaseObserver;
import com.example.tolovepy.everywheretrip.net.HttpUtils;
import com.example.tolovepy.everywheretrip.net.ResultCallBack;
import com.example.tolovepy.everywheretrip.net.RxUtils;
import com.example.tolovepy.everywheretrip.ui.api.MyApi;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class StayModel extends BaseModel {

    public void getModel(int page , final ResultCallBack<StayBean> callBack){
        MyApi myApi = HttpUtils.getInstance().getApiserver(MyApi.mainUrl, MyApi.class);
        Observable<StayBean> data = myApi.getData(page);
        data.compose(RxUtils.<StayBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<StayBean>() {
                    @Override
                    public void onNext(StayBean stayBean) {
                        callBack.onSuccess(stayBean);
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
