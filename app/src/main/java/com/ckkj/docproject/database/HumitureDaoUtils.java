package com.ckkj.docproject.database;


import android.content.Context;


import com.greendao.entity.Humiture;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by zct11 on 2017/8/17.
 */

public class HumitureDaoUtils {

    private DaoManager daoManager;

    public HumitureDaoUtils(Context context) {
        daoManager = DaoManager.getInstance();
        daoManager.init(context);
    }


    /*
    * 完成保护登记表的插入
    * */
    public boolean insertHumiture(Humiture humiture) {
        boolean flag = false;
        flag = daoManager.getDaoSession().insert(humiture) != -1 ? true : false;
        return flag;
    }

     /*
    * 完成保护登记表的删除
    * */

    public boolean deleteHumiture() {
        boolean flag = false;
        try {
            daoManager.getDaoSession().deleteAll(Humiture.class);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }



    public List<Humiture> humiturelistAll() {

        return daoManager.getDaoSession().loadAll(Humiture.class);
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
    public long humitureCount(){
        QueryBuilder<Humiture> builder=daoManager.getDaoSession().queryBuilder(Humiture.class);
        return builder.count();
    }


    /*关闭数据库操作*/
    public void close() {
        daoManager.closeConnect();
    }

}
