package com.ckkj.docproject.presenter.examine;

import android.app.Activity;
import android.content.Context;

import com.ckkj.docproject.bean.examine.Entitive;
import com.ckkj.docproject.callback.examine.EntitiveDataCallBack;
import com.ckkj.docproject.contract.EntitiveContract;
import com.ckkj.docproject.model.examine.EntitiveModel;
import com.ckkj.docproject.utils.NetUtils;

import java.util.List;

/**
 * Created by Ting on 2017/9/13.
 */

public class EntitivePresenter implements EntitiveContract.Presenter {

    private EntitiveModel entitiveModel;
    private EntitiveContract.View mview;


    public EntitivePresenter(EntitiveContract.View view) {
        mview=view;
        entitiveModel=new EntitiveModel((Activity) mview);
    }

    @Override
    public void init() {

    }


    /**
     * 获取数据
     * @param pageNum
     * @param limits
     */
    @Override
    public void getData(String pageNum, String limits) {
        entitiveModel.getEntitve(pageNum, limits, new EntitiveDataCallBack() {
            @Override
            public void success(List<Entitive.DataBean> data, int total, int pageNum) {
                 mview.setEntitiveData(data,total,pageNum);
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
                    mview.getEntitiveDataFail("当前无网络，请保持网络连接！");
                } else {
                    mview.getEntitiveDataFail(e.getMessage());
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
        entitiveModel.checked(examine_processId, new EntitiveDataCallBack() {
            @Override
            public void success(List<Entitive.DataBean> data, int total, int pageNum) {

            }

            @Override
            public void failure(String msg) {
               mview.sendMessage(msg);
            }

            @Override
            public void error(Exception e) {
                if (!NetUtils.hasNetWorkConection((Context) mview)) {
                    mview.getEntitiveDataFail("当前无网络，请保持网络连接！");
                } else {
                    mview.getEntitiveDataFail(e.getMessage());
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
     * @param replicable 复印：0不允许  1允许（必填）
     * @param accessTo 调借：0不允许  1允许（必填）
     */
    public void nextchecked(String examine_id,String examine_processId,String currentTaskId,String auditState,String rejectReason,String replicable,String accessTo){
        entitiveModel.nextchecked(examine_id, examine_processId, currentTaskId, auditState, rejectReason, replicable, accessTo, new EntitiveDataCallBack() {
            @Override
            public void success(List<Entitive.DataBean> data, int total, int pageNum) {

            }

            @Override
            public void failure(String msg) {
                mview.sendMessage(msg);
            }

            @Override
            public void error(Exception e) {
                if (!NetUtils.hasNetWorkConection((Context) mview)) {
                    mview.getEntitiveDataFail("当前无网络，请保持网络连接！");
                } else {
                    mview.getEntitiveDataFail(e.getMessage());
                }
            }

            @Override
            public void success(String status) {
               mview.sendMessage(status);
            }
        });
    }

}
