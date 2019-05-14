package com.example.tolovepy.everywheretrip.mvp.view;

import com.example.tolovepy.everywheretrip.base.BaseMvpView;
import com.example.tolovepy.everywheretrip.bean.CollectionList;

public interface CollectionView extends BaseMvpView {

    void setData(CollectionList collectionList);
    void setError(String error);

}
