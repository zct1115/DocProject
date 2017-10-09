package com.ckkj.docproject.contract;

import com.ckkj.docproject.base.BasePresenter;
import com.ckkj.docproject.bean.examine.Medium;
import com.ckkj.docproject.bean.examine.Preprocess;

import java.util.List;

/**
 * Created by Ting on 2017/9/14.
 */

public interface MediumContract {
    interface View{
         /*
        * 成功获取数据，设置数据到适配器
        * */
         void setMediumData(List<Medium.DataBean> data, int total, int pageNum);

        /*
      * 获取预归数据失败
      * */
        void getMediumDataFail(String message);

        /*
        * 获取信息
        * */
        void sendMessage(String msg);

        /*
        * 审核检验
        * */
        void getStatus(String status);

    }
    interface Presenter extends BasePresenter{
        /**
         * 获取预归数据
         * @param pageNum
         * @param limits
         */

        void getData(String pageNum, String limits);

        /**
         * 审核检验
         * @param examine_processId
         */
        void checked(String examine_processId);

        /**
         * 逐级审核
         * @param examine_id
         * @param examine_processId
         * @param currentTaskId
         * @param auditState
         * @param rejectReason
         */
        void nextchecked(String examine_id, String examine_processId, String currentTaskId, String auditState, String rejectReason);
    }
    interface Model{

    }
}
