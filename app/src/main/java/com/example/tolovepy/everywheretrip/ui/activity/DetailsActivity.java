package com.example.tolovepy.everywheretrip.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.tolovepy.everywheretrip.R;
import com.example.tolovepy.everywheretrip.base.BaseActivity;
import com.example.tolovepy.everywheretrip.bean.MainDetailsBean;
import com.example.tolovepy.everywheretrip.mvp.presenter.DetailsPre;
import com.example.tolovepy.everywheretrip.mvp.view.DetailsView;
import com.example.tolovepy.everywheretrip.util.ToastUtil;
import com.jaeger.library.StatusBarUtil;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class DetailsActivity extends BaseActivity<DetailsView, DetailsPre> implements DetailsView {

    @BindView(R.id.mIV_title)
    ImageView mIVTitle;
    @BindView(R.id.mIV_photo)
    ImageView mIVPhoto;
    @BindView(R.id.mTv_name_de)
    TextView mTvNameDe;
    @BindView(R.id.mTv_profession)
    TextView mTvProfession;
    @BindView(R.id.mTv_area_name)
    TextView mTvAreaName;
    @BindView(R.id.mTv_text)
    TextView mTvText;
    @BindView(R.id.mTv_evaluate)
    TextView mTvEvaluate;
    @BindView(R.id.mBtn_share)
    Button mBtnShare;
    @BindView(R.id.mBtn_collect)
    Button mBtnCollect;
    @BindView(R.id.mBtn_circuit)
    Button mBtnCircuit;
    @BindView(R.id.mBtn_price)
    Button mBtnPrice;
    @BindView(R.id.mTv_area_de)
    TextView mTvAreaDe;
    @BindView(R.id.mTv_title_de)
    TextView mTvTitleDe;
    @BindView(R.id.mTv_str_de)
    TextView mTvStrDe;
    @BindView(R.id.mIv_de)
    ImageView mIvDe;
    @BindView(R.id.mIV_photo_comment)
    ImageView mIVPhotoComment;
    @BindView(R.id.mTv_name_comment)
    TextView mTvNameComment;
    @BindView(R.id.mTv_profession_comment)
    TextView mTvProfessionComment;
    @BindView(R.id.mTv_str_comment)
    TextView mTvStrComment;
    @BindView(R.id.mIV_photo_comment02)
    ImageView mIVPhotoComment02;
    @BindView(R.id.mTv_name_comment02)
    TextView mTvNameComment02;
    @BindView(R.id.mTv_profession_comment02)
    TextView mTvProfessionComment02;
    @BindView(R.id.mTv_str_comment02)
    TextView mTvStrComment02;
    @BindView(R.id.mIV_photo_comment03)
    ImageView mIVPhotoComment03;
    @BindView(R.id.mTv_name_comment03)
    TextView mTvNameComment03;
    @BindView(R.id.mTv_profession_comment03)
    TextView mTvProfessionComment03;
    @BindView(R.id.mTv_str_comment03)
    TextView mTvStrComment03;
    @BindView(R.id.mBtn_collects)
    Button mBtnCollects;
    private int mId;
    private boolean mMIsCollected;
    private String mUrl;

    @Override
    protected DetailsPre initPresenter() {
        return new DetailsPre();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_details;
    }

    @Override
    protected void initView() {
        StatusBarUtil.setLightMode(this);
        Intent intent = getIntent();
        mId = intent.getIntExtra("id", 0);
        showLoading();
        mPresenter.getMyData(mId);
    }

    @OnClick({R.id.mTv_evaluate, R.id.mBtn_share, R.id.mBtn_collect, R.id.mBtn_collects, R.id.mBtn_circuit, R.id.mBtn_price, R.id.mIv_de})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mTv_evaluate:
                //查看全部评论
                Intent intent = new Intent(DetailsActivity.this, CommentActivity.class);
                intent.putExtra("id", mId);
                startActivity(intent);
                break;
            case R.id.mBtn_share:
                //分享
                shareBorad();
                break;
            case R.id.mBtn_collect:
                //收藏
                mPresenter.getOk(mId);
                mPresenter.getMyData(mId);
                break;
            case R.id.mBtn_collects:
                //取消收藏
                mPresenter.getNo(mId);
                mPresenter.getMyData(mId);
                break;
            case R.id.mBtn_circuit:
                //开始线路
                break;
            case R.id.mBtn_price:
                //价格
                break;
            case R.id.mIv_de:
                //返回
                finish();
                break;
        }
    }


    /**
     * 带面板的分享
     */
    private void shareBorad() {

        UMImage thumb = new UMImage(this, mUrl);
        //
        thumb.compressStyle = UMImage.CompressStyle.SCALE;//大小压缩，默认为大小压缩，
        new ShareAction(DetailsActivity.this).withText(mTvStrDe.getText().toString().trim())
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
            Toast.makeText(DetailsActivity.this, "成功了", Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(DetailsActivity.this, "失败" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(DetailsActivity.this, "取消了", Toast.LENGTH_LONG).show();

        }
    };

    @Override
    public void setBeans(MainDetailsBean bean) {

        MainDetailsBean.ResultBean.RouteBean route = bean.getResult().getRoute();
        mUrl = route.getCardURL();
        //是否收藏
        mMIsCollected = route.isIsCollected();
        if (mMIsCollected) {
            mBtnCollects.setVisibility(GridView.VISIBLE);
            mBtnCollect.setVisibility(GridView.GONE);
        } else {
            mBtnCollect.setVisibility(GridView.VISIBLE);
            mBtnCollects.setVisibility(GridView.GONE);
        }

        mTvAreaDe.setText(route.getCity());
        mTvTitleDe.setText(route.getTitle());
        mTvStrDe.setText(route.getIntro());
        mBtnPrice.setText(getResources().getString(R.string.cost) + route.getPrice());
        RequestOptions options = RequestOptions.placeholderOf(R.mipmap.ee);
        Glide.with(this).load(mUrl).apply(options).into(mIVTitle);

        MainDetailsBean.ResultBean.BanmiBean banmi = bean.getResult().getBanmi();

        RequestOptions optionRadio = RequestOptions.circleCropTransform().placeholderOf(R.mipmap.ee);
        Glide.with(this).load(banmi.getPhoto()).apply(optionRadio).into(mIVPhoto);
        mTvNameDe.setText(banmi.getName());
        mTvProfession.setText(banmi.getOccupation());
        mTvAreaName.setText(banmi.getLocation());
        mTvText.setText(banmi.getIntroduction());

        List<MainDetailsBean.ResultBean.ReviewsBean> list = bean.getResult().getReviews();

        MainDetailsBean.ResultBean.ReviewsBean reviewsBean = list.get(0);
        Glide.with(this).load(reviewsBean.getUserPhoto()).apply(optionRadio).into(mIVPhotoComment);
        mTvNameComment.setText(reviewsBean.getUserName());
        mTvProfessionComment.setText(reviewsBean.getCreatedAt());
        mTvStrComment.setText(reviewsBean.getContent());

        MainDetailsBean.ResultBean.ReviewsBean reviewsBean01 = list.get(1);
        Glide.with(this).load(reviewsBean01.getUserPhoto()).apply(optionRadio).into(mIVPhotoComment02);
        mTvNameComment02.setText(reviewsBean01.getUserName());
        mTvProfessionComment02.setText(reviewsBean01.getCreatedAt());
        mTvStrComment02.setText(reviewsBean01.getContent());

        MainDetailsBean.ResultBean.ReviewsBean reviewsBean02 = list.get(2);
        Glide.with(this).load(reviewsBean02.getUserPhoto()).apply(optionRadio).into(mIVPhotoComment03);
        mTvNameComment03.setText(reviewsBean02.getUserName());
        mTvProfessionComment03.setText(reviewsBean02.getCreatedAt());
        mTvStrComment03.setText(reviewsBean02.getContent());
        hideLoading();
    }

    @Override
    public void setError(String msg) {

        ToastUtil.showShort(msg);
    }

    @Override
    public void setToast(String string) {

        ToastUtil.showShort(string);
    }

}
