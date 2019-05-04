package com.example.tolovepy.everywheretrip.ui.wxapi;

import android.os.Bundle;

import com.example.tolovepy.everywheretrip.R;
import com.umeng.socialize.weixin.view.WXCallbackActivity;

public class WXEntryActivity extends WXCallbackActivity {

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_wxentry);
    }
}
