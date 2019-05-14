package com.example.tolovepy.everywheretrip.ui.fragment;


import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.example.tolovepy.everywheretrip.R;
import com.example.tolovepy.everywheretrip.base.BaseFragment;
import com.example.tolovepy.everywheretrip.mvp.presenter.EmptyPre;
import com.example.tolovepy.everywheretrip.mvp.view.EmptyView;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentGuide extends BaseFragment<EmptyView, EmptyPre> implements EmptyView {


    @BindView(R.id.mImg_Frag)
    ImageView mImgFrag;
    private int mInt;

    public FragmentGuide() {
        // Required empty public constructor
    }

    public void setImg(int i) {
        mInt=i;
    }

    @Override
    protected EmptyPre initPresenter() {
        return new EmptyPre();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_guide;
    }

    @Override
    protected void initView() {
        if (mInt==0){
            mImgFrag.setImageResource(R.drawable.guide_01);
        }else if (mInt==1){
            mImgFrag.setImageResource(R.drawable.guide_02);
        }else {
            mImgFrag.setImageResource(R.drawable.guide_03);
        }
    }
}
