package com.example.tolovepy.everywheretrip.ui.api;

import com.example.tolovepy.everywheretrip.base.Constants;
import com.example.tolovepy.everywheretrip.bean.AllComment;
import com.example.tolovepy.everywheretrip.bean.AttenBeanDelete;
import com.example.tolovepy.everywheretrip.bean.AttenBeanInsert;
import com.example.tolovepy.everywheretrip.bean.AttenList;
import com.example.tolovepy.everywheretrip.bean.BalanceBean;
import com.example.tolovepy.everywheretrip.bean.CollectionList;
import com.example.tolovepy.everywheretrip.bean.CollectionNo;
import com.example.tolovepy.everywheretrip.bean.CollectionOk;
import com.example.tolovepy.everywheretrip.bean.DemoBean;
import com.example.tolovepy.everywheretrip.bean.LoginInfo;
import com.example.tolovepy.everywheretrip.bean.MainBean;
import com.example.tolovepy.everywheretrip.bean.MainDetailsBean;
import com.example.tolovepy.everywheretrip.bean.MessageBean;
import com.example.tolovepy.everywheretrip.bean.NewVersion;
import com.example.tolovepy.everywheretrip.bean.StayBeans;
import com.example.tolovepy.everywheretrip.bean.WebBean;
import com.example.tolovepy.everywheretrip.bean.WithPath;
import com.example.tolovepy.everywheretrip.bean.WithState;
import com.example.tolovepy.everywheretrip.util.SpUtil;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MyApi {

    //token
    String param = (String) SpUtil.getParam(Constants.TOKEN, "");
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
     * 微博登录
     *
     * @param oAuthToken 就是三方里面的uid
     * @return
     */
    @FormUrlEncoded
    @POST("api/3.0/account/login/oauth")
    Observable<LoginInfo> postWeiboLogin(@Field("oAuthToken") String oAuthToken);

    /**
     * 主页数据
     *
     * @return
     */
    @GET("api/3.0/content/routesbundles?")
    Observable<MainBean> getMain(@Header("banmi-app-token") String token,
                                 @Query("page") int page);

    /**
     * 伴米数据
     *
     * @param page
     * @return
     */
    @GET("api/3.0/banmi")
    Observable<StayBeans> getData(@Query("page") int page,
                                  @Header("banmi-app-token") String token);

    /**
     * 账户余额
     *
     * @param token
     * @return
     */
    @GET("api/3.0/account/balance")
    Observable<BalanceBean> getBan(@Header("banmi-app-token") String token);

    /**
     * 修改个人信息
     *
     * @param userName
     * @param description
     * @param gender
     * @param photo
     * @param token
     * @return
     */
    @POST("api/3.0/account/updateInfo")
    @FormUrlEncoded
    Observable<ResponseBody> upDateInfo(@Field("userName") String userName,
                                        @Field("description") String description,
                                        @Field("gender") String gender,
                                        @Field("photo") String photo,
                                        @Header("banmi-app-token") String token);


    /**
     * 获取个人信息
     *
     * @param token
     * @return
     */
    @GET("api/3.0/account/info")
    Observable<MessageBean> newDateInfo(@Header("banmi-app-token") String token);


    /**
     * 主页详情线路
     *
     * @param routeId
     * @param token
     * @return
     */
    @GET("api/3.0/content/routes/{routeId}")
    Observable<MainDetailsBean> setDetails(@Path("routeId") int routeId,
                                           @Header("banmi-app-token") String token);

    /**
     * 收藏数据
     *
     * @param token
     * @param id
     * @return
     */
    @POST("api/3.0/content/routes/{routeId}/like")
    Observable<CollectionOk> setCollectionOk(@Header("banmi-app-token") String token,
                                             @Path("routeId") int id);

    /**
     * 取消收藏
     *
     * @param token
     * @param id
     * @return
     */
    @POST("api/3.0/content/routes/{routeId}/dislike")
    Observable<CollectionNo> setCollectionNo(@Header("banmi-app-token") String token,
                                             @Path("routeId") int id);

    /**
     * 获取已收藏的数据
     *
     * @param token
     * @return
     */
    @GET("api/3.0/account/collectedRoutes?")
    Observable<CollectionList> setCollectionList(@Header("banmi-app-token") String token,
                                                 @Query("page") int page);

    /**
     * 关注
     *
     * @param token
     * @param banmiId
     * @return
     */
    @POST("api/3.0/banmi/{banmiId}/follow")
    Observable<AttenBeanInsert> setInsert(@Header("banmi-app-token") String token,
                                          @Path("banmiId") int banmiId);

    /**
     * 取消关注
     *
     * @param token
     * @param banmiId
     * @return
     */
    @POST("api/3.0/banmi/{banmiId}/unfollow")
    Observable<AttenBeanDelete> setDelete(@Header("banmi-app-token") String token,
                                          @Path("banmiId") int banmiId);

    /**
     * 获取关注列表
     *
     * @param token
     * @param page
     * @return
     */
    @GET("api/3.0/account/followedBanmi?")
    Observable<AttenList> setList(@Header("banmi-app-token") String token,
                                  @Query("page") int page);

    /**
     * 伴米动态
     *
     * @param token
     * @param page
     * @param banmiId
     * @return
     */
    @GET("api/3.0/banmi/{banmiId}?")
    Observable<WithState> setState(@Header("banmi-app-token") String token,
                                   @Path("banmiId") int banmiId,
                                   @Query("page") int page);

    /**
     * 伴米线路
     *
     * @param token
     * @param page
     * @param banmiId
     * @return
     */
    @GET("api/3.0/banmi/{banmiId}/routes?")
    Observable<WithPath> setPath(@Header("banmi-app-token") String token,
                                 @Path("banmiId") int banmiId,
                                 @Query("page") int page);

    /**
     * 全部评价
     * @param token
     * @param routeId
     * @param page
     * @return
     */
    @GET("api/3.0/content/routes/{routeId}/reviews?")
    Observable<AllComment> allComment(@Header("banmi-app-token") String token,
                                      @Path("routeId") int routeId,
                                      @Query("page") int page);

    /**
     * 旅行专题
     * @param token
     * @return
     */
    @GET("api/3.0/content/bundles")
    Observable<WebBean> setWeb(@Header("banmi-app-token") String token);

    /**
     * 获取版本信息
     * @param token
     * @return
     */
    @GET("api/app/common/getVersionInfo?operating_system=android")
    Observable<NewVersion> getVersion(@Header("banmi-app-token") String token);




}
