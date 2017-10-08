package com.ckkj.docproject.presenter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import  com.ckkj.docproject.bean.ArchivesCatalogue;
import  com.ckkj.docproject.bean.ArchivesData;
import  com.ckkj.docproject.callback.ArchivesDataCallBack;
import  com.ckkj.docproject.callback.TreeDataCallBack;
import  com.ckkj.docproject.contract.ArchivesContract;
import  com.ckkj.docproject.event.MessageEvent;
import  com.ckkj.docproject.model.ArchivesModel;
import  com.ckkj.docproject.model.LoginModel;
import  com.ckkj.docproject.model.SearchModel;
import  com.ckkj.docproject.utils.NetUtils;
import com.ckkj.docproject.utils.StringUtils;
import  com.ckkj.docproject.view.treeview.MyNodeViewFactory;
import ckkj.mylibrary.treeview.TreeNode;
import ckkj.mylibrary.treeview.TreeView;


/**
 * Created by XISEVEN on 2017/5/23.
 */

public class ArchivesPresenter implements ArchivesContract.Presenter {
    private ArchivesContract.View mView;
    private Context mContext;
    private ArchivesModel mModel;

    public ArchivesPresenter(ArchivesContract.View v) {
        mView = v;
        mContext = (Context) v;
        mModel = new ArchivesModel((Activity) mView);
    }

    @Override
    public void init() {

    }

    @Override
    public void getTreeViewData() {
        mModel.getTreeData(new TreeDataCallBack() {
            @Override
            public void success(List<ArchivesCatalogue.DataBean> list) {
                View view = analysisTreeData(list);
                mView.setTreeViewData(view);
                EventBus.getDefault().post(new MessageEvent(list.get(0).getTableName(), list.get(0).getTableNameCH(),list.get(0).getId()));
            }

            @Override
            public void error(Exception e) {
                mView.getArchivesDataFail(e.getMessage());
            }
        });
    }


    @Override
    public void getArchivesData(String tableName, String catalogueId,String pageNum, String limits) {
        mModel.getArchivesData(tableName, catalogueId,pageNum, limits, new ArchivesDataCallBack() {
            @Override
            public void success(List<ArchivesData.DataBean> data, int total,int pageNum) {
                mView.setArchivesData(data, total,pageNum);
            }

            @Override
            public void error(Exception e) {
                if (!NetUtils.hasNetWorkConection(mContext)) {
                    mView.getArchivesDataFail("当前无网络，请保持网络连接！");
                } else {
                    mView.getArchivesDataFail(e.getMessage());
                }
            }
        });
    }

    @Override
    public void submitQuery(String tableName, String pageNum, String limits, String searchCondition) {
        new SearchModel((Activity) mView).search(tableName, pageNum, limits, searchCondition, new ArchivesDataCallBack() {
            @Override
            public void success(List<ArchivesData.DataBean> data, int total,int pageNum) {
                mView.setArchivesData(data, total,pageNum);
            }



            @Override
            public void error(Exception e) {
                if (!NetUtils.hasNetWorkConection(mContext)) {
                    mView.getArchivesDataFail("当前无网络，请保持网络连接！");
                } else {
                    mView.getArchivesDataFail(e.getMessage());
                }

            }
        });
    }



    private View analysisTreeData(List<ArchivesCatalogue.DataBean> list) {
        //根据level进行排序
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - 1 - i; j++) {
                if (list.get(j).getLevel() > list.get(j + 1).getLevel()) {
                    Collections.swap(list, j, j + 1);
                }
            }
        }
        //解析tree数据对应的父子关系
        List<TreeNode> treeList = new ArrayList<>();
        TreeNode root = TreeNode.root();
        TreeView treeView;
        int i;
        for (ArchivesCatalogue.DataBean bean : list) {
            TreeNode treeNode = new TreeNode(bean);
            treeNode.setLevel(bean.getLevel());
            treeList.add(treeNode);
            for (i = 0; i < list.size(); i++) {
                //说明该treeNode为子节点
                if (bean.getPid().equals(list.get(i).getId()) ){
                    treeList.get(i).addChild(treeNode);
                    break;
                }

            }
            //说明该treeNode没有找到爸爸
            if (i == list.size()) {
                root.addChild(treeNode);
            }
        }


        treeView = new TreeView(root, mContext, new MyNodeViewFactory());
        View view = treeView.getView();
        view.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return view;
    }
}
