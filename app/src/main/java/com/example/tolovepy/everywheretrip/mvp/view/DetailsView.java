package com.example.tolovepy.everywheretrip.mvp.view;

import com.example.tolovepy.everywheretrip.base.BaseMvpView;
import com.example.tolovepy.everywheretrip.bean.MainDetailsBean;

public interface DetailsView extends BaseMvpView {

    void setBeans(MainDetailsBean bean);
    void setError(String msg);
    void setToast(String string);
}
