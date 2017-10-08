package com.ckkj.docproject.callback.examine;





import com.ckkj.docproject.bean.examine.Preprocess;

import java.util.List;

/**
 * Created by zct11 on 2017/8/9.
 */

public interface PreprocessCallBack {
    void success(List<Preprocess.DataBean> data, int total, int pageNum);

    void failure(String msg);

    void error(Exception e);

    void success(String status);
}
