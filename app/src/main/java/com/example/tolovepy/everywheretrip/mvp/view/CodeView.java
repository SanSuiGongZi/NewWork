package com.example.tolovepy.everywheretrip.mvp.view;

import com.example.tolovepy.everywheretrip.base.BaseMvpView;

public interface CodeView extends BaseMvpView {

    void setCode(String code);
    void setErrors(String msg);

}
