package com.example.tolovepy.everywheretrip.mvp.model;

import android.text.TextUtils;

import com.example.tolovepy.everywheretrip.base.BaseModel;
import com.example.tolovepy.everywheretrip.bean.BalanceBean;
import com.example.tolovepy.everywheretrip.bean.DemoBean;
import com.example.tolovepy.everywheretrip.bean.MessageBean;
import com.example.tolovepy.everywheretrip.bean.NewVersion;
import com.example.tolovepy.everywheretrip.net.BaseObserver;
import com.example.tolovepy.everywheretrip.net.HttpUtils;
import com.example.tolovepy.everywheretrip.net.ResultCallBack;
import com.example.tolovepy.everywheretrip.net.RxUtils;
import com.example.tolovepy.everywheretrip.ui.api.MyApi;
import com.example.tolovepy.everywheretrip.util.ToastUtil;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * @author xts
 * Created by asus on 2019/4/29.
 * 1.打log,交给日志拦截器
 * 2.无网络的情况下,没有提示
 * 3.加载数据需要loading界面
 */

public class Model extends BaseModel {


    public void getVerifyCode(final ResultCallBack<DemoBean> callBack) {

        MyApi myApi = HttpUtils.getInstance().getApiserver(MyApi.sBaseUrl, MyApi.class);
        Observable<DemoBean> code = myApi.getVerifyCode();
        code.compose(RxUtils.<DemoBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<DemoBean>() {
                    @Override
                    public void onNext(DemoBean demoBean) {
                        callBack.onSuccess(demoBean);
                    }

                    @Override
                    public void error(String msg) {

                    }

                    @Override
                    protected void subscribe(Disposable d) {
                        addDisposable(d);
                    }
                });

    }

    public void getBalance(final ResultCallBack<BalanceBean> callBack) {

        if (!TextUtils.isEmpty(MyApi.param)) {
            MyApi myApi = HttpUtils.getInstance().getApiserver(MyApi.mainUrl, MyApi.class);
            Observable<BalanceBean> apiBan = myApi.getBan(MyApi.param);
            apiBan.compose(RxUtils.<BalanceBean>rxObserableSchedulerHelper())
                    .subscribe(new BaseObserver<BalanceBean>() {
                        @Override
                        public void onNext(BalanceBean balanceBean) {
                            callBack.onSuccess(balanceBean);
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
        } else {
            ToastUtil.showShort("数据加载失败,请先绑定微博");
        }

    }

    //获取修改的数据
    public void newMessage(final ResultCallBack<MessageBean> callBack) {
        MyApi myApi = HttpUtils.getInstance().getApiserver(MyApi.mainUrl, MyApi.class);
        Observable<MessageBean> newDateInfo = myApi.newDateInfo(MyApi.param);
        newDateInfo.compose(RxUtils.<MessageBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<MessageBean>() {
                    @Override
                    public void onNext(MessageBean messageBean) {

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

    //获取是否版本数据
    public void getVersion(final ResultCallBack<NewVersion> callBack) {
        MyApi myApi = HttpUtils.getInstance().getApiserver(MyApi.mainUrl, MyApi.class);
        Observable<NewVersion> apiVersion = myApi.getVersion(MyApi.param);
        apiVersion.compose(RxUtils.<NewVersion>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<NewVersion>() {
                    @Override
                    public void onNext(NewVersion newVersion) {
                        callBack.onSuccess(newVersion);
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
