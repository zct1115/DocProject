package com.ckkj.docproject.callback.cupboard;



import com.ckkj.docproject.bean.cupboard.CupBoardData;

import java.util.List;

/**
 * Created by zct11 on 2017/8/9.
 */

public interface CupBoardDataCallBack {
    void success(List<CupBoardData.DataBean> data, int total, int pageNum);

    void success(String msg);

    void error(Exception e);
}
