package com.ckkj.docproject.model.examine;

import android.app.Activity;

import com.ckkj.docproject.bean.examine.Presernters;
import com.ckkj.docproject.callback.DialogCallBack;
import com.ckkj.docproject.callback.examine.PresentersCallBack;
import com.ckkj.docproject.contract.EntitiveContract;
import com.ckkj.docproject.http.Api;
import com.google.gson.Gson;
import com.lzy.okgo.model.Response;

/**
 * Created by Ting on 2017/9/13.
 */

public class PresentersModel implements EntitiveContract.Model {

    private Activity activity;

    public PresentersModel(Activity activity) {
        this.activity = activity;
    }


    /*查找数据*/
    public void getEntitve(String pagNum, String limits, final PresentersCallBack callBack){
        Api.presenterinfo(pagNum, limits, new DialogCallBack<Presernters>(activity) {
            @Override
            public void onSuccess(Response<Presernters> response) {
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
            public Presernters convertResponse(okhttp3.Response response) throws Throwable {
                return new Gson().fromJson(response.body().string(), Presernters.class);
            }

            @Override
            public void onError(Response<Presernters> response) {
                super.onError(response);
                callBack.error((Exception) response.getException());
            }
        });
    }
    /*审核检验*/
    public  void checked(String examine_processId,final PresentersCallBack callBack){
        Api.presenterchecked(examine_processId, new DialogCallBack<Presernters>(activity) {
            @Override
            public void onSuccess(Response<Presernters> response) {
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
            public Presernters convertResponse(okhttp3.Response response) throws Throwable {
                return new Gson().fromJson(response.body().string(), Presernters.class);
            }

            @Override
            public void onError(Response<Presernters> response) {
                super.onError(response);
                callBack.error((Exception) response.getException());
            }
        });
    }

    //逐级审核
    public  void nextchecked(String examine_id,String examine_processId,String currentTaskId,String auditState,String rejectReason,String allowDownload,final PresentersCallBack callBack){
        Api.presenternextchecked(examine_id, examine_processId, currentTaskId, auditState, rejectReason, allowDownload, new DialogCallBack<Presernters>(activity) {
            @Override
            public void onSuccess(Response<Presernters> response) {
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
            public Presernters convertResponse(okhttp3.Response response) throws Throwable {
                return new Gson().fromJson(response.body().string(), Presernters.class);
            }

            @Override
            public void onError(Response<Presernters> response) {
                super.onError(response);
                callBack.error((Exception) response.getException());
            }
        });
    }
}
