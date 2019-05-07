package com.example.tolovepy.everywheretrip.ui.api;

import com.example.tolovepy.everywheretrip.base.BaseApp;
import com.example.tolovepy.everywheretrip.bean.BanmiBean;
import com.example.tolovepy.everywheretrip.dao.BanmiBeanDao;
import com.example.tolovepy.everywheretrip.dao.DaoSession;

import java.util.List;

public class MyGreenDao {

    public static DaoSession daoSession = BaseApp.getmSession();

    /**
     * 插入
     * @param banmiBean
     */
    public static void insert(BanmiBean banmiBean) {
        if (queryOnly(banmiBean) == null) {
            daoSession.insert(banmiBean);
        }
    }

    /**
     * 删除
     * @param banmiBean
     */
    public static void delete(BanmiBean banmiBean){
        if (queryOnly(banmiBean)!=null){
            daoSession.delete(banmiBean);
        }
    }

    /**
     * 查询全部
     * @return
     */
    public static List<BanmiBean> queryAll(){
        List<BanmiBean> all = daoSession.loadAll(BanmiBean.class);
        return all;
    }

    /**
     * 查询是否有该数据
     *
     * @param banmiBean
     * @return
     */
    public static BanmiBean queryOnly(BanmiBean banmiBean) {
        BanmiBean bean = daoSession.queryBuilder(BanmiBean.class)
                .where(BanmiBeanDao.Properties.Name.eq(banmiBean.getName()))
                .build()
                .unique();
        return bean;
    }

}
