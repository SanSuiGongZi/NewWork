package com.example.tolovepy.everywheretrip.mvp.presenter;

import com.example.tolovepy.everywheretrip.base.BasePresenter;
import com.example.tolovepy.everywheretrip.bean.MessageBean;
import com.example.tolovepy.everywheretrip.bean.OutData;
import com.example.tolovepy.everywheretrip.mvp.model.MyMessageModel;
import com.example.tolovepy.everywheretrip.mvp.view.MyMessageView;
import com.example.tolovepy.everywheretrip.net.ResultCallBack;
import com.example.tolovepy.everywheretrip.util.ToastUtil;

public class MyMessagePre extends BasePresenter<MyMessageView> {

    private MyMessageModel mModel;

    @Override
    protected void initModel() {
        mModel = new MyMessageModel();
        mModels.add(mModel);
    }

    public void outData() {
        mModel.outMessage(new ResultCallBack<OutData>() {
            @Override
            public void onSuccess(OutData bean) {
                ToastUtil.showShort("修改成功,请等待");
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }

    public void newData() {
        mModel.newMessage(new ResultCallBack<MessageBean>() {
            @Override
            public void onSuccess(MessageBean bean) {
                if (bean != null) {
                    if (mMvpView != null) {
                        mMvpView.setMessage(bean);
                    }
                }
            }

            @Override
            public void onFail(String msg) {
                mMvpView.setError(msg);
            }
        });
    }

}
