package com.ckkj.docproject.contract;

import com.ckkj.docproject.base.BasePresenter;
import com.ckkj.docproject.bean.cupboard.ProtectData;

import java.util.List;

/**
 * Created by zct11 on 2017/8/9.
 */

public interface ProtectContract {
    interface View{
        /*
        * 成功获取数据，设置数据到适配器
        * */
        void setProtectData(List<ProtectData.DataBean> data, int total, int pageNum);

        /*
       * 获取登记保护数据失败
       * */
        void getProtectDataFail(String message);

        /*
        * 获取信息
        * */
        void sendMessage(String msg);
    }
    interface Model{

    }
    interface Presenter extends BasePresenter{
        /*
        * 获取登记保护数据
        * */
        void getData(String pageNum, String limits);
       /*
       *  增加登记保护
       * */
        void addData(String StoreroomCode, String StoreroomName, String ProtectType, String ProtectTime, String ProtectResult, String OperUser);

        /*
       *  修改登记保护
       * */
        void updateData(String id, String StoreroomCode, String StoreroomName, String ProtectType, String ProtectTime, String ProtectResult, String OperUser);

       /*
       * 查询登记保护
       * */
       void searchData(String pageNum, String mLimits, String StoreroomCode, String StoreroomName, String ProtectType, String ProtectTime, String ProtectResult, String OperUser);

    }
}
