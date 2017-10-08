package com.ckkj.docproject.callback;


import  com.ckkj.docproject.bean.User;

/**
 * Created by XISEVEN on 2017/5/27.
 */

public interface LoginCallBack {
    void success(User user);

    void error(Exception e);
}
