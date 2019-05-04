package com.example.tolovepy.everywheretrip.ui.fragment;


import android.Manifest;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tolovepy.everywheretrip.R;
import com.example.tolovepy.everywheretrip.base.BaseFragment;
import com.example.tolovepy.everywheretrip.mvp.presenter.FragLoginPre;
import com.example.tolovepy.everywheretrip.mvp.view.FragmentLoginView;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_login extends BaseFragment<FragmentLoginView, FragLoginPre> implements FragmentLoginView {

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

    public Fragment_login() {
        // Required empty public constructor
    }

    @Override
    protected FragLoginPre initPresenter() {
        return new FragLoginPre();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initView() {
        initPer();
        initI();

    }

    private void initI() {
        mEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String string = s.toString();

                final String regex = "1[35789][0-9]{9}";
                if (string.matches(regex)) {
                    mBtn.setEnabled(true);
                    mBtn.setBackground(getResources().getDrawable(R.mipmap.button_highlight));

                    mBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (onClick != null) {
                                onClick.onClickLister(true);
                            }
                        }
                    });

                } else {
                    mBtn.setEnabled(false);
                    mBtn.setBackground(getResources().getDrawable(R.mipmap.button_unavailable));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @OnClick({R.id.imgQQ, R.id.imgWeChat, R.id.imgWB, R.id.btn_Verification})
    public void okClick(View view) {
        switch (view.getId()) {
            case R.id.imgQQ:
                login(SHARE_MEDIA.QQ);
                break;
            case R.id.imgWeChat:
                login(SHARE_MEDIA.WEIXIN);
                break;
            case R.id.imgWB:
                login(SHARE_MEDIA.SINA);
                break;
        }
    }


    //权限
    private void initPer() {
        String[] per = {Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

        ActivityCompat.requestPermissions(getActivity(), per, 100);
    }


    private void login(SHARE_MEDIA type) {
        UMShareAPI umShareAPI = UMShareAPI.get(getActivity());
        umShareAPI.getPlatformInfo(getActivity(), type, umAuthListener);
    }

    UMAuthListener umAuthListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            for (Map.Entry<String, String> entry : data.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                Log.d(TAG, "key: " + key + ",value:" + value);
            }
            Toast.makeText(getActivity(), "成功了", Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {

            Toast.makeText(getActivity(), "失败：" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(getActivity(), "取消了", Toast.LENGTH_LONG).show();
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(getActivity()).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    public onClick onClick;

    public void setOnClick(Fragment_login.onClick onClick) {
        this.onClick = onClick;
    }

    public interface onClick {
        void onClickLister(boolean mBoolean);
    }

}
