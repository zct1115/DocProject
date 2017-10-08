package com.ckkj.docproject.presenter;

import android.app.Activity;
import android.content.Context;

import  com.ckkj.docproject.bean.User;
import  com.ckkj.docproject.callback.LoginCallBack;
import  com.ckkj.docproject.contract.LoginContract;
import  com.ckkj.docproject.model.LoginModel;
import  com.ckkj.docproject.utils.NetUtils;
import  com.ckkj.docproject.utils.SPUtils;

/**
* Created by XISEVEN on 2017/05/22
*/

public class LoginPresenter implements LoginContract.Presenter{
    private LoginContract.View mView;
    private Context mContext;
    private LoginModel mLoginModel;
    private String username;
    private String password;
    public LoginPresenter(LoginContract.View view) {
        mContext = (Context) view;
        mView = view;
        mLoginModel = new LoginModel((Activity) mView);
    }


    @Override
    public void login(final String username, final String password) {
        this.username = username;
        this.password = password;
        mLoginModel.login(username, password, new LoginCallBack() {
            @Override
            public void success(User user) {
                if (LoginContract.STATUS_SUCCESS==(Integer.parseInt(user.getStatus())) ) {
                    mView.success();
                } else{
                    mView.fail(user.getMsg());
                }
            }
            @Override
            public void error(Exception e) {
                if (!NetUtils.hasNetWorkConection(mContext)) {
                    mView.fail("当前无网络，请保持网络连接！");
                } else {
                    mView.fail("网络出错！！");
                }
            }
        });

    }

    @Override
    public void autoLogin(boolean state) {
        SPUtils.setParam(mContext, "autoLogin", state);
    }

    @Override
    public void rememberPsd(boolean state) {
        SPUtils.setParam(mContext, "rememberPsd", state);
        if (state) {
            SPUtils.setParam(mContext, "username", username);
            SPUtils.setParam(mContext, "password", password);
        }
    }

    @Override
    public void logout() {
        new LoginModel((Activity) mView).logout();
    }

    @Override
    public void init() {

    }
}