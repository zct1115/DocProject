package com.ckkj.docproject.database;


import android.content.Context;


import com.ckkj.docproject.database.DaoManager;
import com.greendao.dao.CupboardDao;
import com.greendao.entity.Cupboard;


import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zct11 on 2017/8/17.
 */

public class CupBoardDaoUtils {

    private DaoManager daoManager;

    public CupBoardDaoUtils(Context context) {
        daoManager = DaoManager.getInstance();
        daoManager.init(context);
    }


    /*
    * 完成柜架表的插入
    * */
    public boolean insertCupboard(Cupboard cupBoard) {
        boolean flag = false;
        flag = daoManager.getDaoSession().insert(cupBoard) != -1 ? true : false;
        return flag;
    }




    /*
    * 完成柜架表的删除
    * */

    public boolean deleteCupboard() {
        boolean flag = false;
        try {
            daoManager.getDaoSession().deleteAll(Cupboard.class);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public boolean samecupboard(Cupboard cupBoard){
        QueryBuilder t=daoManager.getDaoSession().queryBuilder(Cupboard.class);
        t.where(CupboardDao.Properties.Code.eq(cupBoard.getCode()));
        List test=t.list();
        if(test!=null){
            return false;
        }
        return true;
    }



    /*
    * 查询柜架多条记录    */
    public List<Cupboard> listAll() {

        return daoManager.getDaoSession().loadAll(Cupboard.class);
    }


    /*
    * 柜架数据的封装
    * */
    public List<Map<String, Object>> MapAll() {
        List<Map<String, Object>> maplist = new ArrayList<Map<String, Object>>();
        List<Cupboard> list = daoManager.getDaoSession().loadAll(Cupboard.class);
        for (Cupboard cup : list) {
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
    }
    /*
    *
    * 获取柜架表的数据量
    * */
    public long cupboardCount(){
        QueryBuilder<Cupboard> builder = daoManager.getDaoSession().queryBuilder(Cupboard.class);
        return builder.count();
    }


    /*关闭数据库操作*/
    public void close() {
        daoManager.closeConnect();
    }

}
