package com.example.tolovepy.everywheretrip.ui.fragment;


import android.support.v4.app.Fragment;

import com.example.tolovepy.everywheretrip.R;
import com.example.tolovepy.everywheretrip.base.BaseFragment;
import com.example.tolovepy.everywheretrip.mvp.presenter.EmptyPre;
import com.example.tolovepy.everywheretrip.mvp.view.EmptyView;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_State extends BaseFragment<EmptyView,EmptyPre> implements EmptyView{


    public Fragment_State() {
        // Required empty public constructor
    }

    @Override
    protected EmptyPre initPresenter() {
        return new EmptyPre();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment__state;
    }

}
