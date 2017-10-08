package com.ckkj.docproject.model.examine;

import android.app.Activity;

import com.ckkj.docproject.bean.examine.Entitive;
import com.ckkj.docproject.callback.DialogCallBack;
import com.ckkj.docproject.callback.examine.EntitiveDataCallBack;
import com.ckkj.docproject.contract.EntitiveContract;
import com.ckkj.docproject.http.Api;
import com.google.gson.Gson;
import com.lzy.okgo.model.Response;

/**
 * Created by Ting on 2017/9/13.
 */

public class EntitiveModel implements EntitiveContract.Model {

    private Activity activity;

    public EntitiveModel(Activity activity) {
        this.activity = activity;
    }


    /*查找数据*/
    public void getEntitve(String pagNum, String limits, final EntitiveDataCallBack callBack){
        Api.entitiveinfo(pagNum, limits, new DialogCallBack<com.ckkj.docproject.bean.examine.Entitive>(activity) {
            @Override
            public void onSuccess(Response<com.ckkj.docproject.bean.examine.Entitive> response) {
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
            public com.ckkj.docproject.bean.examine.Entitive convertResponse(okhttp3.Response response) throws Throwable {
                return new Gson().fromJson(response.body().string(), com.ckkj.docproject.bean.examine.Entitive.class);
            }

            @Override
            public void onError(Response<com.ckkj.docproject.bean.examine.Entitive> response) {
                super.onError(response);
                callBack.error((Exception) response.getException());
            }
        });
    }
    /*审核检验*/
    public  void checked(String examine_processId,final EntitiveDataCallBack callBack){
        Api.checked(examine_processId, new DialogCallBack<Entitive>(activity) {
            @Override
            public void onSuccess(Response<Entitive> response) {
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
            public Entitive convertResponse(okhttp3.Response response) throws Throwable {
                return new Gson().fromJson(response.body().string(), com.ckkj.docproject.bean.examine.Entitive.class);
            }

            @Override
            public void onError(Response<com.ckkj.docproject.bean.examine.Entitive> response) {
                super.onError(response);
                callBack.error((Exception) response.getException());
            }
        });
    }

    //逐级审核
    public  void nextchecked(String examine_id,String examine_processId,String currentTaskId,String auditState,String rejectReason,String replicable,String accessTo,final EntitiveDataCallBack callBack){
        Api.nextchecked(examine_id, examine_processId, currentTaskId, auditState, rejectReason, replicable, accessTo, new DialogCallBack<Entitive>(activity) {
            @Override
            public void onSuccess(Response<Entitive> response) {
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
            public Entitive convertResponse(okhttp3.Response response) throws Throwable {
                return new Gson().fromJson(response.body().string(), com.ckkj.docproject.bean.examine.Entitive.class);
            }

            @Override
            public void onError(Response<com.ckkj.docproject.bean.examine.Entitive> response) {
                super.onError(response);
                callBack.error((Exception) response.getException());
            }
        });
    }
}
