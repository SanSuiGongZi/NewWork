package com.example.tolovepy.everywheretrip.mvp.presenter;

import com.example.tolovepy.everywheretrip.base.BasePresenter;
import com.example.tolovepy.everywheretrip.bean.AttenBeanDelete;
import com.example.tolovepy.everywheretrip.bean.AttenBeanInsert;
import com.example.tolovepy.everywheretrip.bean.StayBeans;
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

    //伴米数据
    public void stayData(int page) {
        stayModel.getModel(page, new ResultCallBack<StayBeans>() {
            @Override
            public void onSuccess(StayBeans bean) {
                if (bean != null) {
                    if (mMvpView != null) {
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

    //关注
    public void getInsert(final int banmild) {
        stayModel.getPay(banmild, new ResultCallBack<AttenBeanInsert>() {
            @Override
            public void onSuccess(AttenBeanInsert bean) {
                if (bean != null) {
                    if (mMvpView != null) {
                        mMvpView.setToast(bean.getResult().getMessage());
                    }
                }
            }

            @Override
            public void onFail(String msg) {
                if (mMvpView != null) {
                    mMvpView.setError(msg);
                }
            }
        });
    }

    //取消关注
    public void getDelete(int banmild) {
        stayModel.getOff(banmild, new ResultCallBack<AttenBeanDelete>() {
            @Override
            public void onSuccess(AttenBeanDelete bean) {
                if (bean != null) {
                    if (mMvpView != null) {
                        mMvpView.setToast(bean.getResult().getMessage());
                    }
                }
            }

            @Override
            public void onFail(String msg) {
                if (mMvpView != null) {
                    mMvpView.setError(msg);
                }
            }
        });
    }

}
