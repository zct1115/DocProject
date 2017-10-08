package com.ckkj.docproject.model.cupboard;

import android.app.Activity;
import android.util.Log;

import com.ckkj.docproject.bean.cupboard.CupBoardData;
import com.ckkj.docproject.callback.DialogCallBack;
import com.ckkj.docproject.callback.cupboard.CupBoardDataCallBack;
import com.ckkj.docproject.contract.CupBoardContract;
import com.ckkj.docproject.http.Api;
import com.google.gson.Gson;
import com.lzy.okgo.model.Response;

import org.json.JSONArray;

import java.util.List;

/**
 * Created by zct11 on 2017/8/9.
 */

public class CupBoardModel implements CupBoardContract.Model {

    private Activity activity;

    public CupBoardModel(Activity activity){
        this.activity=activity;
    }

    public void getData(String pageNum,String limits,final CupBoardDataCallBack callBack){
        Api.cupboardinfo(pageNum,limits,new DialogCallBack<CupBoardData>(activity) {
            @Override
            public void onSuccess(Response<CupBoardData> response) {
                if(response.body()!=null){
                    if(response.body().getStatus().equals("1")) {
                        callBack.success(response.body().getData(),response.body().getTotal(),response.body().getPageNum());
                    }
                    callBack.success(response.body().getMsg());
                }
                else {
                    callBack.error(new Exception("获取数据失败"));
                }
            }

            @Override
            public CupBoardData convertResponse(okhttp3.Response response) throws Throwable {
                return new Gson().fromJson(response.body().string(), CupBoardData.class);
            }

            @Override
            public void onError(Response<CupBoardData> response) {
                super.onError(response);
                callBack.error((Exception) response.getException());
            }
        });
    }

    public void addData(String name,String code,String location,String storeroomName,String storeroomCode,String remark,final CupBoardDataCallBack callBack){
        Api.addcupboardinfo(name, code, location, storeroomName, storeroomCode, remark, new DialogCallBack<CupBoardData>(activity) {
            @Override
            public void onSuccess(Response<CupBoardData> response) {
                if(response.body()!=null){
                    if(response.body().getStatus().equals("1")) {
                        callBack.success("添加成功");
                    }else{
                        callBack.success(response.body().getMsg());
                    }
                }
                else {
                    callBack.error(new Exception("添加失败"));
                }
            }

            @Override
            public CupBoardData convertResponse(okhttp3.Response response) throws Throwable {
                return new Gson().fromJson(response.body().string(), CupBoardData.class);
            }
            @Override
            public void onError(Response<CupBoardData> response) {
                super.onError(response);
                callBack.error((Exception) response.getException());


            }
        });
    }

    public void updateData(String id,String name,String code,String location,String storeroomName,String storeroomCode,String remark,final CupBoardDataCallBack callBack){
        Api.updatecupboardinfo(id,name, code, location, storeroomName, storeroomCode, remark, new DialogCallBack<CupBoardData>(activity) {
            @Override
            public void onSuccess(Response<CupBoardData> response) {
                if(response.body()!=null){
                    if(response.body().getStatus().equals("1")) {
                        callBack.success(response.body().getMsg());
                    }
                }
                else {
                    callBack.error(new Exception("修改失败"));
                }
            }

            @Override
            public CupBoardData convertResponse(okhttp3.Response response) throws Throwable {
                return new Gson().fromJson(response.body().string(), CupBoardData.class);
            }
            @Override
            public void onError(Response<CupBoardData> response) {
                super.onError(response);
                callBack.error((Exception) response.getException());


            }
        });
    }

    public void searchData(String pageNum,String limits,String Dataname,String Datavalues,final CupBoardDataCallBack callBack){
        Api.searchData(pageNum,limits,Dataname, Datavalues, new DialogCallBack<CupBoardData>(activity) {
            @Override
            public void onSuccess(Response<CupBoardData> response) {
                if(response.body()!=null&&response.body().getStatus().equals("1")){
                    callBack.success(response.body().getData(), response.body().getTotal(),response.body().getPageNum());
                }
                else {
                    callBack.error(new Exception("获取数据失败"));
                }
            }

            @Override
            public CupBoardData convertResponse(okhttp3.Response response) throws Throwable {
                return new Gson().fromJson(response.body().string(), CupBoardData.class);
            }
            @Override
            public void onError(Response<CupBoardData> response) {
                super.onError(response);
                Log.d("CupBoardModel", "response.getException():" + response.getException());
                callBack.error((Exception) response.getException());


            }
        });
    }

    public void offtaken(String cupBoards, CupBoardDataCallBack cupBoardDataCallBack) {
        Api.putTaken(cupBoards, new DialogCallBack<CupBoardData>(activity) {
            @Override
            public CupBoardData convertResponse(okhttp3.Response response) throws Throwable {
                return new Gson().fromJson(response.body().string(), CupBoardData.class);
            }

            @Override
            public void onSuccess(Response<CupBoardData> response) {

            }
        });
    }
}
