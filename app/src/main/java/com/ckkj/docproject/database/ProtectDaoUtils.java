package com.ckkj.docproject.database;


import android.content.Context;

import com.ckkj.docproject.database.DaoManager;
import com.greendao.entity.Protect;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zct11 on 2017/8/17.
 */

public class ProtectDaoUtils {

    private DaoManager daoManager;

    public ProtectDaoUtils(Context context) {
        daoManager = DaoManager.getInstance();
        daoManager.init(context);
    }


    /*
    * 完成保护登记表的插入
    * */
    public boolean insertProtect(Protect protect) {
        boolean flag = false;
        flag = daoManager.getDaoSession().insert(protect) != -1 ? true : false;
        return flag;
    }

     /*
    * 完成保护登记表的删除
    * */

    public boolean deleteProtect() {
        boolean flag = false;
        try {
            daoManager.getDaoSession().deleteAll(Protect.class);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }



    public List<Protect> protectlistAll() {

        return daoManager.getDaoSession().loadAll(Protect.class);
    }
    /*
    * 数据的封装
    * */
    /*public List<Map<String, Object>> MapAll() {
        List<Map<String, Object>> maplist = new ArrayList<Map<String, Object>>();
        List<CupBoard> list = daoManager.getDaoSession().loadAll(CupBoard.class);
        for (CupBoard cup : list) {
            Map<String, Object> map = new HashMap<String, Object>();
            if (cup.getName() != null) {
                map.put("name", cup.getName());
            } else {
                map.put("name", "");
            }
            if (cup.getCode() != null) {
                map.put("code", cup.getCode());
            } else {
                map.put("code", "");
            }
            if (cup.getLocation() != null) {
                map.put("location", cup.getLocation());
            } else {
                map.put("location", "");
            }

            if (cup.getStoreroomCode() != null) {
                map.put("storeroomCode", cup.getStoreroomCode());
            } else {
                map.put("storeroomCode", "");
            }
            if (cup.getStoreroomName() != null) {
                map.put("storeroomName", cup.getStoreroomName());
            } else {
                map.put("storeroomName", "");
            }
            if (cup.getRemark() != null) {
                map.put("remark", cup.getRemark());
            } else {
                map.put("remark", "");
            }

            maplist.add(map);
        }

        return maplist;
    }*/
    /*
    *
    * 获取柜架表的数据量
    * */
    public long protectCount(){
        QueryBuilder<Protect> builder=daoManager.getDaoSession().queryBuilder(Protect.class);
        return builder.count();
    }


    /*关闭数据库操作*/
    public void close() {
        daoManager.closeConnect();
    }

}
