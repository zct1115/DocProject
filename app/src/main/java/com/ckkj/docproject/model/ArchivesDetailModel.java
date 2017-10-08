package com.ckkj.docproject.model;

import android.app.Activity;
import android.util.Log;


import com.ckkj.docproject.bean.DownLoadData;
import com.ckkj.docproject.callback.AttachmentCallBack;
import com.ckkj.docproject.http.Urls;
import com.ckkj.docproject.utils.StringUtils;
import com.google.gson.Gson;
import com.lzy.okgo.callback.AbsCallback;

import  com.ckkj.docproject.bean.ArchivesAttachment;
import  com.ckkj.docproject.bean.ArchivesDetail;
import  com.ckkj.docproject.callback.ArchivesDetailCallBack;
import  com.ckkj.docproject.callback.DialogCallBack;
import  com.ckkj.docproject.contract.ArchivesDetailContract;
import  com.ckkj.docproject.http.Api;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Response;

/**
 * Created by XISEVEN on 2017/6/3.
 */

public class ArchivesDetailModel implements ArchivesDetailContract.Model {
    private Activity mActivity;

    private String tablName;

    public ArchivesDetailModel(Activity activity) {
        mActivity = activity;
    }

    //获取档案详情
    public void getArchivesDetail(String id, final String tableName, final ArchivesDetailCallBack callBack) {
        this.tablName=tableName;
        Api.findDatabyID(id, tableName, new DialogCallBack<ArchivesDetail>(mActivity) {
            @Override
            public ArchivesDetail convertResponse(Response response) throws Throwable {
                return new Gson().fromJson(response.body().string(), ArchivesDetail.class);
            }

            @Override
            public void onSuccess(com.lzy.okgo.model.Response<ArchivesDetail> response) {

                if (response.body() != null ) {
                    callBack.success(response.body().getData());

                    /*
                    * 获取Electron_wenjian文件分割的字符串
                    * */
                     if(response.body().getElectron_wenjian()!=null) {
                         String filenames = response.body().getElectron_wenjian();
                         List<ArchivesAttachment.DataBean> dataBeenlist = new ArrayList<ArchivesAttachment.DataBean>();

                    /*
                    * 文件分割
                    * */
                         String[] files = StringUtils.changesize(filenames);
                         String url = Urls.sBase_url + "app/appController/download?tableName=" + tableName + "&electron_wenjian=";
                         for (int i = 0; i < files.length; i++) {
                             ArchivesAttachment.DataBean dataBean = new ArchivesAttachment.DataBean();
                             dataBean.setName(files[i]);
                             dataBean.setPath(url + files[i]);
                             dataBeenlist.add(dataBean);
                             Log.d("ArchivesDetailModel", files[i]);
                         }

                         callBack.download(dataBeenlist);
                     }



                } else {
                    callBack.error(new Exception("数据获取失败"));
                }
            }

            @Override
            public void onError(com.lzy.okgo.model.Response<ArchivesDetail> response) {
                super.onError(response);
                callBack.error((Exception) response.getException());

            }


        });
    }

    public void getfileUrl(String path, final AttachmentCallBack callBack){
        Api.downloadfile(path, new DialogCallBack<DownLoadData>(mActivity) {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<DownLoadData> response) {
                if (response.body() != null && Integer.parseInt(response.body().getStatus()) == 1) {
                    callBack.success(response.body());
                } else {
                    callBack.error(new Exception("数据获取失败"));
                }
            }

            @Override
            public DownLoadData convertResponse(Response response) throws Throwable {
                return new Gson().fromJson(response.body().string(), DownLoadData.class);

            }

            @Override
            public void onError(com.lzy.okgo.model.Response<DownLoadData> response) {
                super.onError(response);
                callBack.error((Exception) response.getException());

            }
        });
    }


}
