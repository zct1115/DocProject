package com.ckkj.docproject.contract;



import java.util.List;

import com.ckkj.docproject.base.BasePresenter;
import  com.ckkj.docproject.bean.ArchivesData;

/**
 * Created by XISEVEN on 2017/5/23.
 */

public interface ArchivesContract {
    interface Model {
    }

    interface View {
        /**
         * 设置TreeView数据
         * @param view
         */
        void setTreeViewData(android.view.View view);

        /**
         * 设置档案数据
         * @param data
         */
        void setArchivesData(List<ArchivesData.DataBean> data, int total, int pageNum);

        void getArchivesDataFail(String msg);

    }

    interface Presenter extends BasePresenter {
        /**
         * 获取侧边栏TreeView的内容
         */
        void getTreeViewData();

        /**
         * 获取档案数据
         */
        void getArchivesData(String tableName, String catalogueId, String starts, String limits);

        /**
         * 提交查询
         *
         * @param tableName
         * @param starts
         * @param limits
         * @param searchCondition
         */
        void submitQuery(String tableName, String starts, String limits, String searchCondition);


    }
}
