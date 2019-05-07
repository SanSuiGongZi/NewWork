package com.example.tolovepy.everywheretrip.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.tolovepy.everywheretrip.R;
import com.example.tolovepy.everywheretrip.base.BaseActivity;
import com.example.tolovepy.everywheretrip.mvp.presenter.EmptyPre;
import com.example.tolovepy.everywheretrip.mvp.view.EmptyView;

import butterknife.BindView;
import butterknife.OnClick;

public class HeadActivity extends BaseActivity<EmptyView, EmptyPre> implements EmptyView {


    @BindView(R.id.img_replaces)
    ImageView imgReplaces;
    @BindView(R.id.mImg_popup)
    ImageView mImgPopup;
    @BindView(R.id.mTool_head)
    Toolbar mToolHead;
    @BindView(R.id.mImg_head)
    ImageView mImgHead;
    private PopupWindow mWindow;

    @Override
    protected EmptyPre initPresenter() {
        return new EmptyPre();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_head;
    }

    @Override
    protected void initView() {
        if (Build.VERSION.SDK_INT >= 23) {
            int REQUEST_CODE_CONTACT = 101;
            String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
            //验证是否许可权限
            for (String str : permissions) {
                if (this.checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
                    //申请权限
                    this.requestPermissions(permissions, REQUEST_CODE_CONTACT);
                    return;
                }
            }
        }

        mToolHead.setTitle("");
        setSupportActionBar(mToolHead);
    }

    @OnClick({R.id.img_replaces, R.id.mImg_popup})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_replaces:
                finish();
                break;
            case R.id.mImg_popup:
                initpopupPhoto();
                break;
        }
    }

    private void initpopupPhoto() {
        View view = LayoutInflater.from(this).inflate(R.layout.item_popup_photo, null);
        mWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        TextView tv_no = view.findViewById(R.id.mTv_noss);
        TextView tv_camera = view.findViewById(R.id.mTv_camera);
        TextView tv_photo = view.findViewById(R.id.mTv_photo);

        mWindow.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.c_60)));
        mWindow.setOutsideTouchable(true);
        //设置除布局外的点击事件
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //关闭popop
                mWindow.dismiss();
            }
        });
        mWindow.showAtLocation(view, 0, 0, Gravity.BOTTOM);

        tv_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWindow.dismiss();
            }
        });

        //相机
        tv_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWindow.dismiss();

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 57);
            }
        });
        //相册
        tv_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWindow.dismiss();
                Intent intent2 = new Intent(Intent.ACTION_PICK);
                intent2.setType("image/*");
                startActivityForResult(intent2, 99);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 57 && resultCode == RESULT_OK) {
            Bitmap bitmap = data.getParcelableExtra("data");
            mImgHead.setImageBitmap(bitmap);
            //iv.setImageBitmap(bitmap);
        }
        if (requestCode == 99 && resultCode == this.RESULT_OK) {
            Uri uri = data.getData();
            mImgHead.setImageURI(uri);
            //iv.setImageURI(uri);
        }
    }
}
