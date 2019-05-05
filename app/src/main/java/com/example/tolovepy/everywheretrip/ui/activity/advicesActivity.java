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
import com.example.tolovepy.everywheretrip.mvp.presenter.Presenter;
import com.example.tolovepy.everywheretrip.mvp.view.IView;
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

public class advicesActivity extends BaseActivity<IView, Presenter> implements IView {

    @BindView(R.id.mImg_T1)
    ImageView mImgT1;
    @BindView(R.id.mTv_Clear1)
    TextView mTvClear1;
    @BindView(R.id.mTools1)
    Toolbar mTools1;
    @BindView(R.id.Mrv1)
    SwipeMenuRecyclerView Mrv1;
    private ArrayList<InformBean> list1;
    private MyAdapter adapter;

    @Override
    protected Presenter initPresenter() {
        return new Presenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_advices;
    }

    @Override
    protected void initView() {
        mTools1.setTitle("");
        setSupportActionBar(mTools1);
        Mrv1.setLayoutManager(new LinearLayoutManager(this));
        //添加item分割线--------设置分割线颜色为灰色
        Mrv1.addItemDecoration(new DefaultItemDecoration(Color.GRAY));
        //设置添加删除按钮
        Mrv1.setSwipeMenuCreator(swipeMenuCreator);
        //设置滑动菜单item监听
        Mrv1.setSwipeMenuItemClickListener(swipeMenuItemClickListener);



        list1 = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            InformBean bean = new InformBean();

            bean.setTime("2017/10/21");
            if (i == 0) {
                bean.setTitle("消息回复");
                bean.setText("阚锁回复了你的留言");
            } else {
                bean.setTitle("系统通知");
                bean.setText("林竹回复了你的留言");
            }
            list1.add(bean);
        }

        adapter = new MyAdapter(this, list1);
        Mrv1.setAdapter(adapter);


        mImgT1.setOnClickListener(new View.OnClickListener() {
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
            SwipeMenuItem deleteItem = new SwipeMenuItem(advicesActivity.this)
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
                list1.remove(adapterPosition);//删除item
                adapter.notifyDataSetChanged();
                Toast.makeText(advicesActivity.this, "list第" + adapterPosition + "; 右侧菜单第" + position, Toast.LENGTH_SHORT).show();
            }

        }
    };

}
