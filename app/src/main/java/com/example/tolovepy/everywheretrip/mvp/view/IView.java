package com.example.tolovepy.everywheretrip.mvp.view;

import com.example.tolovepy.everywheretrip.base.BaseMvpView;
import com.example.tolovepy.everywheretrip.bean.BalanceBean;
import com.example.tolovepy.everywheretrip.bean.MessageBean;

public interface IView extends BaseMvpView {

    void setBalance(BalanceBean balance);
    void setError(String msg);
    void setMessage(MessageBean message);
}
