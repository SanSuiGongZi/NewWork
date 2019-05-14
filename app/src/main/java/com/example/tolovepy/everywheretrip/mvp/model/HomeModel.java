package com.example.tolovepy.everywheretrip.mvp.model;

import android.text.TextUtils;

import com.example.tolovepy.everywheretrip.base.BaseModel;
import com.example.tolovepy.everywheretrip.bean.MainBean;
import com.example.tolovepy.everywheretrip.net.BaseObserver;
import com.example.tolovepy.everywheretrip.net.HttpUtils;
import com.example.tolovepy.everywheretrip.net.ResultCallBack;
import com.example.tolovepy.everywheretrip.net.RxUtils;
import com.example.tolovepy.everywheretrip.ui.api.MyApi;
import com.example.tolovepy.everywheretrip.util.ToastUtil;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class HomeModel extends BaseModel {

    public void model(int page, final ResultCallBack<MainBean> callBack) {
        String param = MyApi.param;
        if (!TextUtils.isEmpty(param)) {
            MyApi myApi = HttpUtils.getInstance().getApiserver(MyApi.mainUrl, MyApi.class);
            Observable<MainBean> apiMain = myApi.getMain(MyApi.param, page);
            apiMain.compose(RxUtils.<MainBean>rxObserableSchedulerHelper())
                    .subscribe(new BaseObserver<MainBean>() {
                        @Override
                        public void onNext(MainBean mainBean) {
                            callBack.onSuccess(mainBean);
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
            ToastUtil.showShort("暂无数据");
        }
    }

}
