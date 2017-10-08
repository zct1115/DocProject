package com.ckkj.docproject.callback;



import java.util.List;

import  com.ckkj.docproject.bean.ArchivesAttachment;
import com.ckkj.docproject.bean.ArchivesDetail;
import com.ckkj.docproject.bean.DownLoadData;

/**
 * Created by XISEVEN on 2017/6/3.
 */

public interface AttachmentCallBack {
    void success(DownLoadData downLoadData);

    void error(Exception e);
}
