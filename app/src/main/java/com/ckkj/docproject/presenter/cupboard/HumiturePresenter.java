package com.ckkj.docproject.presenter.cupboard;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.ckkj.docproject.bean.cupboard.HumitureData;
import com.ckkj.docproject.callback.cupboard.HumitureDataCallBack;
import com.ckkj.docproject.contract.HumitureContract;
import com.ckkj.docproject.model.cupboard.HumitureModel;
import com.ckkj.docproject.utils.NetUtils;

import java.util.List;

/**
 * Created by zct11 on 2017/8/9.
 */

public class HumiturePresenter implements HumitureContract.Presenter {


    private HumitureContract.View view;
    private HumitureModel model;
    private Context mContext;

    public HumiturePresenter(HumitureContract.View view) {
        this.view = view;
        mContext = (Context) view;
        model = new HumitureModel((Activity) view);

    }


    @Override
    public void getData(String pageNum, String limits) {
        model.getData(pageNum, limits, new HumitureDataCallBack() {
            @Override
            public void success(List<HumitureData.DataBean> data, int total, int pagNum) {

                view.setHumitureData(data, total, pagNum);

            }

            @Override
            public void success(String msg) {

            }

            @Override
            public void error(Exception e) {
                if (!NetUtils.hasNetWorkConection(mContext)) {
                    view.getHumitureDataFail("当前无网络，请保持网络连接！");
                } else {
                    view.getHumitureDataFail(e.getMessage());
                }
            }
        });
    }

    @Override
    public void addData(String checkData, String AmTemp, String AmHumidity, String PmTemp, String PmHumidity, String inTemp, String inHumidity, String outTemp, String outHumidity, String Taken, String custodian, String remark) {
        model.addData(checkData, AmTemp, AmHumidity, PmTemp, PmHumidity, inTemp, inHumidity, outTemp, outHumidity, Taken, custodian, remark, new HumitureDataCallBack() {
            @Override
            public void success(List<HumitureData.DataBean> data, int total, int pagNum) {

            }

            @Override
            public void success(String msg) {
                view.sendMessage(msg);
            }

            @Override
            public void error(Exception e) {
                if (!NetUtils.hasNetWorkConection(mContext)) {
                    view.getHumitureDataFail("当前无网络，请保持网络连接！");
                } else {
                    view.getHumitureDataFail(e.getMessage());
                }
            }
        });
    }


    @Override
    public void updateData(String id, String checkData, String AmTemp, String AmHumidity, String PmTemp, String PmHumidity, String inTemp, String inHumidity, String outTemp, String outHumidity, String Taken, String custodian, String remark) {
        model.updateData(id, checkData, AmTemp, AmHumidity, PmTemp, PmHumidity, inTemp, inHumidity, outTemp, outHumidity, Taken, custodian, remark, new HumitureDataCallBack() {
            @Override
            public void success(List<HumitureData.DataBean> data, int total, int pagNum) {

            }

            @Override
            public void success(String msg) {
                view.sendMessage(msg);
            }

            @Override
            public void error(Exception e) {
                Log.d("HumiturePresenter", e.getMessage());
                if (!NetUtils.hasNetWorkConection(mContext)) {
                    view.getHumitureDataFail("当前无网络，请保持网络连接！");
                } else {
                    view.getHumitureDataFail(e.getMessage());
                }
            }
        });
    }

    @Override
    public void searchData(String checkData, String AmTemp, String AmHumidity, String PmTemp, String PmHumidity, String inTemp, String inHumidity, String outTemp, String outHumidity, String Taken, String custodian, String remark) {
        model.searchData(checkData, AmTemp, AmHumidity, PmTemp, PmHumidity, inTemp, inHumidity, outTemp, outHumidity, Taken, custodian, remark, new HumitureDataCallBack() {
            @Override
            public void success(List<HumitureData.DataBean> data, int total, int pagNum) {
                view.setHumitureData(data, total, pagNum);
            }

            @Override
            public void success(String msg) {

            }

            @Override
            public void error(Exception e) {
                if (!NetUtils.hasNetWorkConection(mContext)) {
                    view.getHumitureDataFail("当前无网络，请保持网络连接！");
                } else {
                    view.getHumitureDataFail(e.getMessage());
                }
            }
        });
    }


    @Override
    public void init() {

    }
}
