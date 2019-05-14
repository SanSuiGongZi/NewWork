package com.example.tolovepy.everywheretrip.ui.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tolovepy.everywheretrip.R;
import com.example.tolovepy.everywheretrip.base.BaseActivity;
import com.example.tolovepy.everywheretrip.base.BaseFragment;
import com.example.tolovepy.everywheretrip.base.Constants;
import com.example.tolovepy.everywheretrip.mvp.presenter.EmptyPre;
import com.example.tolovepy.everywheretrip.mvp.view.EmptyView;
import com.example.tolovepy.everywheretrip.ui.fragment.FragmentGuide;
import com.example.tolovepy.everywheretrip.util.SpUtil;
import com.example.tolovepy.everywheretrip.widget.PreviewIndicator;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class GuideActivity extends BaseActivity<EmptyView, EmptyPre> implements EmptyView {

    @BindView(R.id.mVp_guide)
    ViewPager mVpGuide;
    @BindView(R.id.mPir)
    PreviewIndicator mPir;
    @BindView(R.id.mBtn_guide)
    Button mBtnGuide;
    @BindView(R.id.mTv_guide)
    TextView mTvGuide;
    private ArrayList<BaseFragment> mList;

    @Override
    protected EmptyPre initPresenter() {
        return new EmptyPre();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_guide;
    }

    @Override
    protected void initView() {

        initTextColor();
        mList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            FragmentGuide guide = new FragmentGuide();
            guide.setImg(i);
            mList.add(guide);
        }
        //设置
        mPir.setNumbers(mList.size());
        FragmentManager fm = getSupportFragmentManager();
        mVpGuide.setAdapter(new FragmentPagerAdapter(fm) {
            @Override
            public Fragment getItem(int i) {
                return mList.get(i);
            }

            @Override
            public int getCount() {
                return mList.size();
            }
        });
        //监听viewPage页面的切换
        mVpGuide.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                mPir.setSelected(i);
                if (i == 0) {
                    mTvGuide.setText(R.string.guide01);
                    initTextColor();
                } else if (i == 1) {
                    mPir.setVisibility(View.VISIBLE);
                    mBtnGuide.setVisibility(View.INVISIBLE);
                    mTvGuide.setText(R.string.guide02);
                    initTextColor();
                } else {
                    mPir.setVisibility(View.INVISIBLE);
                    mBtnGuide.setVisibility(View.VISIBLE);
                    mTvGuide.setText(R.string.guide03);
                    initTextColor();
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });


    }

    //设置图文混排
    private void initTextColor() {

        SpannableStringBuilder stringBuilder = new SpannableStringBuilder(mTvGuide.getText());
        //设置前景色
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(getResources().getColor(R.color.c_FA6A13));
        stringBuilder.setSpan(colorSpan, (mTvGuide.length() - 4), mTvGuide.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        mTvGuide.setText(stringBuilder);

    }

    @OnClick(R.id.mBtn_guide)
    public void onViewClicked() {
        SpUtil.setParam(Constants.BOOLEAN, true);
        startActivity(new Intent(GuideActivity.this, LoginActivity.class));
        finish();
    }
}
