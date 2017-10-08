package com.ckkj.docproject.contract;

import com.ckkj.docproject.base.BasePresenter;
import com.ckkj.docproject.bean.cupboard.CupBoardData;
import com.ckkj.docproject.bean.cupboard.HumitureData;

import java.util.List;

/**
 * Created by zct11 on 2017/8/9.
 */

public interface HumitureContract {
    interface View{
        /*
        * 成功获取数据，设置数据到适配器
        * */
        void setHumitureData(List<HumitureData.DataBean> data, int total, int pageNum);

        /*
       * 获取温湿度数据失败
       * */
        void getHumitureDataFail(String message);

        /*
        * 获取信息
        * */
        void sendMessage(String msg);
    }
    interface Model{

    }
    interface Presenter extends BasePresenter{
        /*
        * 获取温湿度数据
        * */
        void getData(String pageNum, String limits);
       /*
       *  增加温湿度
       * */
       void addData(String checkData, String AmTemp, String AmHumidity, String PmTemp, String PmHumidity, String inTemp, String inHumidity, String outTemp, String outHumidity, String Taken, String custodian, String remark);

        /*
       *  修改温湿度
       * */
        void updateData(String id, String checkData, String AmTemp, String AmHumidity, String PmTemp, String PmHumidity, String inTemp, String inHumidity, String outTemp, String outHumidity, String Taken, String custodian, String remark);

        /*
      *  搜索温湿度
      * */
        void searchData(String checkData, String AmTemp, String AmHumidity, String PmTemp, String PmHumidity, String inTemp, String inHumidity, String outTemp, String outHumidity, String Taken, String custodian, String remark);
    }
}
