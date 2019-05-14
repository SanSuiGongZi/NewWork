package com.example.tolovepy.everywheretrip.mvp.view;

import com.example.tolovepy.everywheretrip.base.BaseMvpView;
import com.example.tolovepy.everywheretrip.bean.AllComment;

public interface AllCommentView extends BaseMvpView {

    void setAllData(AllComment allData);
    void setAllError(String error);

}
