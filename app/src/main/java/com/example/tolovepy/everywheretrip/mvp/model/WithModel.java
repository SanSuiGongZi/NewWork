package com.example.tolovepy.everywheretrip.mvp.model;

import com.example.tolovepy.everywheretrip.base.BaseModel;
import com.example.tolovepy.everywheretrip.bean.WithPath;
import com.example.tolovepy.everywheretrip.bean.WithState;
import com.example.tolovepy.everywheretrip.net.BaseObserver;
import com.example.tolovepy.everywheretrip.net.HttpUtils;
import com.example.tolovepy.everywheretrip.net.ResultCallBack;
import com.example.tolovepy.everywheretrip.net.RxUtils;
import com.example.tolovepy.everywheretrip.ui.api.MyApi;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class WithModel extends BaseModel {

    /**
     * 伴米动态
     *
     * @param page
     * @param banmiId
     * @param callBack
     */
    public void getState(int page, int banmiId, final ResultCallBack<WithState> callBack) {
        MyApi myApi = HttpUtils.getInstance().getApiserver(MyApi.mainUrl, MyApi.class);
        Observable<WithState> setState = myApi.setState(MyApi.param, banmiId, page);
        setState.compose(RxUtils.<WithState>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<WithState>() {
                    @Override
                    public void onNext(WithState withState) {
                        callBack.onSuccess(withState);
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

    /**
     * 伴米线路
     *
     * @param page
     * @param banmiId
     * @param callBack
     */
    public void getPath(int page, int banmiId, final ResultCallBack<WithPath> callBack) {
        MyApi myApi = HttpUtils.getInstance().getApiserver(MyApi.mainUrl, MyApi.class);
        Observable<WithPath> setPath = myApi.setPath(MyApi.param, banmiId, page);
        setPath.compose(RxUtils.<WithPath>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<WithPath>() {
                    @Override
                    public void onNext(WithPath withPath) {
                        callBack.onSuccess(withPath);
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
