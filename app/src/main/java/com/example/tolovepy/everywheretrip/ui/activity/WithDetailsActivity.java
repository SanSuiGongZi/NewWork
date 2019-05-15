package com.example.tolovepy.everywheretrip.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.tolovepy.everywheretrip.R;
import com.example.tolovepy.everywheretrip.base.BaseActivity;
import com.example.tolovepy.everywheretrip.base.BaseFragment;
import com.example.tolovepy.everywheretrip.bean.WithPath;
import com.example.tolovepy.everywheretrip.bean.WithState;
import com.example.tolovepy.everywheretrip.mvp.presenter.WithPre;
import com.example.tolovepy.everywheretrip.mvp.view.WithView;
import com.example.tolovepy.everywheretrip.ui.fragment.FragmentPath;
import com.example.tolovepy.everywheretrip.ui.fragment.Fragment_State;
import com.example.tolovepy.everywheretrip.util.ToastUtil;
import com.jaeger.library.StatusBarUtil;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class WithDetailsActivity extends BaseActivity<WithView, WithPre> implements WithView {

    private static final String TAG = "WithDetailsActivity";
    @BindView(R.id.goBack)
    ImageView goBack;
    @BindView(R.id.share)
    ImageButton share;
    @BindView(R.id.mTool)
    Toolbar mTool;
    @BindView(R.id.mIV_title)
    ImageView mIVTitle;
    @BindView(R.id.mTv_title)
    TextView mTvTitle;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.mTv_region)
    TextView mTvRegion;
    @BindView(R.id.img_likes)
    ImageView imgLikes;
    @BindView(R.id.mTv_status)
    TextView mTvStatus;
    @BindView(R.id.isFollow)
    ImageView isFollow;
    @BindView(R.id.mTv_intro)
    TextView mTvIntro;
    @BindView(R.id.mTv_str)
    TextView mTvStr;
    @BindView(R.id.mTab)
    TabLayout mTab;
    @BindView(R.id.mVp)
    AutofitViewPager mVp;
    @BindView(R.id.mTV_isCollection)
    TextView mTVIsCollection;
    private int mId;
    private int page = 1;
    private ArrayList<BaseFragment> mList;
    private String mPhoto;
    private RequestOptions mOptions;

    @Override
    protected WithPre initPresenter() {
        return new WithPre();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_with_details;
    }

    @Override
    protected void initView() {
        StatusBarUtil.setLightMode(this);
        mTool.setTitle("");
        setSupportActionBar(mTool);

        RoundedCorners corners = new RoundedCorners(20);
        mOptions = RequestOptions.bitmapTransform(corners);

        mId = getIntent().getIntExtra("idssss", 0);
        Log.e(TAG, "initView: "+mId );
        showLoading();
        mPresenter.setTit(page, mId);
        mPresenter.setStr(page, mId);

        mTab.addTab(mTab.newTab().setText("动态"));
        mTab.addTab(mTab.newTab().setText("线路"));

        mList = new ArrayList<>();
        FragmentPath path = new FragmentPath();
        Fragment_State state = new Fragment_State();
        path.setban(mId);
        state.setban(mId);

        mList.add(state);
        mList.add(path);
        FragmentManager fm = getSupportFragmentManager();
        mVp.setOffscreenPageLimit(2);
        mVp.setAdapter(new FragmentPagerAdapter(fm) {
            @Override
            public Fragment getItem(int i) {
                return mList.get(i);
            }

            @Override
            public int getCount() {
                return mList.size();
            }
        });
        mVp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTab));
        mTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mVp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    public void getDataTitle(WithState withState) {
        hideLoading();
        WithState.ResultBean.BanmiBean banmi = withState.getResult().getBanmi();
        mPhoto = banmi.getPhoto();

        Glide.with(this).load(mPhoto).apply(mOptions).into(mIVTitle);
        mTvTitle.setText(banmi.getName());
        mTvStr.setText(banmi.getFollowing() + "人关注");
        mTvRegion.setText(banmi.getLocation());
        mTvStatus.setText(banmi.getOccupation());
        mTvIntro.setText(banmi.getIntroduction());

        boolean followed = banmi.isIsFollowed();
        if (followed) {
            Glide.with(this).load(R.mipmap.follow).into(isFollow);
            mTVIsCollection.setText(getResources().getString(R.string.OkAttention));

        } else {
            Glide.with(this).load(R.mipmap.follow_unselected).into(isFollow);
            mTVIsCollection.setText(getResources().getString(R.string.Attention));
        }

    }

    @OnClick({R.id.goBack,R.id.share})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.goBack:
                finish();
                break;
            case R.id.share:
                shareBorad();
                break;
        }
    }


    /**
     * 带面板的分享
     */
    private void shareBorad() {

        UMImage thumb = new UMImage(this,mPhoto);
        //
        thumb.compressStyle = UMImage.CompressStyle.SCALE;//大小压缩，默认为大小压缩，
        new ShareAction(WithDetailsActivity.this).withText(mTvTitle.getText().toString().trim())
                .withMedia(thumb).
                setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN)
                .setCallback(umShareListener).open();
    }

    private UMShareListener umShareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {
        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(WithDetailsActivity.this, "成功了", Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(WithDetailsActivity.this, "失败" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(WithDetailsActivity.this, "取消了", Toast.LENGTH_LONG).show();

        }
    };

    @Override
    public void getDataStr(WithPath withPath) {

    }

    @Override
    public void getError(String error) {
        ToastUtil.showShort(error);
    }

}
