package com.example.tolovepy.everywheretrip.mvp.view;

import com.example.tolovepy.everywheretrip.base.BaseMvpView;
import com.example.tolovepy.everywheretrip.bean.MapCityBean;
import com.example.tolovepy.everywheretrip.bean.MapTabBean;

public interface MyMapView extends BaseMvpView {

    void getCity(MapCityBean cityBean);
    void getTab(MapTabBean tabBean);
    void getOutTab(MapTabBean tabBean);
    void setError(String error);

}
