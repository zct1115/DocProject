package com.ckkj.docproject.contract;



import com.ckkj.docproject.base.BasePresenter;
import  com.ckkj.docproject.base.BaseView;

/**
 * Created by XISEVEN on 2017/5/22.
 */

public interface LoginContract{
    int STATUS_ERROR = 2;
    int STATUS_SUCCESS = 1;


    interface View extends BaseView {
        /**
         * 登录成功
         */
        void success();

        /**
         * 登录失败
         */
        void fail(String msg);
    }

    interface Presenter extends BasePresenter {
        /**
         * 登录
         * @param username
         * @param password
         */
        void login(String username, String password);

        /**
         * 是否开启自动登录
         * @param state
         */
        void autoLogin(boolean state);

        /**
         * 是否开启记住密码
         * @param state
         */
        void rememberPsd(boolean state);
        /**
         * 登出
         */
        void logout();

    }

    interface Model {
    }


}