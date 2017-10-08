package com.ckkj.docproject.presenter.examine;

import android.app.Activity;
import android.content.Context;

import com.ckkj.docproject.bean.examine.Presernters;
import com.ckkj.docproject.callback.examine.PresentersCallBack;
import com.ckkj.docproject.contract.PresenterContract;
import com.ckkj.docproject.model.examine.PresentersModel;
import com.ckkj.docproject.utils.NetUtils;

import java.util.List;

/**
 * Created by Ting on 2017/9/13.
 */

public class PresentersPresenter implements PresenterContract.Presenter {

    private PresentersModel PresenterModel;
    private PresenterContract.View mview;


    public PresentersPresenter(PresenterContract.View view) {
        mview=view;
        PresenterModel=new PresentersModel((Activity) mview);
    }



    /**
     * 获取数据
     * @param pageNum
     * @param limits
     */
    @Override
    public void getData(String pageNum, String limits) {
        PresenterModel.getEntitve(pageNum, limits, new PresentersCallBack() {
            @Override
            public void success(List<Presernters.DataBean> data, int total, int pageNum) {
                 mview.setPresenterData(data,total,pageNum);
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
                    mview.getPresenterDataFail("当前无网络，请保持网络连接！");
                } else {
                    mview.getPresenterDataFail(e.getMessage());
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
        PresenterModel.checked(examine_processId, new PresentersCallBack() {
            @Override
            public void success(List<Presernters.DataBean> data, int total, int pageNum) {

            }

            @Override
            public void failure(String msg) {
               mview.sendMessage(msg);
            }

            @Override
            public void error(Exception e) {
                if (!NetUtils.hasNetWorkConection((Context) mview)) {
                    mview.getPresenterDataFail("当前无网络，请保持网络连接！");
                } else {
                    mview.getPresenterDataFail(e.getMessage());
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
     * @param download 下载：0不允许  1允许（必填）
     */
    public void nextchecked(String examine_id,String examine_processId,String currentTaskId,String auditState,String rejectReason,String download){
        PresenterModel.nextchecked(examine_id, examine_processId, currentTaskId, auditState, rejectReason, download, new PresentersCallBack() {
            @Override
            public void success(List<Presernters.DataBean> data, int total, int pageNum) {

            }

            @Override
            public void failure(String msg) {
                mview.sendMessage(msg);
            }

            @Override
            public void error(Exception e) {
                if (!NetUtils.hasNetWorkConection((Context) mview)) {
                    mview.getPresenterDataFail("当前无网络，请保持网络连接！");
                } else {
                    mview.getPresenterDataFail(e.getMessage());
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
