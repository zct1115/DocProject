package com.ckkj.docproject.callback.cupboard;



import com.ckkj.docproject.bean.cupboard.ProtectData;

import java.util.List;

/**
 * Created by zct11 on 2017/8/9.
 */

public interface ProtectDataCallBack {
    void success(List<ProtectData.DataBean> data, int total, int pageNum);

    void success(String msg);

    void error(Exception e);
}
