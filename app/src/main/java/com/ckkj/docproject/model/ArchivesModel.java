package com.ckkj.docproject.model;

import android.app.Activity;
import android.util.Log;

import com.google.gson.Gson;

import  com.ckkj.docproject.bean.ArchivesCatalogue;
import  com.ckkj.docproject.bean.ArchivesData;
import  com.ckkj.docproject.callback.ArchivesDataCallBack;
import  com.ckkj.docproject.callback.DialogCallBack;
import  com.ckkj.docproject.callback.TreeDataCallBack;
import  com.ckkj.docproject.contract.ArchivesContract;
import  com.ckkj.docproject.http.Api;
import okhttp3.Response;


/**
 * Created by XISEVEN on 2017/5/23.
 */

public class ArchivesModel implements ArchivesContract.Model {
    private Activity mActivity;

    public ArchivesModel(Activity activity) {
        mActivity = activity;
    }

    public void getTreeData(final TreeDataCallBack callBack) {
        Api.findCatalogue(new DialogCallBack<ArchivesCatalogue>(mActivity) {
            @Override
            public ArchivesCatalogue convertResponse(Response response) throws Throwable {
                return new Gson().fromJson(response.body().string(), ArchivesCatalogue.class);
            }

            @Override
            public void onSuccess(com.lzy.okgo.model.Response<ArchivesCatalogue> response) {
                if (response.body() != null && response.body().getStatus() == 1) {
                    callBack.success(response.body().getData());
                } else {
                    callBack.error(new Exception("数据获取失败"));
                }
            }

            @Override
            public void onError(com.lzy.okgo.model.Response<ArchivesCatalogue> response) {
                super.onError(response);
                callBack.error((Exception) response.getException());
            }


        });
    }

//    private String getBytesFromInputStream(InputStream inputStream) throws IOException {
//        try {
//            byte[] buffer = new byte[8192];
//            int bytesRead;
//            ByteArrayOutputStream output = new ByteArrayOutputStream();
//            while ((bytesRead = inputStream.read(buffer)) != -1) {
//                output.write(buffer, 0, bytesRead);
//            }
//            return output.toString();
//        } catch (OutOfMemoryError error) {
//            return null;
//        }
//    }

    public void getArchivesData(String tableName, String catalogueId,String pageNum, String limits, final ArchivesDataCallBack callBack) {
        Api.findDatabyCatalogue(tableName, catalogueId ,pageNum, limits, new DialogCallBack<ArchivesData>(mActivity) {
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
