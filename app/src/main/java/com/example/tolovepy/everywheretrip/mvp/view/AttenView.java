package com.example.tolovepy.everywheretrip.mvp.view;

import com.example.tolovepy.everywheretrip.base.BaseMvpView;
import com.example.tolovepy.everywheretrip.bean.AttenList;

public interface AttenView extends BaseMvpView {

    void setAttenList(AttenList bean);
    void setToast(String str);
    void setError(String error);
}
