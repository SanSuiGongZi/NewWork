package com.example.tolovepy.everywheretrip.mvp.presenter;

import com.example.tolovepy.everywheretrip.base.BasePresenter;
import com.example.tolovepy.everywheretrip.bean.StayBean;
import com.example.tolovepy.everywheretrip.mvp.model.StayModel;
import com.example.tolovepy.everywheretrip.mvp.view.StayView;
import com.example.tolovepy.everywheretrip.net.ResultCallBack;
import com.example.tolovepy.everywheretrip.util.ToastUtil;

public class StayPre extends BasePresenter<StayView> {

    private StayModel stayModel;

    @Override
    protected void initModel() {
        stayModel = new StayModel();
        mModels.add(stayModel);
    }

    public void stayData(int page) {
        stayModel.getModel(page, new ResultCallBack<StayBean>() {
            @Override
            public void onSuccess(StayBean bean) {
                if (bean!=null){
                    if (mMvpView!=null){
                        mMvpView.setList(bean);
                    }
                }
            }

            @Override
            public void onFail(String msg) {
                ToastUtil.showShort(msg);
            }
        });
    }

}
