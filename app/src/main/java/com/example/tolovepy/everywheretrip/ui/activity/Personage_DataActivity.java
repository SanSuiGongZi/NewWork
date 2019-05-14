package com.example.tolovepy.everywheretrip.ui.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tolovepy.everywheretrip.R;
import com.example.tolovepy.everywheretrip.base.BaseActivity;
import com.example.tolovepy.everywheretrip.base.Constants;
import com.example.tolovepy.everywheretrip.bean.MessageBean;
import com.example.tolovepy.everywheretrip.mvp.presenter.MyMessagePre;
import com.example.tolovepy.everywheretrip.mvp.view.MyMessageView;
import com.example.tolovepy.everywheretrip.util.SpUtil;
import com.jaeger.library.StatusBarUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class Personage_DataActivity extends BaseActivity<MyMessageView, MyMessagePre> implements MyMessageView {

    private static final String TAG = "Personage_DataActivity";
    @BindView(R.id.img_replace)
    ImageView imgReplace;
    @BindView(R.id.mTv_pers)
    TextView mTvPers;
    @BindView(R.id.mTv_outs)
    TextView mTvOuts;
    @BindView(R.id.mTool_data)
    Toolbar mToolData;
    @BindView(R.id.mEt_per)
    EditText mEtPer;
    @BindView(R.id.mTv_perSize)
    TextView mTvPerSize;
    private String mStringExtra;

    @Override
    protected MyMessagePre initPresenter() {
        return new MyMessagePre();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_personage__data;
    }

    @Override
    protected void initView() {
        StatusBarUtil.setLightMode(this);

        Intent intent = getIntent();
        String data = intent.getStringExtra("data");
        mStringExtra = intent.getStringExtra("isss");
        Log.e(TAG, "initView: "+mStringExtra );
        String extra = intent.getStringExtra("toolData");

        mEtPer.setText(data);
        mTvPers.setText(extra);
        mTvPerSize.setText((27-data.length())+"/27");


    }

    @Override
    protected void initListener() {
        mEtPer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTvPerSize.setText(27-s.length()+"/27");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @OnClick({R.id.img_replace, R.id.mTv_outs})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_replace:
                finish();
                break;
            case R.id.mTv_outs:
                String trim = mEtPer.getText().toString().trim();
                if (mStringExtra.equals("1")){
                    SpUtil.setParam(Constants.USERNAME,trim);
                    Log.e(TAG, "onViewClicked: "+trim );
                }else {
                    SpUtil.setParam(Constants.DESC,trim);
                }
                mPresenter.outData();
                finish();
                break;
        }
    }

    @Override
    public void setMessage(MessageBean message) {

    }

    @Override
    public void setError(String msg) {

    }
}
