package com.ckkj.docproject.presenter.cupboard;

import android.app.Activity;
import android.content.Context;

import com.ckkj.docproject.bean.cupboard.CupBoardData;
import com.ckkj.docproject.callback.cupboard.CupBoardDataCallBack;
import com.ckkj.docproject.contract.CupBoardContract;
import com.ckkj.docproject.event.MessageEvent;
import com.ckkj.docproject.model.cupboard.CupBoardModel;
import com.ckkj.docproject.utils.NetUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by zct11 on 2017/8/9.
 */

public  class CupBoardPresenter implements CupBoardContract.Presenter {


    private CupBoardContract.View view;
    private CupBoardModel model;
    private Context mContext;

    public CupBoardPresenter(CupBoardContract.View view) {
        this.view=view;
        mContext=(Context)view;
        model=new CupBoardModel((Activity) view);

    }


    @Override
    public  void getData(String pageNum,String limits) {
        model.getData(pageNum,limits,new CupBoardDataCallBack() {
            @Override
            public void success(List<CupBoardData.DataBean> data,int total,int pagNum) {

                view.setCupBoardData(data,total,pagNum);

            }

            @Override
            public void success(String msg) {
                // view.sendMessage(msg);
            }

            @Override
            public void error(Exception e) {
                if (!NetUtils.hasNetWorkConection(mContext)) {
                    view.getCupBoardDataFail("当前无网络，请保持网络连接！");
                } else {
                    view.getCupBoardDataFail(e.getMessage());
                }
            }

        });
    }

    @Override
    public void addData(String name, String code, String location, String storeroomName, String storeroomCode, String remark) {
       model.addData(name, code, location, storeroomName, storeroomCode, remark, new CupBoardDataCallBack() {
           @Override
           public void success(List<CupBoardData.DataBean> data,int total,int pagNum) {

           }

           @Override
           public void success(String msg) {
               view.sendMessage(msg);
           }

           @Override
           public void error(Exception e) {
               if (!NetUtils.hasNetWorkConection(mContext)) {
                   view.getCupBoardDataFail("当前无网络，请保持网络连接！");
               } else {
                   view.getCupBoardDataFail(e.getMessage());
               }
           }
       });
    }
    @Override
    public void updateData(String id,String name, String code, String location, String storeroomName, String storeroomCode, String remark) {
        model.updateData(id,name, code, location, storeroomName, storeroomCode, remark, new CupBoardDataCallBack() {
            @Override
            public void success(List<CupBoardData.DataBean> data,int total,int pagNum) {

            }

            @Override
            public void success(String msg) {
                view.sendMessage(msg);
            }

            @Override
            public void error(Exception e) {
                if (!NetUtils.hasNetWorkConection(mContext)) {
                    view.getCupBoardDataFail("当前无网络，请保持网络连接！");
                } else {
                    view.getCupBoardDataFail(e.getMessage());
                }
            }
        });
    }

    @Override
    public void searchData(String pageNum,String limits,String Dataname, String Datavalues) {
       model.searchData(pageNum,limits,Dataname, Datavalues, new CupBoardDataCallBack() {
           @Override
           public void success(List<CupBoardData.DataBean> data, int total,int pagNum) {
               view.setCupBoardData(data,total,pagNum);
           }

           @Override
           public void success(String msg) {

           }

           @Override
           public void error(Exception e) {
               if (!NetUtils.hasNetWorkConection(mContext)) {
                   view.getCupBoardDataFail("当前无网络，请保持网络连接！");
               } else {
                   view.getCupBoardDataFail(e.getMessage());
               }
           }
       });
    }

    @Override
    public void offtaken(String cupBoards) {
        model.offtaken(cupBoards, new CupBoardDataCallBack() {
            @Override
            public void success(List<CupBoardData.DataBean> data, int total, int pageNum) {

            }

            @Override
            public void success(String msg) {

            }

            @Override
            public void error(Exception e) {

           }
       });
    }


    @Override
    public void init() {

    }
}
