package com.ckkj.docproject.model.examine;

import android.app.Activity;

import com.ckkj.docproject.bean.examine.Medium;
import com.ckkj.docproject.bean.examine.Preprocess;
import com.ckkj.docproject.callback.DialogCallBack;
import com.ckkj.docproject.callback.examine.MediumCallBack;
import com.ckkj.docproject.callback.examine.PreprocessCallBack;
import com.ckkj.docproject.contract.MediumContract;
import com.ckkj.docproject.contract.PreprocessStoreroomContract;
import com.ckkj.docproject.http.Api;
import com.google.gson.Gson;
import com.lzy.okgo.model.Response;

/**
 * Created by Ting on 2017/9/13.
 */

public class MediumModel implements MediumContract.Model {

    private Activity activity;

    public MediumModel(Activity activity) {
        this.activity = activity;
    }


    /*查找数据*/
    public void getMedium(String pagNum, String limits, final MediumCallBack callBack){
        Api.mediuminfo(pagNum, limits, new DialogCallBack<Medium>(activity) {
            @Override
            public void onSuccess(Response<Medium> response) {
                if(response.body()!=null){
                    if(response.body().getStatus().equals("1")) {
                        callBack.success(response.body().getData(),response.body().getTotal(),response.body().getPageNum());
                    }else {
                        callBack.failure(response.body().getMsg());
                    }

                }
                else {
                    callBack.error(new Exception("获取数据失败"));
                }
            }

            @Override
            public Medium convertResponse(okhttp3.Response response) throws Throwable {
                return new Gson().fromJson(response.body().string(), Medium.class);
            }

            @Override
            public void onError(Response<Medium> response) {
                super.onError(response);
                callBack.error((Exception) response.getException());
            }
        });
    }
    /*审核检验*/
    public  void checked(String examine_processId,final MediumCallBack callBack){
        Api.mediumchecked(examine_processId, new DialogCallBack<Medium>(activity) {
            @Override
            public void onSuccess(Response<Medium> response) {
                if(response.body()!=null){
                    if(response.body().getStatus().equals("1")) {
                        callBack.success(response.body().getStatus());
                    }else {
                        callBack.failure(response.body().getMsg());
                    }

                }
                else {
                    callBack.error(new Exception("审核失败"));
                }
            }

            @Override
            public Medium convertResponse(okhttp3.Response response) throws Throwable {
                return new Gson().fromJson(response.body().string(), Medium.class);
            }

            @Override
            public void onError(Response<Medium> response) {
                super.onError(response);
                callBack.error((Exception) response.getException());
            }
        });
    }

    //逐级审核
    public  void nextchecked(String examine_id,String examine_processId,String currentTaskId,String auditState,String rejectReason,final MediumCallBack callBack){
        Api.mediumnextchecked(examine_id, examine_processId, currentTaskId, auditState, rejectReason, new DialogCallBack<Medium>(activity) {
            @Override
            public void onSuccess(Response<Medium> response) {
                if(response.body()!=null){
                    if(response.body().getStatus().equals("1")) {
                        callBack.success("审核成功");
                    }
                    callBack.failure(response.body().getMsg());
                }
                else {
                    callBack.error(new Exception("审核失败"));
                }
            }

            @Override
            public Medium convertResponse(okhttp3.Response response) throws Throwable {
                return new Gson().fromJson(response.body().string(), Medium.class);
            }

            @Override
            public void onError(Response<Medium> response) {
                super.onError(response);
                callBack.error((Exception) response.getException());
            }
        });
    }
}
