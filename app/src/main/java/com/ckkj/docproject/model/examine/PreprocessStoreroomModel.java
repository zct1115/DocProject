package com.ckkj.docproject.model.examine;

import android.app.Activity;

import com.ckkj.docproject.bean.examine.Preprocess;
import com.ckkj.docproject.callback.DialogCallBack;
import com.ckkj.docproject.callback.examine.PreprocessCallBack;
import com.ckkj.docproject.contract.EntitiveContract;
import com.ckkj.docproject.http.Api;
import com.google.gson.Gson;
import com.lzy.okgo.model.Response;

/**
 * Created by Ting on 2017/9/13.
 */

public class PreprocessStoreroomModel implements EntitiveContract.Model {

    private Activity activity;

    public PreprocessStoreroomModel(Activity activity) {
        this.activity = activity;
    }


    /*查找数据*/
    public void getEntitve(String pagNum, String limits, final PreprocessCallBack callBack){
        Api.preprocessstoreroominfo(pagNum, limits, new DialogCallBack<Preprocess>(activity) {
            @Override
            public void onSuccess(Response<Preprocess> response) {
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
            public Preprocess convertResponse(okhttp3.Response response) throws Throwable {
                return new Gson().fromJson(response.body().string(), Preprocess.class);
            }

            @Override
            public void onError(Response<Preprocess> response) {
                super.onError(response);
                callBack.error((Exception) response.getException());
            }
        });
    }
    /*审核检验*/
    public  void checked(String examine_processId,final PreprocessCallBack callBack){
        Api.preprocessstoreroomchecked(examine_processId, new DialogCallBack<Preprocess>(activity) {
            @Override
            public void onSuccess(Response<Preprocess> response) {
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
            public Preprocess convertResponse(okhttp3.Response response) throws Throwable {
                return new Gson().fromJson(response.body().string(), Preprocess.class);
            }

            @Override
            public void onError(Response<Preprocess> response) {
                super.onError(response);
                callBack.error((Exception) response.getException());
            }
        });
    }

    //逐级审核
    public  void nextchecked(String examine_id,String examine_processId,String currentTaskId,String auditState,String rejectReason,final PreprocessCallBack callBack){
        Api.preprocessstoreroomnextchecked(examine_id, examine_processId, currentTaskId, auditState, rejectReason, new DialogCallBack<Preprocess>(activity) {
            @Override
            public void onSuccess(Response<Preprocess> response) {
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
            public Preprocess convertResponse(okhttp3.Response response) throws Throwable {
                return new Gson().fromJson(response.body().string(), Preprocess.class);
            }

            @Override
            public void onError(Response<Preprocess> response) {
                super.onError(response);
                callBack.error((Exception) response.getException());
            }
        });
    }
}
