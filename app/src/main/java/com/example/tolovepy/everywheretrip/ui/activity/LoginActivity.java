package com.example.tolovepy.everywheretrip.ui.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.widget.FrameLayout;

import com.example.tolovepy.everywheretrip.R;
import com.example.tolovepy.everywheretrip.base.BaseActivity;
import com.example.tolovepy.everywheretrip.base.Constants;
import com.example.tolovepy.everywheretrip.mvp.presenter.EmptyPre;
import com.example.tolovepy.everywheretrip.mvp.view.EmptyView;
import com.example.tolovepy.everywheretrip.ui.fragment.Fragment_login;
import com.umeng.socialize.UMShareAPI;

import butterknife.BindView;

public class LoginActivity extends BaseActivity<EmptyView, EmptyPre> implements EmptyView {

    @BindView(R.id.mfl)
    FrameLayout mfl;
    private int TYPE_LOGIN = 0;
    public static String TAG = "Fragment_login";

    @Override
    protected EmptyPre initPresenter() {
        return new EmptyPre();
    }

    /**
     * 启动当前Activiy
     *
     * @param context
     * @param type    如果是0:代表登录界面;1:代表要绑定手机
     */
    public static void startAct(Context context, int type) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.putExtra(Constants.TYPE, type);
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        initPer();
        int extra = getIntent().getIntExtra(Constants.TYPE, TYPE_LOGIN);
        Fragment_login newLogin = Fragment_login.newLogin(extra);
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().add(R.id.mfl, newLogin,TAG).commit();

    }

    private void initPer() {
        String[] per = {Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE};

        ActivityCompat.requestPermissions(this, per, 100);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //内存泄漏解决方案
        UMShareAPI.get(this).release();
    }

}
