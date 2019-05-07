package com.example.tolovepy.everywheretrip.mvp.view;

import android.app.Activity;

import com.example.tolovepy.everywheretrip.base.BaseMvpView;

public interface MessageView extends BaseMvpView {

    String getPhone();
    Activity getAct();
    void goMainActivity();
    void toastShort(String string);
    void setCode(String code);
}
