package com.example.tolovepy.everywheretrip.mvp.presenter;

import com.example.tolovepy.everywheretrip.base.BasePresenter;
import com.example.tolovepy.everywheretrip.bean.MapCityBean;
import com.example.tolovepy.everywheretrip.bean.MapTabBean;
import com.example.tolovepy.everywheretrip.mvp.model.MapModel;
import com.example.tolovepy.everywheretrip.mvp.view.MyMapView;
import com.example.tolovepy.everywheretrip.net.ResultCallBack;

public class MapPre extends BasePresenter<MyMapView> {

    private MapModel mMapModel;

    @Override
    protected void initModel() {
        mMapModel = new MapModel();
        mModels.add(mMapModel);
    }

    //城市列表
    public void setCity(){
        mMapModel.setCity(new ResultCallBack<MapCityBean>() {
            @Override
            public void onSuccess(MapCityBean bean) {
                if (bean!=null){
                    if (mMvpView!=null){
                        mMvpView.getCity(bean);
                    }
                }
            }

            @Override
            public void onFail(String msg) {
                mMvpView.setError(msg);
            }
        });
    }

    //分类景点数据
    public void setTab(String tagID, int cityID){
        mMapModel.setTab(tagID, cityID, new ResultCallBack<MapTabBean>() {
            @Override
            public void onSuccess(MapTabBean bean) {
                if (bean!=null){
                    if (mMvpView!=null){
                        mMvpView.getTab(bean);
                    }
                }
            }

            @Override
            public void onFail(String msg) {
                mMvpView.setError(msg);
            }
        });
    }
    //景点数据
    public void setOutTab(int cityID){
        mMapModel.setOutTab(cityID, new ResultCallBack<MapTabBean>() {
            @Override
            public void onSuccess(MapTabBean bean) {
                if (bean!=null){
                    if (mMvpView!=null){
                        mMvpView.getOutTab(bean);
                    }
                }
            }

            @Override
            public void onFail(String msg) {
                mMvpView.setError(msg);
            }
        });
    }
}
