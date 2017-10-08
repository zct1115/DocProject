package com.ckkj.docproject.contract;


import  com.ckkj.docproject.base.BasePresenter;

/**
 * Created by XISEVEN on 2017/6/3.
 */

public interface ArchivesDetailContract {
    interface Model {
    }

    interface View {
        void setArchivesDetail(android.view.View view);

        void setAttachment(android.view.View view);

        void getDataFail(String msg);
    }

    interface Presenter extends BasePresenter {
        void getArchivesDetail(String id, String tableName);



    }
}
