package com.example.tolovepy.everywheretrip.mvp.presenter;

import android.util.Log;
import android.widget.Toast;

import com.example.tolovepy.everywheretrip.R;
import com.example.tolovepy.everywheretrip.base.BaseApp;
import com.example.tolovepy.everywheretrip.base.BasePresenter;
import com.example.tolovepy.everywheretrip.mvp.model.MessageModel;
import com.example.tolovepy.everywheretrip.mvp.view.MessageView;
import com.example.tolovepy.everywheretrip.util.ToastUtil;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

public class MessagePre extends BasePresenter<MessageView> {

    private static final String TAG = "MessagePre";
    private MessageModel model;

    @Override
    protected void initModel() {
        model = new MessageModel();
        mModels.add(model);
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

        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            for (Map.Entry<String, String> entry : data.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                Log.d(TAG, "key: " + key + ",value:" + value);
            }
            Toast.makeText(mMvpView.getAct(), "成功了", Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
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
        }
    };
}
