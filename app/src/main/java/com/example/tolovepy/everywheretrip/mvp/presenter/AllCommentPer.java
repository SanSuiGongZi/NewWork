package com.example.tolovepy.everywheretrip.mvp.presenter;

import com.example.tolovepy.everywheretrip.base.BasePresenter;
import com.example.tolovepy.everywheretrip.bean.AllComment;
import com.example.tolovepy.everywheretrip.mvp.model.AllCommentModel;
import com.example.tolovepy.everywheretrip.mvp.view.AllCommentView;
import com.example.tolovepy.everywheretrip.net.ResultCallBack;

public class AllCommentPer extends BasePresenter<AllCommentView> {

    private AllCommentModel mModel;

    @Override
    protected void initModel() {
        mModel = new AllCommentModel();
        mModels.add(mModel);
    }

    //构建桥梁
    public void getAllData(int id, int page) {
        mModel.setAll(id, page, new ResultCallBack<AllComment>() {
            @Override
            public void onSuccess(AllComment bean) {
                if (bean!=null){
                    if (mMvpView!=null){
                        mMvpView.setAllData(bean);
                    }
                }
            }

            @Override
            public void onFail(String msg) {
                if (mMvpView!=null){
                    mMvpView.setAllError(msg);
                }
            }
        });
    }

}
