package com.example.tolovepy.everywheretrip.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tolovepy.everywheretrip.R;
import com.example.tolovepy.everywheretrip.base.BaseApp;
import com.example.tolovepy.everywheretrip.base.BaseFragment;
import com.example.tolovepy.everywheretrip.base.Constants;
import com.example.tolovepy.everywheretrip.mvp.presenter.CodePre;
import com.example.tolovepy.everywheretrip.mvp.view.CodeView;
import com.example.tolovepy.everywheretrip.ui.activity.IdentifyingCodeView;
import com.example.tolovepy.everywheretrip.ui.activity.LoginActivity;
import com.example.tolovepy.everywheretrip.ui.activity.MainActivity;
import com.example.tolovepy.everywheretrip.util.SpUtil;
import com.example.tolovepy.everywheretrip.util.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentCodes extends BaseFragment<CodeView, CodePre> implements CodeView {

    private static final String TAG = "FragmentCodes";
    @BindView(R.id.tv_input_code)
    TextView tvInputCode;
    @BindView(R.id.icv)
    IdentifyingCodeView icv;
    @BindView(R.id.tv_wait)
    TextView tvWait;
    @BindView(R.id.img_start)
    ImageView imgStart;
    @BindView(R.id.tv_send_again)
    TextView tvSendAgain;
    private int mTime;
    private String codes;

    public static FragmentCodes getNewCode(String code) {
        FragmentCodes codes = new FragmentCodes();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.VERIFY_CODE, code);
        codes.setArguments(bundle);
        return codes;
    }


    public FragmentCodes() {
        // Required empty public constructor
    }

    @Override
    protected CodePre initPresenter() {
        return new CodePre();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_codes;
    }

    @Override
    protected void initView() {
        //获取传过来的验证码
        String string = getArguments().getString(Constants.VERIFY_CODE);
        setCode(string);
    }

    public void autoLogin(int time) {
        mTime = time;
        if (tvSendAgain != null) {
            if (time != 0) {
                String format = String.format(getResources().getString(R.string.send_newAgain) + "(%ss)", mTime);
                tvSendAgain.setText(format);
                tvSendAgain.setTextColor(getResources().getColor(R.color.c_999));
            } else {
                tvSendAgain.setText(getResources().getString(R.string.send_newAgain));
                tvSendAgain.setTextColor(getResources().getColor(R.color.c_FA6A13));
            }
        }
    }

    @Override
    public void setCode(String code) {
        if (!TextUtils.isEmpty(code) && tvWait != null) {
            tvWait.setText("验证码:" + code);
            codes = code;
            SpUtil.setParam(Constants.CODEMAIN, code);
        }
    }

    @Override
    public void setErrors(String msg) {
        Log.e(TAG, "setErrors: " + msg);
    }

    @OnClick({R.id.img_start, R.id.tv_send_again})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_start:
                pop();
                break;
            case R.id.tv_send_again:
                if (mTime == 0) {
                    mPresenter.getCode();
                    //重新发起倒计时
                    Fragment_login fragment = (Fragment_login) getActivity().getSupportFragmentManager().findFragmentByTag(LoginActivity.TAG);
                    //调用倒计时
                    fragment.countDown();

                }
                break;
        }
    }

    /**
     * 碎片手动弹栈
     */
    private void pop() {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        //弹栈
        manager.popBackStack();
    }

    @Override
    protected void initListener() {
        icv.setOnEditorActionListener(new IdentifyingCodeView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                return false;
            }

            @Override
            public void onTextChanged(String s) {
                autoLogin();
            }
        });
    }

    private void autoLogin() {
        if (icv.getTextContent().length() >= 4) {
            //自动登录
            icv.setBackgroundEnter(false);
            String content = icv.getTextContent();
            showLoading();
            if (content.equals(codes)) {
                tvWait.setText(BaseApp.getRes().getString(R.string.wait_please));

                String token = (String) SpUtil.getParam(Constants.TOKEN, "");
                if (!TextUtils.isEmpty(token)) {
                    new Thread() {
                        @Override
                        public void run() {
                            super.run();

                            try {
                                sleep(3000);
                                startActivity(new Intent(getActivity(), MainActivity.class));
                                getActivity().finish();
                                hideLoading();

                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }
                    }.start();
                } else {
                    ToastUtil.showShort("抱歉注册失败,无法进去App");
                    icv.clearAllText();
                    hideLoading();
                }
            } else {
                ToastUtil.showShort("验证码输入错误");
                icv.clearAllText();
                hideLoading();
            }
        }
    }
}
