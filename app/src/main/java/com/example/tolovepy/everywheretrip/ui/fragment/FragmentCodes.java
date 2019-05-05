package com.example.tolovepy.everywheretrip.ui.fragment;


import android.support.v4.app.Fragment;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tolovepy.everywheretrip.R;
import com.example.tolovepy.everywheretrip.base.BaseFragment;
import com.example.tolovepy.everywheretrip.mvp.presenter.CodePre;
import com.example.tolovepy.everywheretrip.mvp.view.CodeView;
import com.example.tolovepy.everywheretrip.ui.activity.IdentifyingCodeView;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentCodes extends BaseFragment<CodeView, CodePre> implements CodeView {


    @BindView(R.id.img_start)
    ImageView imgStart;
    @BindView(R.id.tv_send_again)
    TextView tvSendAgain;
    @BindView(R.id.tv_input_code)
    TextView tvInputCode;
    @BindView(R.id.icv)
    IdentifyingCodeView icv;
    @BindView(R.id.tv_wait)
    TextView tvWait;

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

}
