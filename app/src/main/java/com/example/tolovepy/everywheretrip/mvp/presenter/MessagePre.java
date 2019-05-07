package com.example.tolovepy.everywheretrip.mvp.presenter;

import android.util.Log;

import com.example.tolovepy.everywheretrip.R;
import com.example.tolovepy.everywheretrip.base.BaseApp;
import com.example.tolovepy.everywheretrip.base.BasePresenter;
import com.example.tolovepy.everywheretrip.base.Constants;
import com.example.tolovepy.everywheretrip.bean.DemoBean;
import com.example.tolovepy.everywheretrip.bean.LoginInfo;
import com.example.tolovepy.everywheretrip.mvp.model.MessageModel;
import com.example.tolovepy.everywheretrip.mvp.model.Model;
import com.example.tolovepy.everywheretrip.mvp.view.MessageView;
import com.example.tolovepy.everywheretrip.net.ResultCallBack;
import com.example.tolovepy.everywheretrip.util.SpUtil;
import com.example.tolovepy.everywheretrip.util.ToastUtil;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

public class MessagePre extends BasePresenter<MessageView> {

    private static final String TAG = "MessagePre";
    private MessageModel model;
    private Model mModel;

    @Override
    protected void initModel() {
        model = new MessageModel();
        mModels.add(model);

        mModel = new Model();
        mModels.add(mModel);
    }

    public void getCode(){
        model.getVerifyCode(new ResultCallBack<DemoBean>() {
            @Override
            public void onSuccess(DemoBean bean) {
                mMvpView.setCode(bean.getData());
            }

            @Override
            public void onFail(String msg) {
            }
        });
    }


    public void oauthLogin(SHARE_MEDIA type) {
        UMShareAPI umShareAPI = UMShareAPI.get(mMvpView.getAct());
        umShareAPI.getPlatformInfo(mMvpView.getAct(), type, umAuthListener);
    }

    UMAuthListener umAuthListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {
            Log.e(TAG, "onStart: ");
        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            Log.e(TAG, "onComplete: " + data.toString());
            loginSina(data.get("uid"));

            for (Map.Entry<String, String> entry : data.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (key.equals("name")) {
                    Log.e(TAG, "onComplete: "+value );
                    SpUtil.setParam(Constants.USERNAME,value);
                }
                if (key.equals("avatar_hd")){
                    Log.e(TAG, "onComplete: "+value );
                    SpUtil.setParam(Constants.PHOTO,value);
                }

                Log.d(TAG, "key: " + key + ",value:" + value);
            }
            ToastUtil.showShort(BaseApp.getRes().getString(R.string.oauth_ok));
        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Log.e(TAG, "onError: " + t.getMessage());
            ToastUtil.showShort(BaseApp.getRes().getString(R.string.oauth_error));
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            ToastUtil.showShort(BaseApp.getRes().getString(R.string.oauth_cancel));
            Log.e(TAG, "onCancel: ");
        }
    };

    /**
     * 新浪微博登录
     * @param uid
     */
    private void loginSina(String uid) {
        model.loginSina(uid, new ResultCallBack<LoginInfo>() {
            @Override
            public void onSuccess(LoginInfo bean) {
                //登录成功了,需要做什么
                //1.跳转主页面
                //2.保存用户信息
                if (bean.getResult() != null) {
                    saveUserInfo(bean.getResult());
                    if (mMvpView != null){
                        mMvpView.toastShort(BaseApp.getRes().getString(R.string.login_success));
                        mMvpView.goMainActivity();

                    }
                }else {
                    if (mMvpView != null){
                        mMvpView.toastShort(BaseApp.getRes().getString(R.string.login_fail));
                    }
                }
            }

            @Override
            public void onFail(String msg) {
                if (mMvpView != null){
                    mMvpView.toastShort(msg);
                }
            }
        });
    }

    /**
     * 保存用户信息
     * @param result
     */
    private void saveUserInfo(LoginInfo.ResultBean result) {
        SpUtil.setParam(Constants.TOKEN,result.getToken());
        SpUtil.setParam(Constants.DESC,result.getDescription());
        SpUtil.setParam(Constants.GENDER,result.getGender());
        SpUtil.setParam(Constants.EMAIL,result.getEmail());
        SpUtil.setParam(Constants.PHONE,result.getPhone());
    }

}
