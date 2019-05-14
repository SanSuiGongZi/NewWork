package com.example.tolovepy.everywheretrip.ui.activity;

import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tolovepy.everywheretrip.R;
import com.example.tolovepy.everywheretrip.base.BaseActivity;
import com.example.tolovepy.everywheretrip.bean.InformBean;
import com.example.tolovepy.everywheretrip.mvp.presenter.EmptyPre;
import com.example.tolovepy.everywheretrip.mvp.view.EmptyView;
import com.example.tolovepy.everywheretrip.ui.adapter.MyAdapter;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.yanzhenjie.recyclerview.swipe.widget.DefaultItemDecoration;

import java.util.ArrayList;

import butterknife.BindView;

public class informActivity extends BaseActivity<EmptyView, EmptyPre> implements EmptyView {

    @BindView(R.id.mImg_T)
    ImageView mImgT;
    @BindView(R.id.mTv_Clear)
    TextView mTvClear;
    @BindView(R.id.mTools)
    Toolbar mTools;
    @BindView(R.id.Mrv)
    SwipeMenuRecyclerView Mrv;
    private ArrayList<InformBean> list;
    private MyAdapter adapter;

    @Override
    protected EmptyPre initPresenter() {
        return new EmptyPre();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_inform;
    }

    @Override
    protected void initView() {
        mTools.setTitle("");
        setSupportActionBar(mTools);
        Mrv.setLayoutManager(new LinearLayoutManager(this));

        //添加item分割线--------设置分割线颜色为灰色
        Mrv.addItemDecoration(new DefaultItemDecoration(Color.GRAY));
        //设置添加删除按钮
        Mrv.setSwipeMenuCreator(swipeMenuCreator);
        //设置滑动菜单item监听
        Mrv.setSwipeMenuItemClickListener(swipeMenuItemClickListener);

        list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            InformBean bean = new InformBean();
            bean.setTitle("系统通知");
            bean.setTime("2017/10/21");
            if (i == 0) {
                bean.setText("用户注册成功");
            } else {
                bean.setText("通过分享获得2米粒");
            }
            list.add(bean);
        }

        adapter = new MyAdapter(this, list);
        Mrv.setAdapter(adapter);

        mImgT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    // 设置菜单监听器。
    SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        // 创建菜单：
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
            int width = getResources().getDimensionPixelSize(R.dimen.dp_70);
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            SwipeMenuItem deleteItem = new SwipeMenuItem(informActivity.this)
                    .setBackground(R.color.c_FA6A13)
                    .setTextColor(Color.WHITE)
                    .setText("删除")
                    .setWidth(width)
                    .setHeight(height);
            swipeRightMenu.addMenuItem(deleteItem);
        }
    };

    // 菜单点击监听。
    SwipeMenuItemClickListener swipeMenuItemClickListener = new SwipeMenuItemClickListener() {
        @Override
        public void onItemClick(SwipeMenuBridge menuBridge) {
            // 任何操作必须先关闭菜单，否则可能出现Item菜单打开状态错乱。
            menuBridge.closeMenu();

            int direction = menuBridge.getDirection();//左边还是右边菜单
            int adapterPosition = menuBridge.getAdapterPosition();//    ecyclerView的Item的position。
            int position = menuBridge.getPosition();// 菜单在RecyclerView的Item中的Position。

            if (direction == SwipeMenuRecyclerView.RIGHT_DIRECTION) {
                list.remove(adapterPosition);//删除item
                adapter.notifyDataSetChanged();
                Toast.makeText(informActivity.this, "list第" + adapterPosition + "; 右侧菜单第" + position, Toast.LENGTH_SHORT).show();
            }

        }
    };

}
