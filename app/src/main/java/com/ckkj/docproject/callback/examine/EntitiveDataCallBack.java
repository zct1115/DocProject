package com.ckkj.docproject.callback.examine;





import com.ckkj.docproject.bean.examine.Entitive;

import java.util.List;

/**
 * Created by zct11 on 2017/8/9.
 */

public interface EntitiveDataCallBack {
    void success(List<Entitive.DataBean> data, int total, int pageNum);

    void failure(String msg);

    void error(Exception e);

    void success(String status);
}
