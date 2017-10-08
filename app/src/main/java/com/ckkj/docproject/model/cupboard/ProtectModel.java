package com.ckkj.docproject.model.cupboard;

import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;

import com.ckkj.docproject.bean.cupboard.ProtectData;
import com.ckkj.docproject.callback.DialogCallBack;
import com.ckkj.docproject.callback.cupboard.ProtectDataCallBack;
import com.ckkj.docproject.contract.ProtectContract;
import com.ckkj.docproject.http.Api;
import com.google.gson.Gson;
import com.lzy.okgo.model.Response;

/**
 * Created by zct11 on 2017/8/9.
 */

public class ProtectModel implements ProtectContract.Model {

    private Activity activity;

    public ProtectModel(Activity activity){
        this.activity=activity;
    }

    public void getData(String pageNum,String limits,final ProtectDataCallBack callBack){
        Api.protectinfo(pageNum,limits,new DialogCallBack<ProtectData>(activity) {
            @Override
            public void onSuccess(Response<ProtectData> response) {
                if(response.body()!=null){
                    if(response.body().getStatus().equals("1")) {
                        Log.d("ProtectModel", "response.body().getData():" + response.body().getData());
                        callBack.success(response.body().getData(),response.body().getTotal(),response.body().getPageNum());
                        callBack.success("添加成功");
                    }else if(response.body().getStatus().equals("2")){
                        callBack.success(response.body().getMsg());
                    }else if(response.body().getStatus().equals("3")){
                        callBack.success(response.body().getMsg());
                    }else if(response.body().getStatus().equals("4")){
                        callBack.success(response.body().getMsg());
                    }else if(response.body().getStatus().equals("5")){
                        callBack.success(response.body().getMsg());
                    }else if(response.body().getStatus().equals("6")){
                        callBack.success(response.body().getMsg());
                    }else if(response.body().getStatus().equals("7")){
                        callBack.success(response.body().getMsg());
                    } else if(response.body().getStatus().equals("0")){
                        callBack.success(response.body().getMsg());
                    }
                }
                else {
                    callBack.error(new Exception("添加失败"));
                }
            }

            @Override
            public ProtectData convertResponse(okhttp3.Response response) throws Throwable {
                return new Gson().fromJson(response.body().string(), ProtectData.class);
            }

            @Override
            public void onError(Response<ProtectData> response) {
                super.onError(response);
                callBack.error((Exception) response.getException());


            }
        });
    }

    public void addData(String StoreroomCode, String StoreroomName, String ProtectType, String ProtectTime, String ProtectResult, String OperUser,final ProtectDataCallBack callBack){
        Api.addprotectinfo(StoreroomCode, StoreroomName, ProtectType, ProtectTime, ProtectResult, OperUser, new DialogCallBack<ProtectData>(activity) {
            @Override
            public void onSuccess(Response<ProtectData> response) {
                if(response.body()!=null){
                    Log.d("ProtectModel", response.body().getStatus());
                    if(response.body().getStatus().equals("1")) {
                        callBack.success(response.body().getMsg());
                    }else if(response.body().getStatus().equals("2")){
                        callBack.success(response.body().getMsg());
                    }else if(response.body().getStatus().equals("3")){
                        callBack.success(response.body().getMsg());
                    }else if(response.body().getStatus().equals("4")){
                        callBack.success(response.body().getMsg());
                    }else if(response.body().getStatus().equals("5")){
                        callBack.success(response.body().getMsg());
                    }else if(response.body().getStatus().equals("6")){
                        callBack.success(response.body().getMsg());
                    }else if(response.body().getStatus().equals("7")){
                        callBack.success(response.body().getMsg());
                    }else if(response.body().getStatus().equals("8")){
                        callBack.success(response.body().getMsg());
                    } else if(response.body().getStatus().equals("0")){
                        callBack.success(response.body().getMsg());
                    }
                }
                else {
                    callBack.error(new Exception("保存失败"));
                }
            }

            @Override
            public ProtectData convertResponse(okhttp3.Response response) throws Throwable {
                return new Gson().fromJson(response.body().string(), ProtectData.class);
            }
            @Override
            public void onError(Response<ProtectData> response) {
                super.onError(response);
                callBack.error((Exception) response.getException());


            }
        });
    }

    public void updateData(String id, String StoreroomCode, String StoreroomName, String ProtectType, String ProtectTime, String ProtectResult, String OperUser , final ProtectDataCallBack callBack){
        Api.updateprotectinfo(id, StoreroomCode, StoreroomName, ProtectType, ProtectTime, ProtectResult, OperUser, new DialogCallBack<ProtectData>(activity) {
            @Override
            public void onSuccess(Response<ProtectData> response) {
                if(response.body()!=null){
                    if(response.body().getStatus().equals("1")) {
                        callBack.success(response.body().getMsg());
                    }else if(response.body().getStatus().equals("2")){
                        callBack.success(response.body().getMsg());
                    }else if(response.body().getStatus().equals("3")){
                        callBack.success(response.body().getMsg());
                    }else if(response.body().getStatus().equals("4")){
                        callBack.success(response.body().getMsg());
                    }else if(response.body().getStatus().equals("5")){
                        callBack.success(response.body().getMsg());
                    }else if(response.body().getStatus().equals("6")){
                        callBack.success(response.body().getMsg());
                    }else if(response.body().getStatus().equals("7")){
                        callBack.success(response.body().getMsg());
                    }else if(response.body().getStatus().equals("8")){
                        callBack.success(response.body().getMsg());
                    } else if(response.body().getStatus().equals("0")){
                        callBack.success(response.body().getMsg());
                    }
                }
                else {
                    callBack.error(new Exception("修改失败"));
                }
            }

            @Override
            public ProtectData convertResponse(okhttp3.Response response) throws Throwable {
                return new Gson().fromJson(response.body().string(), ProtectData.class);
            }
            @Override
            public void onError(Response<ProtectData> response) {
                super.onError(response);
                callBack.error((Exception) response.getException());


            }
        });
    }

    public void searchData(String pageNum, String mLimits,String StoreroomCode, String StoreroomName, String ProtectType, String ProtectTime, String ProtectResult, String OperUser,final ProtectDataCallBack callBack){
        Api.searchprotectinfo(pageNum, mLimits,StoreroomCode, StoreroomName, ProtectType, ProtectTime, ProtectResult, OperUser, new DialogCallBack<ProtectData>(activity) {
            @Override
            public void onSuccess(Response<ProtectData> response) {
                if(response.body()!=null&&response.body().getStatus().equals("1")){
                    callBack.success(response.body().getData(),response.body().getTotal(),response.body().getPageNum());
                }
                else {
                    callBack.error(new Exception("查询失败"));
                }
            }

            @Override
            public ProtectData convertResponse(okhttp3.Response response) throws Throwable {
                return new Gson().fromJson(response.body().string(), ProtectData.class);
            }
            @Override
            public void onError(Response<ProtectData> response) {
                super.onError(response);
                callBack.error((Exception) response.getException());


            }
        });
    }
}
