package com.ckkj.docproject.presenter.cupboard;

import android.app.Activity;
import android.content.Context;

import com.ckkj.docproject.bean.cupboard.ProtectData;
import com.ckkj.docproject.callback.cupboard.ProtectDataCallBack;
import com.ckkj.docproject.contract.ProtectContract;
import com.ckkj.docproject.model.cupboard.ProtectModel;
import com.ckkj.docproject.utils.NetUtils;

import java.util.List;

/**
 * Created by zct11 on 2017/8/9.
 */

public class ProtectPresenter implements ProtectContract.Presenter {


    private ProtectContract.View view;
    private ProtectModel model;
    private Context mContext;

    public ProtectPresenter(ProtectContract.View view) {
        this.view=view;
        mContext=(Context)view;
        model=new ProtectModel((Activity) view);

    }


    @Override
    public void getData(String pageNum,String limits) {
        model.getData( pageNum, limits,new ProtectDataCallBack() {
            @Override
            public void success(List<ProtectData.DataBean> data,int total,int pagNum) {

                view.setProtectData(data,total,pagNum);

            }

            @Override
            public void success(String msg) {
                view.sendMessage(msg);
            }

            @Override
            public void error(Exception e) {
                if (!NetUtils.hasNetWorkConection(mContext)) {
                    view.getProtectDataFail("当前无网络，请保持网络连接！");
                } else {
                    view.getProtectDataFail(e.getMessage());
                }
            }
        });
    }


    public void addData(String StoreroomCode, String StoreroomName, String ProtectType, String ProtectTime, String ProtectResult, String OperUser) {
       model.addData(StoreroomCode, StoreroomName, ProtectType, ProtectTime, ProtectResult, OperUser, new ProtectDataCallBack() {
           @Override
           public void success(List<ProtectData.DataBean> data,int total,int pagNum) {

           }

           @Override
           public void success(String msg) {
               view.sendMessage(msg);
           }

           @Override
           public void error(Exception e) {
               if (!NetUtils.hasNetWorkConection(mContext)) {
                   view.getProtectDataFail("当前无网络，请保持网络连接！");
               } else {
                   view.getProtectDataFail(e.getMessage());
               }
           }
       });
    }


    @Override
    public void updateData(String id, String StoreroomCode, String StoreroomName, String ProtectType, String ProtectTime, String ProtectResult, String OperUser ) {
        model.updateData(id, StoreroomCode, StoreroomName, ProtectType, ProtectTime, ProtectResult, OperUser , new ProtectDataCallBack() {
            @Override
            public void success(List<ProtectData.DataBean> data,int total,int pagNum) {

            }

            @Override
            public void success(String msg) {
                view.sendMessage(msg);
            }

            @Override
            public void error(Exception e) {
                if (!NetUtils.hasNetWorkConection(mContext)) {
                    view.getProtectDataFail("当前无网络，请保持网络连接！");
                } else {
                    view.getProtectDataFail(e.getMessage());
                }
            }
        });
    }

    @Override
    public void searchData(String pageNum, String mLimits,String StoreroomCode, String StoreroomName, String ProtectType, String ProtectTime, String ProtectResult, String OperUser) {
        model.searchData(pageNum, mLimits,StoreroomCode,StoreroomName,ProtectType,ProtectTime,ProtectResult,OperUser, new ProtectDataCallBack() {
            @Override
            public void success(List<ProtectData.DataBean> data, int total,int pagNum) {
                view.setProtectData(data,total,pagNum);
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
