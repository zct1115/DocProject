package com.ckkj.docproject.callback;



import java.util.List;

import  com.ckkj.docproject.bean.ArchivesData;

/**
 * Created by XISEVEN on 2017/6/3.
 */

public interface ArchivesDataCallBack {
    void success(List<ArchivesData.DataBean> data, int total, int pageNum);

    void error(Exception e);
}
