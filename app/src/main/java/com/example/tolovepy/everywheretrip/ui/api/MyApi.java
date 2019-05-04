package com.example.tolovepy.everywheretrip.ui.api;

import com.example.tolovepy.everywheretrip.bean.DemoBean;
import com.example.tolovepy.everywheretrip.bean.LoginBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface MyApi {

    String sBaseUrl = "http://yun918.cn/study/public/index.php/";

    /**
     * 获取验证码
     * @return
     */
    @GET("verify")
    Observable<DemoBean> getVerifyCodes();
    @GET("verify")
    Observable<LoginBean> getVerifyCode();
}
