package com.ckkj.docproject.model;

import android.app.Activity;
import android.util.Log;

import com.google.gson.Gson;

import  com.ckkj.docproject.bean.ArchivesData;
import  com.ckkj.docproject.callback.ArchivesDataCallBack;
import  com.ckkj.docproject.callback.DialogCallBack;
import  com.ckkj.docproject.http.Api;
import okhttp3.Response;

/**
* Created by XISEVEN on 2017/05/22
*/

public class SearchModel{
    private Activity mActivity;

    public SearchModel(Activity activity) {
        mActivity = activity;
    }

    public void search(String tableName, String pageNum, String limits, String searchCondition, final ArchivesDataCallBack callBack) {
        Api.search(tableName, Integer.parseInt(pageNum),Integer.parseInt( limits), searchCondition, new DialogCallBack<ArchivesData>(mActivity) {
            @Override
            public ArchivesData convertResponse(Response response) throws Throwable {
                return new Gson().fromJson(response.body().string(), ArchivesData.class);
            }

            @Override
            public void onSuccess(com.lzy.okgo.model.Response<ArchivesData> response) {
                if (response.body() != null && Integer.parseInt(response.body().getStatus()) == 1) {
                    callBack.success(response.body().getData(), response.body().getTotal(),response.body().getPageNum());
                } else {
                    callBack.error(new Exception("数据获取失败"));

                }
            }

            @Override
            public void onError(com.lzy.okgo.model.Response<ArchivesData> response) {
                super.onError(response);
                callBack.error((Exception) response.getException());
            }
        });
    }
}