package com.example.tolovepy.everywheretrip.mvp.model;

import com.example.tolovepy.everywheretrip.base.BaseModel;
import com.example.tolovepy.everywheretrip.bean.MainDetailsBean;
import com.example.tolovepy.everywheretrip.net.BaseObserver;
import com.example.tolovepy.everywheretrip.net.HttpUtils;
import com.example.tolovepy.everywheretrip.net.ResultCallBack;
import com.example.tolovepy.everywheretrip.net.RxUtils;
import com.example.tolovepy.everywheretrip.bean.CollectionNo;
import com.example.tolovepy.everywheretrip.bean.CollectionOk;
import com.example.tolovepy.everywheretrip.ui.api.MyApi;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class DetailsModel extends BaseModel {

    //主页详情数据
    public void getDatas(int id, final ResultCallBack<MainDetailsBean> callBack) {
        MyApi apiserver = HttpUtils.getInstance().getApiserver(MyApi.mainUrl, MyApi.class);
        Observable<MainDetailsBean> setDetails = apiserver.setDetails(id, MyApi.param);
        setDetails.compose(RxUtils.<MainDetailsBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<MainDetailsBean>() {
                    @Override
                    public void onNext(MainDetailsBean mainDetailsBean) {
                        callBack.onSuccess(mainDetailsBean);
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

    //收藏数据
    public void getCollectionOk(int id, final ResultCallBack<CollectionOk> callBack) {
        MyApi myApi = HttpUtils.getInstance().getApiserver(MyApi.mainUrl, MyApi.class);
        Observable<CollectionOk> collectionOk = myApi.setCollectionOk(MyApi.param, id);
        collectionOk.compose(RxUtils.<CollectionOk>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<CollectionOk>() {
                    @Override
                    public void onNext(CollectionOk collectionOk) {
                        callBack.onSuccess(collectionOk);
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

    //取消收藏
    public void getCollectionNo(int id, final ResultCallBack<CollectionNo> callBack) {
        MyApi myApi = HttpUtils.getInstance().getApiserver(MyApi.mainUrl, MyApi.class);
        Observable<CollectionNo> collectionNo = myApi.setCollectionNo(MyApi.param, id);
        collectionNo.compose(RxUtils.<CollectionNo>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<CollectionNo>() {
                    @Override
                    public void onNext(CollectionNo collectionNo) {
                        callBack.onSuccess(collectionNo);
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
