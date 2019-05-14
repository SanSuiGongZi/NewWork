package com.example.tolovepy.everywheretrip.mvp.presenter;

import com.example.tolovepy.everywheretrip.base.BasePresenter;
import com.example.tolovepy.everywheretrip.mvp.model.CollectionModel;
import com.example.tolovepy.everywheretrip.mvp.view.CollectionView;
import com.example.tolovepy.everywheretrip.net.ResultCallBack;
import com.example.tolovepy.everywheretrip.bean.CollectionList;

public class CollectionPre extends BasePresenter<CollectionView> {

    private CollectionModel mModel;

    @Override
    protected void initModel() {
        mModel = new CollectionModel();
        mModels.add(mModel);
    }

    //收藏列表
    public void setLists(int page){
        mModel.getCollectionList(page, new ResultCallBack<CollectionList>() {
            @Override
            public void onSuccess(CollectionList bean) {
                if (bean!=null){
                    if (mMvpView!=null){
                        mMvpView.setData(bean);
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

