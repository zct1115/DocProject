package com.ckkj.docproject.presenter.examine;

import android.app.Activity;
import android.content.Context;

import com.ckkj.docproject.bean.examine.Medium;
import com.ckkj.docproject.callback.examine.MediumCallBack;
import com.ckkj.docproject.contract.MediumContract;
import com.ckkj.docproject.model.examine.MediumModel;
import com.ckkj.docproject.utils.NetUtils;

import java.util.List;

/**
 * Created by Ting on 2017/9/13.
 */

public class MediumPresenter implements MediumContract.Presenter {

    private MediumModel mediumModel;
    private MediumContract.View mview;


    public MediumPresenter(MediumContract.View view) {
        mview=view;
        mediumModel=new MediumModel((Activity) mview);
    }



    /**
     * 获取数据
     * @param pageNum
     * @param limits
     */
    @Override
    public void getData(String pageNum, String limits) {
        mediumModel.getMedium(pageNum, limits, new MediumCallBack() {
            @Override
            public void success(List<Medium.DataBean> data, int total, int pageNum) {
                 mview.setMediumData(data,total,pageNum);
            }

            @Override
            public void failure(String msg) {
              mview.sendMessage(msg);
            }

            @Override
            public void success(String status) {

            }

            @Override
            public void error(Exception e) {
                if (!NetUtils.hasNetWorkConection((Context) mview)) {
                    mview.getMediumDataFail("当前无网络，请保持网络连接！");
                } else {
                    mview.getMediumDataFail(e.getMessage());
                }
            }
        });
    }


    /**
     *
     * 审核检验
     * @param examine_processId    任务标识id
     */
    public void checked(String examine_processId){
        mediumModel.checked(examine_processId, new MediumCallBack() {
            @Override
            public void success(List<Medium.DataBean> data, int total, int pageNum) {

            }

            @Override
            public void failure(String msg) {
               mview.sendMessage(msg);
            }

            @Override
            public void error(Exception e) {
                if (!NetUtils.hasNetWorkConection((Context) mview)) {
                    mview.getMediumDataFail("当前无网络，请保持网络连接！");
                } else {
                    mview.getMediumDataFail(e.getMessage());
                }
            }

            @Override
            public void success(String status) {
                mview.getStatus(status);
            }
        });
    }


    /**
     *
     * 逐级审核
     * @param examine_id   任务的数据id
     * @param examine_processId 任务标识id
     * @param currentTaskId 节点id
     * @param auditState 审核意见: AGREE同意   REJECT驳回 （必填）
     * @param rejectReason 备注
     */
    public void nextchecked(String examine_id,String examine_processId,String currentTaskId,String auditState,String rejectReason){
        mediumModel.nextchecked(examine_id, examine_processId, currentTaskId, auditState, rejectReason, new MediumCallBack() {
            @Override
            public void success(List<Medium.DataBean> data, int total, int pageNum) {

            }

            @Override
            public void failure(String msg) {
                mview.sendMessage(msg);
            }

            @Override
            public void error(Exception e) {
                if (!NetUtils.hasNetWorkConection((Context) mview)) {
                    mview.getMediumDataFail("当前无网络，请保持网络连接！");
                } else {
                    mview.getMediumDataFail(e.getMessage());
                }
            }

            @Override
            public void success(String status) {
               mview.sendMessage(status);
            }
        });
    }

    @Override
    public void init() {

    }
}
