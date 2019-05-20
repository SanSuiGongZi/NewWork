package com.example.tolovepy.everywheretrip.mvp.model;

import com.example.tolovepy.everywheretrip.base.BaseModel;
import com.example.tolovepy.everywheretrip.bean.MapCityBean;
import com.example.tolovepy.everywheretrip.bean.MapTabBean;
import com.example.tolovepy.everywheretrip.net.BaseObserver;
import com.example.tolovepy.everywheretrip.net.HttpUtils;
import com.example.tolovepy.everywheretrip.net.ResultCallBack;
import com.example.tolovepy.everywheretrip.net.RxUtils;
import com.example.tolovepy.everywheretrip.ui.api.MyApi;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class MapModel extends BaseModel {

    //城市列表
    public void setCity(final ResultCallBack<MapCityBean> callBack){
        MyApi myApi = HttpUtils.getInstance().getApiserver(MyApi.mainUrl, MyApi.class);
        Observable<MapCityBean> setCity = myApi.setCity(MyApi.param);
        setCity.compose(RxUtils.<MapCityBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<MapCityBean>() {
                    @Override
                    public void onNext(MapCityBean mapCityBean) {
                        callBack.onSuccess(mapCityBean);
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

    //分类景点数据
    public void setTab(String tagID, int cityID, final ResultCallBack<MapTabBean> callBack){
        MyApi myApi = HttpUtils.getInstance().getApiserver(MyApi.mainUrl, MyApi.class);
        Observable<MapTabBean> mapTab = myApi.setMapTab(MyApi.param, tagID, cityID);
        mapTab.compose(RxUtils.<MapTabBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<MapTabBean>() {
                    @Override
                    public void onNext(MapTabBean mapTabBean) {
                        callBack.onSuccess(mapTabBean);
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


    //景点数据
    public void setOutTab(int cityID, final ResultCallBack<MapTabBean> callBack){
        MyApi myApi = HttpUtils.getInstance().getApiserver(MyApi.mainUrl, MyApi.class);
        Observable<MapTabBean> mapTab = myApi.setOutTab(MyApi.param, cityID);
        mapTab.compose(RxUtils.<MapTabBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<MapTabBean>() {
                    @Override
                    public void onNext(MapTabBean mapTabBean) {
                        callBack.onSuccess(mapTabBean);
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
