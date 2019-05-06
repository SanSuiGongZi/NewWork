package com.example.tolovepy.everywheretrip.ui.fragment;


import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tolovepy.everywheretrip.R;
import com.example.tolovepy.everywheretrip.base.BaseApp;
import com.example.tolovepy.everywheretrip.base.BaseFragment;
import com.example.tolovepy.everywheretrip.mvp.presenter.CodePre;
import com.example.tolovepy.everywheretrip.mvp.view.CodeView;
import com.example.tolovepy.everywheretrip.ui.activity.IdentifyingCodeView;
import com.example.tolovepy.everywheretrip.ui.activity.MainActivity;

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
    private int time = 10;
    private Handler mHandler = new Handler();

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
        mPresenter.getCode();
        mHandler.post(mRunnable);
    }

    //倒计时重新获取验证码
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            time--;
            if (time>0){
                mHandler.postDelayed(mRunnable,1000);
                tvSendAgain.setText("重新发送("+time+")");
            }else {
                tvSendAgain.setText(R.string.send_newAgain);
                tvSendAgain.setTextColor(getResources().getColor(R.color.c_fa6a13));
                tvSendAgain.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        time=10;
                        tvSendAgain.setTextColor(getResources().getColor(R.color.c_999));
                        initView();
                    }
                });
            }
        }
    };

    @Override
    public void setCode(String code) {
        tvWait.setText("验证码:" + code);
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
            tvWait.setText(BaseApp.getRes().getString(R.string.wait_please));
            showLoading();
            startActivity(new Intent(getActivity(), MainActivity.class));
            hideLoading();
        }
    }
}
