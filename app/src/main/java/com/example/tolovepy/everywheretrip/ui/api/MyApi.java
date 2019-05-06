package com.example.tolovepy.everywheretrip.ui.api;

import com.example.tolovepy.everywheretrip.bean.DemoBean;
import com.example.tolovepy.everywheretrip.bean.LoginInfo;
import com.example.tolovepy.everywheretrip.bean.MainBean;
import com.example.tolovepy.everywheretrip.bean.StayBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MyApi {

    public static final int SUCCESS_CODE = 0;
    String sBaseUrl = "http://yun918.cn/study/public/index.php/";
    String mainUrl = "http://api.banmi.com/";
    /**
     * 获取验证码
     *
     * @return
     */
    @GET("verify")
    Observable<DemoBean> getVerifyCode();

    /**
     * 主页数据
     * @return
     */
    @Headers("banmi-app-token:JVy0IvZamK7f7FBZLKFtoniiixKMlnnJ6dWZ6NlsY4HGsxcAA9qvFo8yacHCKHE8YAcd0UF9L59nEm7zk9AUixee0Hl8EeWA880c0ikZBW0KEYuxQy5Z9NP3BNoBi3o3Q0g")
    @GET("api/3.0/content/routesbundles?page=1")
    Observable<MainBean> getMain();


    /**
     * 微博登录
     *
     * @param oAuthToken 就是三方里面的uid
     * @return
     */
    @FormUrlEncoded
    @POST("api/3.0/account/login/oauth")
    Observable<LoginInfo> postWeiboLogin(@Field("oAuthToken") String oAuthToken);

    /**
     * 伴米数据
     * @param page
     * @return
     */
    @Headers("banmi-app-token:JVy0IvZamK7f7FBZLKFtoniiixKMlnnJ6dWZ6NlsY4HGsxcAA9qvFo8yacHCKHE8YAcd0UF9L59nEm7zk9AUixee0Hl8EeWA880c0ikZBW0KEYuxQy5Z9NP3BNoBi3o3Q0g")
    @GET("api/3.0/banmi")
    Observable<StayBean> getData(@Query("page") int page);

}
