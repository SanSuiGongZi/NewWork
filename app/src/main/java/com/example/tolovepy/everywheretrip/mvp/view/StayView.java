package com.example.tolovepy.everywheretrip.mvp.view;

import com.example.tolovepy.everywheretrip.base.BaseMvpView;
import com.example.tolovepy.everywheretrip.bean.StayBeans;

public interface StayView extends BaseMvpView {

    void setList(StayBeans stayBean);
    void setToast(String str);
    void setError(String error);

}
