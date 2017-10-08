package com.ckkj.docproject.callback;

import com.ckkj.docproject.bean.ArchivesAttachment;

import java.util.List;

/**
 * Created by XISEVEN on 2017/6/3.
 */

public interface ArchivesDetailCallBack {

    /*检索数据详细内容*/
    void success(List<String> data);


    /*检索下载文件*/
    void download(List<ArchivesAttachment.DataBean> data);

    void error(Exception e);
}
