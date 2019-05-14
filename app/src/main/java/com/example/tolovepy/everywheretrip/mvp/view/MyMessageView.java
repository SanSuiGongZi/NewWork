package com.example.tolovepy.everywheretrip.mvp.view;

import com.example.tolovepy.everywheretrip.base.BaseMvpView;
import com.example.tolovepy.everywheretrip.bean.MessageBean;

public interface MyMessageView extends BaseMvpView {

    void setMessage(MessageBean message);
    void setError(String msg);

}
