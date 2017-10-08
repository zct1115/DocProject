package com.ckkj.docproject.callback;



import java.util.List;

import  com.ckkj.docproject.bean.ArchivesCatalogue;

/**
 * Created by XISEVEN on 2017/5/27.
 */

public interface TreeDataCallBack {
    void success(List<ArchivesCatalogue.DataBean> list);

    void error(Exception e);
}
