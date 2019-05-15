package com.example.tolovepy.everywheretrip.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.webkit.JavascriptInterface;

public class AndroidJs extends Object{

    //定义JS需要调用的方法
    //被JS调用的方法必须加入@JavascriptInterface注解
    Context mContext;

    public AndroidJs(Context context) {
        mContext = context;
    }

    // 定义JS需要调用的方法
    // 被JS调用的方法必须加入@JavascriptInterface注解
    @JavascriptInterface
    public void callAndroid(int id) {
        System.out.println(id);
        Intent intent = new Intent(mContext, SpecialActivity.class);
        intent.putExtra("id",id);
        mContext.startActivity(intent);
    }

    @JavascriptInterface
    public void callAndroid(String msg,int id) {
        System.out.println(id);
        Intent intent = new Intent(mContext, DetailsActivity.class);
        intent.putExtra("id",id);
        mContext.startActivity(intent);
    }

}
