package com.example.tolovepy.everywheretrip.ui.fragment;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.tolovepy.everywheretrip.R;
import com.example.tolovepy.everywheretrip.base.BaseFragment;
import com.example.tolovepy.everywheretrip.mvp.presenter.FragLoginPre;
import com.example.tolovepy.everywheretrip.mvp.view.FragmentLoginView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Code extends BaseFragment<FragmentLoginView, FragLoginPre> implements FragmentLoginView {


    @BindView(R.id.img_start)
    ImageView mImgStart;
    @BindView(R.id.edit_first)
    EditText mEditFirst;
    @BindView(R.id.edit_second)
    EditText mEditSecond;
    @BindView(R.id.edit_third)
    EditText mEditThird;
    @BindView(R.id.edit_fourth)
    EditText mEditFourth;
    @BindView(R.id.et_code)
    EditText et_code;
    private List<String> codes = new ArrayList<>();
    private InputMethodManager imm;

    public Fragment_Code() {
        // Required empty public constructor
    }

    @Override
    protected FragLoginPre initPresenter() {
        return new FragLoginPre();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment__code;
    }

    @Override
    protected void initView() {

        imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

        mImgStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClick != null) {
                    onClick.onClickLister(true);
                }
            }
        });
        initEvent();

    }

    private void initEvent() {
        //验证码输入
        et_code.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable != null && editable.length() > 0) {
                    et_code.setText("");
                    if (codes.size() < 4) {
                        codes.add(editable.toString());
                        showCode();
                    }
                }
            }
        });
        // 监听验证码删除按键
        et_code.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if (keyCode == KeyEvent.KEYCODE_DEL && keyEvent.getAction() == KeyEvent.ACTION_DOWN && codes.size() > 0) {
                    codes.remove(codes.size() - 1);
                    showCode();
                    return true;
                }
                return false;
            }
        });
    }

    /**
     * 显示输入的验证码
     */
    private void showCode() {
        String code1 = "";
        String code2 = "";
        String code3 = "";
        String code4 = "";
        if (codes.size() >= 1) {
            code1 = codes.get(0);
        }
        if (codes.size() >= 2) {
            code2 = codes.get(1);
        }
        if (codes.size() >= 3) {
            code3 = codes.get(2);
        }
        if (codes.size() >= 4) {
            code4 = codes.get(3);
            showLoading();
        }
        mEditFirst.setText(code1);
        mEditSecond.setText(code2);
        mEditThird.setText(code3);
        mEditFourth.setText(code4);
    }

    /**
     * 显示键盘
     */
    public void showSoftInput() {
        //显示软键盘
        if (imm != null && et_code != null) {
            et_code.postDelayed(new Runnable() {
                @Override
                public void run() {
                    imm.showSoftInput(et_code, 0);
                }
            }, 200);
        }
    }

    /**
     * 获得手机号验证码
     *
     * @return 验证码
     */
    public String getPhoneCode() {
        StringBuilder sb = new StringBuilder();
        for (String code : codes) {
            sb.append(code);
        }
        return sb.toString();
    }

    public onClick onClick;

    public void setOnClick(Fragment_Code.onClick onClick) {
        this.onClick = onClick;
    }

    public interface onClick {
        void onClickLister(boolean mBoolean);
    }
}
