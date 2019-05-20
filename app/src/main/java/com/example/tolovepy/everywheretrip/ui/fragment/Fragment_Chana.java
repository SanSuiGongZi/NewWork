package com.example.tolovepy.everywheretrip.ui.fragment;


import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;

import com.example.tolovepy.everywheretrip.R;
import com.example.tolovepy.everywheretrip.base.BaseFragment;
import com.example.tolovepy.everywheretrip.mvp.presenter.EmptyPre;
import com.example.tolovepy.everywheretrip.mvp.view.EmptyView;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class Fragment_Chana extends BaseFragment<EmptyView,EmptyPre> implements EmptyView {

    private String mCity;

    @SuppressLint("ValidFragment")
    public Fragment_Chana(String city) {
        // Required empty public constructor
        this.mCity=city;
    }

    @Override
    protected EmptyPre initPresenter() {
        return new EmptyPre();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_chana;
    }

}
