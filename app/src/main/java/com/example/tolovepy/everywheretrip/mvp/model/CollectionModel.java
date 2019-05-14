package com.example.tolovepy.everywheretrip.mvp.model;

import com.example.tolovepy.everywheretrip.base.BaseModel;
import com.example.tolovepy.everywheretrip.net.BaseObserver;
import com.example.tolovepy.everywheretrip.net.HttpUtils;
import com.example.tolovepy.everywheretrip.net.ResultCallBack;
import com.example.tolovepy.everywheretrip.net.RxUtils;
import com.example.tolovepy.everywheretrip.bean.CollectionList;
import com.example.tolovepy.everywheretrip.ui.api.MyApi;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class CollectionModel extends BaseModel {

    //获取已收藏的数据
    public void getCollectionList(int page, final ResultCallBack<CollectionList> callBack){
        MyApi myApi = HttpUtils.getInstance().getApiserver(MyApi.mainUrl, MyApi.class);
        Observable<CollectionList> collectionList = myApi.setCollectionList(MyApi.param , page);
        collectionList.compose(RxUtils.<CollectionList>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<CollectionList>() {
                    @Override
                    public void onNext(CollectionList collectionList) {
                        callBack.onSuccess(collectionList);
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
