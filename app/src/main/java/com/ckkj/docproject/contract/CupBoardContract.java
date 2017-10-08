package com.ckkj.docproject.contract;

import com.ckkj.docproject.base.BasePresenter;
import com.ckkj.docproject.bean.cupboard.CupBoardData;


import java.util.List;

/**
 * Created by zct11 on 2017/8/9.
 */

public interface CupBoardContract {
    interface View{
        /*
        * 成功获取数据，设置数据到适配器
        * */
        void setCupBoardData(List<CupBoardData.DataBean> data, int total, int pageNum);

        /*
       * 获取柜架数据失败
       * */
        void getCupBoardDataFail(String message);

        /*
        * 获取信息
        * */
        void sendMessage(String msg);


    }


    interface Model{

    }
    interface Presenter extends BasePresenter{
        /*
        * 获取柜架数据
        * */
        void getData(String pageNum, String limits);
       /*
       *  增加柜架
       * */
        void addData(String name, String code, String location, String storeroomName, String storeroomCode, String remark);

        /*
       *  修改柜架
       * */
        void updateData(String id, String name, String code, String location, String storeroomName, String storeroomCode, String remark);

        /*
        * 搜索柜架
        * */

        void searchData(String pageNum, String limits, String Dataname, String Datavalues);


        /*离线提交*/
        void offtaken(String cupBoards);
    }




}
