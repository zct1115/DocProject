package com.ckkj.docproject.contract;

import com.ckkj.docproject.base.BasePresenter;
import com.ckkj.docproject.bean.examine.Entitive;


import java.util.List;

/**
 * Created by zct11 on 2017/8/9.
 */

public interface EntitiveContract {
    interface View{
        /*
        * 成功获取数据，设置数据到适配器
        * */
        void setEntitiveData(List<Entitive.DataBean> data, int total, int pageNum);

        /*
       * 获取实体档案数据失败
       * */
        void getEntitiveDataFail(String message);

        /*
        * 获取信息
        * */
        void sendMessage(String msg);

        /*
        * 审核检验
        * */
        void getStatus(String status);


    }


    interface Model{

    }
    interface Presenter extends BasePresenter{
        /*
        * 获取柜架数据
        * */
        void getData(String pageNum, String limits);

        void checked(String examine_processId);

        void nextchecked(String examine_id,String examine_processId,String currentTaskId,String auditState,String rejectReason,String replicable,String accessTo);
    }




}
