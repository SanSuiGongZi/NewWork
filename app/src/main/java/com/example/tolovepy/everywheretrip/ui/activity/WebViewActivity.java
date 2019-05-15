package com.example.tolovepy.everywheretrip.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tolovepy.everywheretrip.R;
import com.example.tolovepy.everywheretrip.base.BaseActivity;
import com.example.tolovepy.everywheretrip.mvp.presenter.EmptyPre;
import com.example.tolovepy.everywheretrip.mvp.view.EmptyView;
import com.jaeger.library.StatusBarUtil;
import com.just.agentweb.AgentWeb;

import butterknife.BindView;

public class WebViewActivity extends BaseActivity<EmptyView, EmptyPre> implements EmptyView {

    @BindView(R.id.mTv_Tool)
    TextView mTvTool;
    @BindView(R.id.mTool_Web)
    Toolbar mToolWeb;
    @BindView(R.id.mLl_Web)
    LinearLayout mLlWeb;
    private AgentWeb web;

    @Override
    protected EmptyPre initPresenter() {
        return new EmptyPre();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web_view;
    }

    public static void startAct(Context context) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra("data", "用户协议");
        context.startActivity(intent);
    }

    @Override
    protected void initView() {
        StatusBarUtil.setLightMode(this);

        mToolWeb.setTitle("");
        mToolWeb.setNavigationIcon(R.mipmap.back_white);
        setSupportActionBar(mToolWeb);

        Intent intent = getIntent();
        String data = intent.getStringExtra("data");
        int label = intent.getIntExtra("label", 0);
        mTvTool.setText(data);

        mToolWeb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
            web = AgentWeb.with(this)
                    .setAgentWebParent(mLlWeb, new LinearLayout.LayoutParams(-1, -1))
                    .closeIndicator()
                    .createAgentWeb()
                    .ready()
                    .go("https://api.banmi.com/app2017/agreement.html");
            //.useDefaultIndicator()
         /*new WebView(this).setWebChromeClient(new WebChromeClient(){
            @Override
            public void onReceivedTitle(WebView view, String title) {
                //获取网页标题
                super.onReceivedTitle(view, title);
            }
        });*/

//        web.getWebCreator().getWebView().setWebChromeClient(new WebChromeClient() {
//            @Override
//            public void onReceivedTitle(WebView view, String title) {
//                if (!TextUtils.isEmpty(title)) {
//                    mTvTool.setText(title);
//                }
//                super.onReceivedTitle(view, title);
//            }
//        });

    }

    @Override
    protected void onPause() {
        web.getWebLifeCycle().onPause();
        super.onPause();

    }

    @Override
    protected void onResume() {
        web.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        web.getWebLifeCycle().onDestroy();
        super.onDestroy();
    }

}
