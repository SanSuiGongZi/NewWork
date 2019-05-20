package com.example.tolovepy.everywheretrip.net;

import android.app.ProgressDialog;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.example.tolovepy.everywheretrip.util.InstallUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;

public class MyServer extends Service {


    private static final String TAG = "MyServer";
    private String mUrl;

    /**
     * 绑定服务时才会调用
     * 必须要实现的方法
     *
     * @param intent
     * @return
     */
    @Override
    public IBinder onBind(Intent intent) {
        return new XiaZai();
    }

    /**
     * 首次创建服务时，系统将调用此方法来执行一次性设置程序（在调用 onStartCommand() 或 onBind() 之前）。
     * 如果服务已在运行，则不会调用此方法。该方法只被调用一次
     */

    @Override
    public void onCreate() {
        super.onCreate();
    }

    /**
     * 每次通过startService()方法启动Service时都会被回调。
     *
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        mUrl = intent.getStringExtra("url");
        Log.e(TAG, "onBind: " + mUrl);

        return super.onStartCommand(intent, flags, startId);

    }

    /**
     * 服务销毁时的回调
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public class XiaZai extends Binder {

        public void setVersionDownload(final Context context, final File file , final ProgressDialog dialog) {

            OkHttpClient client = new OkHttpClient.Builder()
                    .build();

            Request request = new Request.Builder()
                    .url(mUrl)
                    .get()
                    .build();

            okhttp3.Call call = client.newCall(request);

            call.enqueue(new okhttp3.Callback() {
                @Override
                public void onFailure(okhttp3.Call call, IOException e) {
                    Log.e(TAG, "onFailure: " + e.getMessage());
                }

                @Override
                public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                    ResponseBody body = response.body();
                    InputStream inputStream = body.byteStream();
                    saveFile(inputStream, file + "/" + "abc123.apk", (int) body.contentLength(), context , dialog);
                }
            });

        }


        private void saveFile(InputStream inputStream, String s, int max, Context context , ProgressDialog dialog) {

            //mDialog.setMax((int) max);
            //读写的进度
            int count = 0;
            int newCount;
            try {
                FileOutputStream outputStream = new FileOutputStream(new File(s));


                int length = -1;
                byte[] bys = new byte[1024 * 10];

                while ((length = inputStream.read(bys)) != -1) {
                    outputStream.write(bys, 0, length);

                    count += length;
                    newCount = (100 * count) / max;
                    dialog.setProgress(newCount);
                    dialog.setMax(100);
                    Log.d(TAG, "progress: " + newCount + "%" + "    max:" + max);
                }

                inputStream.close();
                outputStream.close();

                InstallUtil.installApk(context, s);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
