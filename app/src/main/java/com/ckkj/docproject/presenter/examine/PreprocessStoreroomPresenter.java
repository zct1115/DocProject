package com.ckkj.docproject.presenter.examine;

import android.app.Activity;
import android.content.Context;

import com.ckkj.docproject.bean.examine.Preprocess;
import com.ckkj.docproject.callback.examine.PreprocessCallBack;
import com.ckkj.docproject.contract.PreprocessContract;
import com.ckkj.docproject.contract.PreprocessStoreroomContract;
import com.ckkj.docproject.model.examine.PreprocessModel;
import com.ckkj.docproject.model.examine.PreprocessStoreroomModel;
import com.ckkj.docproject.utils.NetUtils;

import java.util.List;

/**
 * Created by Ting on 2017/9/13.
 */

public class PreprocessStoreroomPresenter implements PreprocessStoreroomContract.Presenter {

    private PreprocessStoreroomModel preprocessModel;
    private PreprocessStoreroomContract.View mview;


    public PreprocessStoreroomPresenter(PreprocessStoreroomContract.View view) {
        mview=view;
        preprocessModel=new PreprocessStoreroomModel((Activity) mview);
    }



    /**
     * 获取数据
     * @param pageNum
     * @param limits
     */
    @Override
    public void getData(String pageNum, String limits) {
        preprocessModel.getEntitve(pageNum, limits, new PreprocessCallBack() {
            @Override
            public void success(List<Preprocess.DataBean> data, int total, int pageNum) {
                 mview.setPreprocessStoreroomData(data,total,pageNum);
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
                    mview.getPreprocessStoreroomDataFail("当前无网络，请保持网络连接！");
                } else {
                    mview.getPreprocessStoreroomDataFail(e.getMessage());
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
        preprocessModel.checked(examine_processId, new PreprocessCallBack() {
            @Override
            public void success(List<Preprocess.DataBean> data, int total, int pageNum) {

            }

            @Override
            public void failure(String msg) {
               mview.sendMessage(msg);
            }

            @Override
            public void error(Exception e) {
                if (!NetUtils.hasNetWorkConection((Context) mview)) {
                    mview.getPreprocessStoreroomDataFail("当前无网络，请保持网络连接！");
                } else {
                    mview.getPreprocessStoreroomDataFail(e.getMessage());
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
        preprocessModel.nextchecked(examine_id, examine_processId, currentTaskId, auditState, rejectReason, new PreprocessCallBack() {
            @Override
            public void success(List<Preprocess.DataBean> data, int total, int pageNum) {

            }

            @Override
            public void failure(String msg) {
                mview.sendMessage(msg);
            }

            @Override
            public void error(Exception e) {
                if (!NetUtils.hasNetWorkConection((Context) mview)) {
                    mview.getPreprocessStoreroomDataFail("当前无网络，请保持网络连接！");
                } else {
                    mview.getPreprocessStoreroomDataFail(e.getMessage());
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
