package com.example.tolovepy.everywheretrip.ui.fragment;


import android.Manifest;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.example.tolovepy.everywheretrip.R;
import com.example.tolovepy.everywheretrip.base.BaseFragment;
import com.example.tolovepy.everywheretrip.bean.MapCityBean;
import com.example.tolovepy.everywheretrip.bean.MapTabBean;
import com.example.tolovepy.everywheretrip.mvp.presenter.MapPre;
import com.example.tolovepy.everywheretrip.mvp.view.MyMapView;
import com.example.tolovepy.everywheretrip.ui.activity.CityActivity;
import com.example.tolovepy.everywheretrip.util.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Discover extends BaseFragment<MyMapView, MapPre> implements MyMapView {

    private static final String TAG = "Fragment_Discover";
    @BindView(R.id.bMap)
    MapView bMap;
    @BindView(R.id.mTabMap)
    TabLayout mTabMap;
    @BindView(R.id.myLocation)
    ImageButton myLocation;
    private BaiduMap mMap;
    private LocationClient client;
    private LatLng mUser_latlng;
    private TextView mTv_city;
    private double mLatitude;
    private double mLongitude;
    private int mId = 10;
    private BitmapDescriptor bitmap = BitmapDescriptorFactory
            .fromResource(R.mipmap.taste);

    private List<MapTabBean.ResultBean.AllTagsBean> mTags;

    public Fragment_Discover() {
        // Required empty public constructor
    }

    @Override
    protected MapPre initPresenter() {
        return new MapPre();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_discover;
    }

    @Override
    protected void initView() {

        initPer();
        mMap = bMap.getMap();
        mPresenter.setOutTab(mId);
        //开启定位
        location();
    }

    @Override
    protected void initData() {
        LinearLayout mll = getActivity().findViewById(R.id.mll);
        mTv_city = getActivity().findViewById(R.id.mTv_city);
        mTv_city.setText("北京");
        mll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CityActivity.class);
                intent.putExtra("city", "北京");
                startActivityForResult(intent, 1);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 2) {
            mId = data.getIntExtra("id", 0);
            String city = data.getStringExtra("city");
            mLatitude = data.getDoubleExtra("latitude", 39.90657);
            mLongitude = data.getDoubleExtra("longitude", 116.3876);

            mTv_city.setText(city);
            mPresenter.setOutTab(mId);

            MapStatusUpdate status2 = MapStatusUpdateFactory.newLatLng(new LatLng(mLatitude, mLongitude));
            mMap.setMapStatus(status2);
        }
    }

    @Override
    protected void initListener() {
        mTabMap.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() != 0) {
                    MapTabBean.ResultBean.AllTagsBean bean = mTags.get(tab.getPosition() - 1);
                    mMap.clear();
                    int id = bean.getId();
                    mPresenter.setTab(id + "", mId);
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    //权限
    private void initPer() {
        String[] per = {Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.CAMERA};

        ActivityCompat.requestPermissions(getActivity(), per, 100);
    }

    @OnClick(R.id.myLocation)
    public void onViewClicked() {
        locate2User();
    }

    //开启地图的定位图层
    private void location() {
        //开启地图的定位图层
        mMap.setMyLocationEnabled(true);
        //通过LocationClient发起定位
        //定位初始化
        client = new LocationClient(getActivity());

        //通过LocationClientOption设置LocationClient相关参数
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);

        //设置locationClientOption
        client.setLocOption(option);

        //注册LocationListener监听器
        MyLocationListener myLocationListener = new MyLocationListener();
        client.registerLocationListener(myLocationListener);
        //开启地图定位图层
        client.start();
    }

    //定位一上来已经完成了,点击按钮仅仅是把地图视图拉到用户的位置
    private void locate2User() {
        MapStatusUpdate status2 = MapStatusUpdateFactory.newLatLng(mUser_latlng);
        mMap.setMapStatus(status2);
    }

    //拉取经纬度
    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //mapView 销毁后不在处理新接收的位置
            if (location == null || bMap == null) {
                return;
            }
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(location.getDirection())
                    .latitude(location.getLatitude())
                    .longitude(location.getLongitude())
                    .build();
            mUser_latlng = new LatLng(location.getLatitude(), location.getLongitude());
            mMap.setMyLocationData(locData);
        }
    }

    //定义Maker坐标点
    private void addMarker(MapTabBean bean) {
        List<MapTabBean.ResultBean.DisplaySpotsBean> spots = bean.getResult().getDisplaySpots();
        List<MapTabBean.ResultBean.AllTagsBean> tags = bean.getResult().getAllTags();
        Log.e(TAG, "addMarker: s" + tags.size());
        Log.e(TAG, "addMarker:sss " + spots.size());
        for (int i = 0; i < spots.size(); i++) {
            MapTabBean.ResultBean.DisplaySpotsBean displaySpotsBean = spots.get(i);
            MapTabBean.ResultBean.AllTagsBean tagsBean = tags.get(i);

            double longitude = displaySpotsBean.getLongitude();//获取经度
            Log.e(TAG, "addMarker: " + longitude);
            double latitude = displaySpotsBean.getLatitude();//获取维度
            LatLng latLng = new LatLng(latitude, longitude);
            Log.e(TAG, "addMarker: " + latitude);


            //定义Maker坐标点
            //构建Marker图标
            if (tagsBean.getName().equals("倔强的味道")) {
                bitmap = BitmapDescriptorFactory
                        .fromResource(R.mipmap.taste);
            } else if (tagsBean.getName().equals("猎奇另类")) {
                bitmap = BitmapDescriptorFactory
                        .fromResource(R.mipmap.strange);
            } else if (tagsBean.getName().equals("Idol足迹")) {
                bitmap = BitmapDescriptorFactory
                        .fromResource(R.mipmap.idol);
            } else if (tagsBean.getName().equals("生活美学")) {
                bitmap = BitmapDescriptorFactory
                        .fromResource(R.mipmap.life);
            } else if (tagsBean.getName().equals("甩掉修片")) {
                bitmap = BitmapDescriptorFactory
                        .fromResource(R.mipmap.camera);
            } else {
                bitmap = BitmapDescriptorFactory
                        .fromResource(R.mipmap.date);
            }

//            Bundle bundle = new Bundle();
//            bundle.putString("id", "id_01");
            //构建MarkerOption，用于在地图上添加Marker
            OverlayOptions option = new MarkerOptions()
                    .position(latLng)
                    .animateType(MarkerOptions.MarkerAnimateType.jump)
                    .draggable(true)
                    .title("你好,百度")
                    .icon(bitmap);
            //在地图上添加Marker，并显示
            mMap.addOverlay(option);
        }
    }

    //marker点击事件
    private void initListeners() {
        //marker点击事件
        /*mMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            //marker被点击时回调的方法
            //若响应点击事件，返回true，否则返回false
            //默认返回false
            @Override
            public boolean onMarkerClick(Marker marker) {
                Bundle extraInfo = marker.getExtraInfo();
                String id = extraInfo.getString("id");
                if ("id_01".equals(id)) {
                    ToastUtil.showShort(marker.getTitle());
                }

                //walkNavi(marker.getPosition());
                return false;
            }
        });


        mMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                addMarker(latLng);
            }

            @Override
            public boolean onMapPoiClick(MapPoi mapPoi) {
                return false;
            }
        });*/
    }

    @Override
    public void getCity(MapCityBean cityBean) {

    }

    @Override
    public void getTab(MapTabBean tabBean) {

        //添加数据
        addMarker(tabBean);

    }

    @Override
    public void getOutTab(MapTabBean tabBean) {
        mTags = tabBean.getResult().getAllTags();

        mTabMap.removeAllTabs();

        mTabMap.addTab(mTabMap.newTab().setText("全部"));
        for (int i = 0; i < mTags.size(); i++) {
            mTabMap.addTab(mTabMap.newTab().setText(mTags.get(i).getName()));
        }

        //添加数据
        addMarker(tabBean);
    }

    @Override
    public void setError(String error) {
        ToastUtil.showShort(error);
    }

    //生命周期
    @Override
    public void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        bMap.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        bMap.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        bMap.onDestroy();
        if (client != null) {
            client.stop();
        }
        //关闭定位
        mMap.setMyLocationEnabled(false);
    }

}
