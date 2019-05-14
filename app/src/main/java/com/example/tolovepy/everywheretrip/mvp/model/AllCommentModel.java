package com.example.tolovepy.everywheretrip.mvp.model;

import com.example.tolovepy.everywheretrip.base.BaseModel;
import com.example.tolovepy.everywheretrip.bean.AllComment;
import com.example.tolovepy.everywheretrip.net.BaseObserver;
import com.example.tolovepy.everywheretrip.net.HttpUtils;
import com.example.tolovepy.everywheretrip.net.ResultCallBack;
import com.example.tolovepy.everywheretrip.net.RxUtils;
import com.example.tolovepy.everywheretrip.ui.api.MyApi;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class AllCommentModel extends BaseModel {

    //获取全部评论
    public void setAll(int id, int page, final ResultCallBack<AllComment> callBack) {
        MyApi myApi = HttpUtils.getInstance().getApiserver(MyApi.mainUrl, MyApi.class);
        Observable<AllComment> allComment = myApi.allComment(MyApi.param, id, page);
        allComment.compose(RxUtils.<AllComment>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<AllComment>() {
                    @Override
                    public void onNext(AllComment allComment) {
                        callBack.onSuccess(allComment);
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
