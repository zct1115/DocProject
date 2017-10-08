package com.ckkj.docproject.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import  com.ckkj.docproject.R;
import  com.ckkj.docproject.bean.ArchivesAttachment;
import com.ckkj.docproject.bean.ArchivesDetail;
import com.ckkj.docproject.bean.DownLoadData;
import  com.ckkj.docproject.callback.ArchivesDetailCallBack;
import  com.ckkj.docproject.callback.AttachmentCallBack;
import  com.ckkj.docproject.contract.ArchivesDetailContract;
import  com.ckkj.docproject.model.ArchivesDetailModel;
import  com.ckkj.docproject.model.DownLoadModel;
import  com.ckkj.docproject.utils.DensityUtils;
import  com.ckkj.docproject.utils.NetUtils;
import com.ckkj.docproject.utils.StringUtils;

/**
 * Created by XISEVEN on 2017/6/3.
 */

public class ArchivesDetailPresenter implements ArchivesDetailContract.Presenter {
    private ArchivesDetailContract.View mView;
    private ArchivesDetailModel mModel;
    private String tableName;
    private int notificationId = 1000;

    public ArchivesDetailPresenter(ArchivesDetailContract.View view) {
        mView = view;
        mModel = new ArchivesDetailModel((Activity) mView);
    }

    @Override
    public void init() {

    }

    public void getArchivesDetail(String id, final String tableName) {
        this.tableName = tableName;
        mModel.getArchivesDetail(id, tableName, new ArchivesDetailCallBack() {
            @Override
            public void success(List<String> data) {

                mView.setArchivesDetail(analysisArchivesDetailData(data));
            }

            @Override
            public void download(List<ArchivesAttachment.DataBean> data) {
                mView.setAttachment(analysisArchivesAttachment(data));
            }


            @Override
            public void error(Exception e) {
                if (!NetUtils.hasNetWorkConection((Context) mView)) {
                    mView.getDataFail("当前无网络，请保持网络连接！");
                } else {
                    mView.getDataFail(e.getMessage());
                }
            }
        });
    }



    private View analysisArchivesDetailData(List<String> data) {
        LinearLayout layout = new LinearLayout((Context) mView);
        layout.setPadding(30,50,30,50);
        layout.setOrientation(LinearLayout.VERTICAL);
        for (String s : data) {
            View v = LayoutInflater.from((Context) mView).inflate(R.layout.item_archivesdetail, null, false);
            String[] split = s.split(":");
            TextView tvKey = (TextView) v.findViewById(R.id.key);
            TextView tvValue = (TextView) v.findViewById(R.id.value);
            tvKey.setText(split[0]);
            tvValue.setText(split[1]);
            layout.addView(v);
        }
        return layout;
    }

    private View analysisArchivesAttachment(final List<ArchivesAttachment.DataBean> data) {
        View view = LayoutInflater.from((Context) mView).inflate(R.layout.attachment_list, null, false);
        LinearLayout layout = (LinearLayout) view.findViewById(R.id.attachment_list_layout);
        for (final ArchivesAttachment.DataBean dataBean : data) {
            TextView textView = new TextView((Context) mView);
            textView.setText(dataBean.getName());
            textView.setTag(dataBean.getPath());
            textView.setTextSize(18);
            textView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        v.setAlpha(0.5f);
                    } else if (event.getAction() == MotionEvent.ACTION_UP) {
                        v.setAlpha(1.0f);
                        notificationId += 1;
                        downLoadClick(v, dataBean);
                    }
                    return true;
                }
            });
            textView.setPadding(0, DensityUtils.dip2px((Context) mView, 20), 0, 0);
            layout.addView(textView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        }
        return view;
    }

    private void downLoadClick(final View v, final ArchivesAttachment.DataBean dataBean) {
        new AlertDialog.Builder(v.getContext())
                .setTitle("确认下载")
                .setMessage(dataBean.getName())
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                               getfileUrl(dataBean.getPath(),dataBean,v);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).show();
    }

    public void getfileUrl(final String path, final  ArchivesAttachment.DataBean dataBean,final View v){
         mModel.getfileUrl(path, new AttachmentCallBack() {
             @Override
             public void success(DownLoadData downLoadData) {
                 new DownLoadModel(notificationId).downLoad(downLoadData.getUrl(), tableName, dataBean.getName());
                 new AlertDialog.Builder(v.getContext())
                         .setTitle("正在为你下载，返回下载页面查看")
                         .setMessage(dataBean.getName())
                         .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                             @Override
                             public void onClick(DialogInterface dialog, int which) {

                             }
                         })
                         .show();
             }
             @Override
             public void error(Exception e) {
                 if (!NetUtils.hasNetWorkConection((Context) mView)) {
                     mView.getDataFail("当前无网络，请保持网络连接！");
                 } else {
                     mView.getDataFail(e.getMessage());
                 }
             }
         });
    }

}
