package com.example.tolovepy.everywheretrip.mvp.model;

import android.util.Log;

import com.example.tolovepy.everywheretrip.base.BaseModel;
import com.example.tolovepy.everywheretrip.base.Constants;
import com.example.tolovepy.everywheretrip.bean.MessageBean;
import com.example.tolovepy.everywheretrip.bean.OutData;
import com.example.tolovepy.everywheretrip.net.BaseObserver;
import com.example.tolovepy.everywheretrip.net.HttpUtils;
import com.example.tolovepy.everywheretrip.net.ResultCallBack;
import com.example.tolovepy.everywheretrip.net.RxUtils;
import com.example.tolovepy.everywheretrip.ui.api.MyApi;
import com.example.tolovepy.everywheretrip.util.SpUtil;
import com.google.gson.Gson;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

public class MyMessageModel extends BaseModel {

    private static final String TAG = "MyMessageModel";

    //修改数据
    public void outMessage(final ResultCallBack<OutData> callBack) {

        String name = (String) SpUtil.getParam(Constants.USERNAME, "name");
        String desc = (String) SpUtil.getParam(Constants.DESC, "desc");
        String gender = (String) SpUtil.getParam(Constants.GENDER, "gender");
        String photo = (String) SpUtil.getParam(Constants.PHOTO, "photo");

        Log.e(TAG, "outMessage:Model "+name );

        MyApi myApi = HttpUtils.getInstance().getApiserver(MyApi.mainUrl, MyApi.class);
        Observable<ResponseBody> upDateInfo = myApi.upDateInfo(name,desc,gender,photo, MyApi.param);
        upDateInfo.compose(RxUtils.<ResponseBody>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<ResponseBody>() {
                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();
                            OutData data = new Gson().fromJson(string, OutData.class);
                            callBack.onSuccess(data);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void error(String msg) {
                        callBack.onFail(msg);
                    }

                    @Override
                    protected void subscribe(Disposable d) {
                        addDisposable(d);
                    }
                });
    }

    //获取修改的数据
    public void newMessage(final ResultCallBack<MessageBean> callBack) {
        MyApi myApi = HttpUtils.getInstance().getApiserver(MyApi.mainUrl, MyApi.class);
        Observable<MessageBean> newDateInfo = myApi.newDateInfo(MyApi.param);
        newDateInfo.compose(RxUtils.<MessageBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<MessageBean>() {
                    @Override
                    public void onNext(MessageBean messageBean) {

                        Log.e(TAG, "onNext: "+messageBean.getResult().getUserName() );

                        callBack.onSuccess(messageBean);
                    }

                    @Override
                    public void error(String msg) {
                        callBack.onFail(msg);
                    }

                    @Override
                    protected void subscribe(Disposable d) {
                        addDisposable(d);
                    }
                });
    }

}
