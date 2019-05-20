package com.example.tolovepy.everywheretrip.ui.fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tolovepy.everywheretrip.R;
import com.example.tolovepy.everywheretrip.base.BaseFragment;
import com.example.tolovepy.everywheretrip.base.Constants;
import com.example.tolovepy.everywheretrip.bean.CityBean;
import com.example.tolovepy.everywheretrip.bean.MapCityBean;
import com.example.tolovepy.everywheretrip.bean.MapTabBean;
import com.example.tolovepy.everywheretrip.mvp.presenter.MapPre;
import com.example.tolovepy.everywheretrip.mvp.view.MyMapView;
import com.example.tolovepy.everywheretrip.ui.adapter.InlandCitiesAdapter;
import com.example.tolovepy.everywheretrip.ui.adapter.InlandTopRvAdapter;
import com.example.tolovepy.everywheretrip.util.SpUtil;
import com.example.tolovepy.everywheretrip.util.ToastUtil;
import com.example.tolovepy.everywheretrip.widget.MyLetterView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class Fragment_Chana extends BaseFragment<MyMapView, MapPre> implements MyMapView {

    private static final String TAG = "InlandFragment";

    @BindView(R.id.listview)
    ListView listview;
    @BindView(R.id.right_letter)
    MyLetterView right_letter;
    @BindView(R.id.dialog)
    TextView dialog;
    private InlandTopRvAdapter mInlandTopRvAdapter;
    private ArrayList<CityBean> list;
    private Button currentLocation;
    private RecyclerView topRecyclerview;
    private String city;
    private View mInflate;

    @SuppressLint("ValidFragment")
    public Fragment_Chana(String city) {
        // Required empty public constructor
        this.city = city;
    }

    @Override
    protected MapPre initPresenter() {
        return new MapPre();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_chana;
    }

    @Override
    protected void initView() {

        //获取布局
        mInflate = LayoutInflater.from(getActivity()).inflate(R.layout.city_holder, null);
        currentLocation = mInflate.findViewById(R.id.current_location);
        topRecyclerview = mInflate.findViewById(R.id.top_recyclerview);


        String city = (String) SpUtil.getParam(Constants.CITY, "");
        currentLocation.setText(city);
        mPresenter.setCity();


    }

    private void addData(MapCityBean.ResultBean.ChinaBean china) {
        List<MapCityBean.ResultBean.ChinaBean.CitiesBean> cities = china.getCities();
        List<MapCityBean.ResultBean.ChinaBean.TopCitiesBean> topCities = china.getTopCities();

        topRecyclerview.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        mInlandTopRvAdapter = new InlandTopRvAdapter(topCities, getActivity());
        topRecyclerview.setAdapter(mInlandTopRvAdapter);


        mInlandTopRvAdapter.setSendData(new InlandTopRvAdapter.SendData() {
            @Override
            public void sendData(int id, String name, double latitude, double longitude) {
                SpUtil.setParam(Constants.CITY_ID, id);
                SpUtil.setParam(Constants.CITY, name);
                Intent intent = getActivity().getIntent();
                intent.putExtra(Constants.LATITUDE, latitude);
                intent.putExtra(Constants.LONGITUDE, longitude);
                getActivity().setResult(2, intent);
                getActivity().finish();
            }
        });

        listview.addHeaderView(mInflate);

        list = new ArrayList<>();
        for (int i = 0; i < cities.size(); i++) {
            list.add(new CityBean(cities.get(i).getName(), cities.get(i).getId(), cities.get(i).getLatitude(), cities.get(i).getLongitude()));
        }
        Collections.sort(list); // 对list进行排序，需要让User实现Comparable接口重写compareTo方法

        right_letter.setTextDialog(dialog);

        final InlandCitiesAdapter inlandCitiesAdapter = new InlandCitiesAdapter(list, getActivity());

        right_letter.setOnTouchingLetterChangedListener(new MyLetterView.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                // 该字母首次出现的位置
                int position = inlandCitiesAdapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    Log.e(TAG, "onTouchingLetterChanged: " + position);
                    listview.setSelection(position);
                }
            }
        });

        listview.setAdapter(inlandCitiesAdapter);

        inlandCitiesAdapter.setSendData(new InlandCitiesAdapter.SendData() {
            @Override
            public void sendData(int id, String name, double latitude, double longitude) {
                SpUtil.setParam(Constants.CITY_ID, id);
                SpUtil.setParam(Constants.CITY, name);
                Intent intent = getActivity().getIntent();
                intent.putExtra(Constants.LATITUDE, latitude);
                intent.putExtra(Constants.LONGITUDE, longitude);
                getActivity().setResult(2, intent);
                getActivity().finish();
            }
        });
    }

    @Override
    public void getCity(MapCityBean cityBean) {
        MapCityBean.ResultBean.ChinaBean china = cityBean.getResult().getChina();
        addData(china);
    }

    @Override
    public void getTab(MapTabBean tabBean) {

    }

    @Override
    public void getOutTab(MapTabBean tabBean) {

    }

    @Override
    public void setError(String error) {
        ToastUtil.showShort(error);
    }
}
