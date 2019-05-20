package com.example.tolovepy.everywheretrip.bean;

import android.support.annotation.NonNull;

import com.example.tolovepy.everywheretrip.util.PinYinUtils;


public class CityBean implements Comparable<CityBean>{
    private String cityName; // 城市名
    private int id;
    private double latitude;
    private double longitude;
    private String pinyin; // 城市名对应的拼音
    private String firstLetter; // 拼音的首字母

    public CityBean(String cityName, int id, double latitude, double longitude) {
        this.cityName = cityName;
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        pinyin = PinYinUtils.getPinyin(cityName); // 根据姓名获取拼音
        firstLetter = pinyin.substring(0, 1).toUpperCase(); // 获取拼音首字母并转成大写
        if (!firstLetter.matches("[A-Z]")) { // 如果不在A-Z中则默认为“#”
            firstLetter = "#";
        }
    }

    public String getCityName() {
        return cityName;
    }

    public String getPinyin() {
        return pinyin;
    }

    public String getFirstLetter() {
        return firstLetter;
    }

    public int getId() {
        return id;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public int compareTo(@NonNull CityBean another) {
        if (firstLetter.equals("#") && !another.getFirstLetter().equals("#")) {
            return 1;
        } else if (!firstLetter.equals("#") && another.getFirstLetter().equals("#")) {
            return -1;
        } else {
            return pinyin.compareToIgnoreCase(another.getPinyin());
        }
    }
}
