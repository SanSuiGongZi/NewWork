package com.example.tolovepy.everywheretrip.mvp.view;

import com.example.tolovepy.everywheretrip.base.BaseMvpView;
import com.example.tolovepy.everywheretrip.bean.WithPath;
import com.example.tolovepy.everywheretrip.bean.WithState;

public interface WithView extends BaseMvpView {

    void getDataTitle(WithState withState);
    void getDataStr(WithPath withPath);
    void getError(String error);

}
