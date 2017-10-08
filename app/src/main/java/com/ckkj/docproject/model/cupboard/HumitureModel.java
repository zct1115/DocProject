package com.ckkj.docproject.model.cupboard;

import android.app.Activity;
import android.util.Log;

import com.ckkj.docproject.bean.cupboard.HumitureData;
import com.ckkj.docproject.callback.DialogCallBack;
import com.ckkj.docproject.callback.cupboard.HumitureDataCallBack;
import com.ckkj.docproject.contract.HumitureContract;
import com.ckkj.docproject.http.Api;
import com.google.gson.Gson;
import com.lzy.okgo.model.Response;

/**
 * Created by zct11 on 2017/8/9.
 */

public class HumitureModel implements HumitureContract.Model {

    private Activity activity;

    public HumitureModel(Activity activity){
        this.activity=activity;
    }

    public void getData(String pageNum,String limits,final HumitureDataCallBack callBack){
        Api.humitureinfo(pageNum,limits,new DialogCallBack<HumitureData>(activity) {
            @Override
            public void onSuccess(Response<HumitureData> response) {
                if(response.body()!=null){
                    if(response.body().getStatus().equals("1")) {
                        callBack.success(response.body().getData(),response.body().getTotal(),response.body().getPageNum());
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
                    } else if(response.body().getStatus().equals("0")){
                        callBack.success(response.body().getMsg());
                    }
                }
                else {
                    callBack.error(new Exception("获取数据失败"));
                }
            }

            @Override
            public HumitureData convertResponse(okhttp3.Response response) throws Throwable {
                return new Gson().fromJson(response.body().string(), HumitureData.class);
            }

            @Override
            public void onError(Response<HumitureData> response) {
                super.onError(response);
                callBack.error((Exception) response.getException());


            }
        });
    }

    public void addData(String checkData, String AmTemp, String AmHumidity, String PmTemp, String PmHumidity, String inTemp, String inHumidity, String outTemp, String outHumidity, String Taken, String custodian, String remark, final HumitureDataCallBack callBack) {
        Api.addhumitureinfo(checkData, AmTemp, AmHumidity, PmTemp, PmHumidity, inTemp, inHumidity, outTemp, outHumidity, Taken, custodian, remark, new DialogCallBack<HumitureData>(activity) {
            @Override
            public void onSuccess(Response<HumitureData> response) {
                if(response.body()!=null){
                    if(response.body().getStatus().equals("1")) {
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
                    }else if(response.body().getStatus().equals("8")){
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
            public HumitureData convertResponse(okhttp3.Response response) throws Throwable {
                return new Gson().fromJson(response.body().string(), HumitureData.class);
            }
            @Override
            public void onError(Response<HumitureData> response) {
                super.onError(response);
                callBack.error((Exception) response.getException());


            }
        });
    }

    public void updateData(String id, String checkData, String AmTemp, String AmHumidity, String PmTemp, String PmHumidity, String inTemp, String inHumidity, String outTemp, String outHumidity, String Taken, String custodian, String remark, final HumitureDataCallBack callBack) {
        Api.updatehumitureinfo(id, checkData, AmTemp, AmHumidity, PmTemp, PmHumidity, inTemp, inHumidity, outTemp, outHumidity, Taken, custodian, remark, new DialogCallBack<HumitureData>(activity) {
            @Override
            public void onSuccess(Response<HumitureData> response) {
                if(response.body()!=null){
                    Log.d("HumitureModel", response.body().getMsg());
                    if(response.body().getStatus().equals("1")) {
                        callBack.success(response.body().getMsg());
                    }
                }
                else {
                    callBack.error(new Exception("修改失败"));
                }
            }

            @Override
            public HumitureData convertResponse(okhttp3.Response response) throws Throwable {
                return new Gson().fromJson(response.body().string(), HumitureData.class);
            }
            @Override
            public void onError(Response<HumitureData> response) {
                super.onError(response);
                callBack.error((Exception) response.getException());


            }
        });
    }

    public void searchData(String checkData, String AmTemp, String AmHumidity,String PmTemp,String PmHumidity, String inTemp,String inHumidity, String outTemp, String outHumidity,String Taken, String custodian, String remark,final HumitureDataCallBack callBack){
        Api.searchhumitureinfo( checkData,  AmTemp, AmHumidity,PmTemp,PmHumidity,inTemp,inHumidity,outTemp, outHumidity,Taken, custodian, remark, new DialogCallBack<HumitureData>(activity) {
            @Override
            public void onSuccess(Response<HumitureData> response) {
                if(response.body()!=null&&response.body().getStatus().equals("1")){
                        callBack.success(response.body().getData(),response.body().getTotal(),response.body().getPageNum());
                }
                else {
                    callBack.error(new Exception("查询失败"));
                }
            }

            @Override
            public HumitureData convertResponse(okhttp3.Response response) throws Throwable {
                return new Gson().fromJson(response.body().string(), HumitureData.class);
            }
            @Override
            public void onError(Response<HumitureData> response) {
                super.onError(response);
                callBack.error((Exception) response.getException());


            }
        });
    }
}
