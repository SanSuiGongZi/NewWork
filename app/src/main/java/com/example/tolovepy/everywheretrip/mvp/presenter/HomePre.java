package com.example.tolovepy.everywheretrip.mvp.presenter;

import com.example.tolovepy.everywheretrip.base.BasePresenter;
import com.example.tolovepy.everywheretrip.bean.MainBean;
import com.example.tolovepy.everywheretrip.mvp.model.HomeModel;
import com.example.tolovepy.everywheretrip.mvp.view.HomeView;
import com.example.tolovepy.everywheretrip.net.ResultCallBack;

public class HomePre extends BasePresenter<HomeView> {

    private HomeModel model;

    @Override
    protected void initModel() {
        model = new HomeModel();
        mModels.add(model);
    }

    public void getPre(int page) {
        model.model(page, new ResultCallBack<MainBean>() {
            @Override
            public void onSuccess(MainBean bean) {
                if (bean != null) {
                    if (mMvpView != null) {
                        mMvpView.getData(bean);
                    }
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }

}
