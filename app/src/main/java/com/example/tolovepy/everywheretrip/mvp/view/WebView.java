package com.example.tolovepy.everywheretrip.mvp.view;

import com.example.tolovepy.everywheretrip.base.BaseMvpView;
import com.example.tolovepy.everywheretrip.bean.WebBean;

public interface WebView extends BaseMvpView {

    void setWeb(WebBean web);
    void setError(String error);
}
