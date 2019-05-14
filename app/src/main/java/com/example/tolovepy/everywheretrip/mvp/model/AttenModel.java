package com.example.tolovepy.everywheretrip.mvp.model;

import com.example.tolovepy.everywheretrip.base.BaseModel;
import com.example.tolovepy.everywheretrip.bean.AttenBeanDelete;
import com.example.tolovepy.everywheretrip.bean.AttenBeanInsert;
import com.example.tolovepy.everywheretrip.bean.AttenList;
import com.example.tolovepy.everywheretrip.net.BaseObserver;
import com.example.tolovepy.everywheretrip.net.HttpUtils;
import com.example.tolovepy.everywheretrip.net.ResultCallBack;
import com.example.tolovepy.everywheretrip.net.RxUtils;
import com.example.tolovepy.everywheretrip.ui.api.MyApi;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class AttenModel extends BaseModel {

    /**
     * 获取关注列表
     *
     * @param page
     * @param callBack
     */
    public void getList(int page, final ResultCallBack<AttenList> callBack) {
        MyApi myApi = HttpUtils.getInstance().getApiserver(MyApi.mainUrl, MyApi.class);
        Observable<AttenList> setList = myApi.setList(MyApi.param, page);
        setList.compose(RxUtils.<AttenList>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<AttenList>() {
                    @Override
                    public void onNext(AttenList attenList) {
                        callBack.onSuccess(attenList);
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
     * 关注
     *
     * @param banmild
     * @param callBack
     */
    public void getPay(int banmild, final ResultCallBack<AttenBeanInsert> callBack) {
        MyApi myApi = HttpUtils.getInstance().getApiserver(MyApi.mainUrl, MyApi.class);
        Observable<AttenBeanInsert> setInsert = myApi.setInsert(MyApi.param, banmild);
        setInsert.compose(RxUtils.<AttenBeanInsert>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<AttenBeanInsert>() {
                    @Override
                    public void onNext(AttenBeanInsert attenBeanInsert) {
                        callBack.onSuccess(attenBeanInsert);
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
     * 取消关注
     *
     * @param banmild
     * @param callBack
     */
    public void getOff(int banmild, final ResultCallBack<AttenBeanDelete> callBack) {
        MyApi myApi = HttpUtils.getInstance().getApiserver(MyApi.mainUrl, MyApi.class);
        Observable<AttenBeanDelete> setDelete = myApi.setDelete(MyApi.param, banmild);
        setDelete.compose(RxUtils.<AttenBeanDelete>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<AttenBeanDelete>() {
                    @Override
                    public void onNext(AttenBeanDelete attenBeanDelete) {
                        callBack.onSuccess(attenBeanDelete);
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
