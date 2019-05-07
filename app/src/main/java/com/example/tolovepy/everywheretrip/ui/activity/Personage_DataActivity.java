package com.example.tolovepy.everywheretrip.ui.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tolovepy.everywheretrip.R;
import com.example.tolovepy.everywheretrip.base.BaseActivity;
import com.example.tolovepy.everywheretrip.mvp.presenter.EmptyPre;
import com.example.tolovepy.everywheretrip.mvp.view.EmptyView;

import butterknife.BindView;
import butterknife.OnClick;

public class Personage_DataActivity extends BaseActivity<EmptyView, EmptyPre> implements EmptyView {

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
    private int DATA = 2;

    @Override
    protected EmptyPre initPresenter() {
        return new EmptyPre();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_personage__data;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        String data = intent.getStringExtra("data");
        mEtPer.setText(data);
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

    @OnClick({R.id.img_replace, R.id.mTv_outs, R.id.mTv_perSize})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_replace:
                finish();
                break;
            case R.id.mTv_outs:
                String trim = mEtPer.getText().toString().trim();
                Intent intent = new Intent();
                intent.putExtra("trim",trim);
                setResult(DATA,intent);
                finish();
                break;
            case R.id.mTv_perSize:

                break;
        }
    }
}
