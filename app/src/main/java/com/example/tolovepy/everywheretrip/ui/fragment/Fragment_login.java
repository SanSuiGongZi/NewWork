package com.example.tolovepy.everywheretrip.ui.fragment;


import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tolovepy.everywheretrip.R;
import com.example.tolovepy.everywheretrip.base.BaseFragment;
import com.example.tolovepy.everywheretrip.mvp.presenter.MessagePre;
import com.example.tolovepy.everywheretrip.mvp.view.MessageView;
import com.example.tolovepy.everywheretrip.ui.activity.MainActivity;
import com.example.tolovepy.everywheretrip.ui.activity.WebViewActivity;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_login extends BaseFragment<MessageView, MessagePre> implements MessageView {

    private static final String TAG = "Fragment_login";
    @BindView(R.id.et_phone)
    EditText mEt;
    @BindView(R.id.btn_Verification)
    Button mBtn;
    @BindView(R.id.imgWeChat)
    ImageView mImg;
    @BindView(R.id.imgQQ)
    ImageButton imgQQ;
    @BindView(R.id.imgWB)
    ImageButton imgWB;
    @BindView(R.id.tv_agreement)
    TextView tvAgreement;
    private Fragment_Code code;

    public Fragment_login() {
        // Required empty public constructor
    }

    @Override
    protected MessagePre initPresenter() {
        return new MessagePre();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initView() {

        //SpannableString ss = new SpannableString(getResources().getString(R.string.agree_protocol));
        //通过设置不同的span达到不同的显示效果
         /*  what：对SpannableString进行润色的各种Span；
            start：需要润色文字段开始的下标,包含；
            end：需要润色文字段结束的下标,结束应用指定Span的位置，不包含；
            flags：Spannable.SPAN_EXCLUSIVE_EXCLUSIVE：前后都不包括（
            在标志位【start，end）前后添加文字，新添加的文字不会有任何设置的属性）
            Spannable.SPAN_EXCLUSIVE_INCLUSIVE ：前面不包括，后面包括。
            （在标志位【start，end）前添加文字，新添加的文字不会有任何设置的属性，后边的添加的文字会带有设置的what属性）
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE ：前面包括，后面不包括。
            （在标志位【start，end）后添加文字，新添加的文字不会有任何设置的属性，前边边的添加的文字会带有设置的what属性）
            Spannable.SPAN_INCLUSIVE_INCLUSIVE ：前后都包括。前后都不包括
            （在标志位【start，end）前后添加文字，新添加的文字会有设置的属性）
          */

            /* ss.setSpan(what,0,16, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            mTvProtocol.setText(ss);*/

        //17个字,0-16
         /*   SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(getResources().getString(R.string.agree_protocol));
            //SPAN_INCLUSIVE_EXCLUSIVE 1-16,插入的位置1-15 可以渲染,16就不会被渲染
            //SPAN_INCLUSIVE_INCLUSIVE 1-16,插入的位置1-16 都可以渲染
            //SPAN_EXCLUSIVE_INCLUSIVE 1-16,插入的位置2-16 都可以渲染
            spannableStringBuilder.setSpan(what,1,16, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            spannableStringBuilder.insert(2,"12");
            mTvProtocol.setText(spannableStringBuilder);*/

        SpannableStringBuilder stringBuilder = new SpannableStringBuilder(getResources().getString(R.string.login_okbangmi));
        //设置文字的点击事件
        ClickableSpan span = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                //跳转页面,webview展示协议
                //webView有很多坑,所以我们不直接用webView
                WebViewActivity.startAct(getActivity());
            }
        };
        stringBuilder.setSpan(span, 12, 16, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        //设置下划线
        UnderlineSpan underlineSpan = new UnderlineSpan();
        stringBuilder.setSpan(underlineSpan, 12, 16, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        //设置前景色
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(getResources().getColor(R.color.c_FA6A13));
        stringBuilder.setSpan(colorSpan, 12, 16, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        //设置这个ClickableSpan才能启动
        tvAgreement.setMovementMethod(LinkMovementMethod.getInstance());
        tvAgreement.setText(stringBuilder);
        initI();
    }

    private void initI() {
        mEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                switchBtnState(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void switchBtnState(CharSequence s) {
        if (!TextUtils.isEmpty(s)) {
            mBtn.setBackgroundResource(R.drawable.shape_ok);
        } else {
            mBtn.setBackgroundResource(R.drawable.shape_no);
        }
    }

    @OnClick({R.id.imgQQ, R.id.imgWeChat, R.id.imgWB, R.id.btn_Verification})
    public void okClick(View view) {
        switch (view.getId()) {
            case R.id.imgQQ:
                mPresenter.oauthLogin(SHARE_MEDIA.QQ);
                break;
            case R.id.imgWeChat:
                mPresenter.oauthLogin(SHARE_MEDIA.WEIXIN);
                break;
            case R.id.imgWB:
                mPresenter.oauthLogin(SHARE_MEDIA.SINA);
                break;
            case R.id.btn_Verification:
                AddVerifyFragment();
                break;
        }
    }

    private void AddVerifyFragment() {
        if (TextUtils.isEmpty(getPhone())) {
            return;
        }
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        //添加回退栈
        code = new Fragment_Code();
        FragmentCodes codes = new FragmentCodes();
        transaction.addToBackStack(null);
        transaction.add(R.id.mfl, codes).commit();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(getActivity()).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public String getPhone() {
        return mEt.getText().toString().trim();
    }

    @Override
    public Activity getAct() {
        return getActivity();
    }

    @Override
    public void goMainActivity() {
        MainActivity.startAct(getContext());
    }

}
