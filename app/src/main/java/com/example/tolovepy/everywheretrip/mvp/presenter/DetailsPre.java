package com.example.tolovepy.everywheretrip.mvp.presenter;

import com.example.tolovepy.everywheretrip.base.BasePresenter;
import com.example.tolovepy.everywheretrip.bean.MainDetailsBean;
import com.example.tolovepy.everywheretrip.mvp.model.DetailsModel;
import com.example.tolovepy.everywheretrip.mvp.view.DetailsView;
import com.example.tolovepy.everywheretrip.net.ResultCallBack;
import com.example.tolovepy.everywheretrip.bean.CollectionNo;
import com.example.tolovepy.everywheretrip.bean.CollectionOk;

public class DetailsPre extends BasePresenter<DetailsView> {

    private DetailsModel mDetailsModel;

    @Override
    protected void initModel() {
        mDetailsModel = new DetailsModel();
        mModels.add(mDetailsModel);
    }

    //主页数据
    public void getMyData(int id){
        mDetailsModel.getDatas(id, new ResultCallBack<MainDetailsBean>() {
            @Override
            public void onSuccess(MainDetailsBean bean) {
                if (bean!=null){
                    if (mMvpView!=null){
                        mMvpView.setBeans(bean);
                    }
                }
            }

            @Override
            public void onFail(String msg) {
                if (mMvpView!=null){
                    mMvpView.setError(msg);
                }
            }
        });
    }

    //收藏
    public void getOk(int id){
        mDetailsModel.getCollectionOk(id, new ResultCallBack<CollectionOk>() {
            @Override
            public void onSuccess(CollectionOk bean) {
                if (bean!=null){
                    if (mMvpView!=null){
                        mMvpView.setToast(bean.getDesc());
                    }
                }
            }

            @Override
            public void onFail(String msg) {
                mMvpView.setError(msg);
            }
        });
    }

    //取消收藏
    public void getNo(int id){
        mDetailsModel.getCollectionNo(id, new ResultCallBack<CollectionNo>() {
            @Override
            public void onSuccess(CollectionNo bean) {
                if (bean!=null){
                    if (mMvpView!=null){
                        mMvpView.setToast(bean.getDesc());
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
