package com.example.tolovepy.everywheretrip.ui.api;

import com.example.tolovepy.everywheretrip.bean.DemoBean;
import com.example.tolovepy.everywheretrip.bean.LoginBean;
import com.example.tolovepy.everywheretrip.bean.MainBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface MyApi {

    String sBaseUrl = "http://yun918.cn/study/public/index.php/";
    String mainUrl = "http://api.banmi.com/";

    /**
     * 获取验证码
     *
     * @return
     */
    @GET("verify")
    Observable<DemoBean> getVerifyCodes();

    @GET("verify")
    Observable<LoginBean> getVerifyCode();

    @Headers("banmi-app-token:JVy0IvZamK7f7FBZLKFtoniiixKMlnnJ6dWZ6NlsY4HGsxcAA9qvFo8yacHCKHE8YAcd0UF9L59nEm7zk9AUixee0Hl8EeWA880c0ikZBW0KEYuxQy5Z9NP3BNoBi3o3Q0g")
    @GET("api/3.0/content/routesbundles?page=1")
    Observable<MainBean> getMain();
}
