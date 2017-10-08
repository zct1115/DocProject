package com.ckkj.docproject.model;

import android.app.Activity;

import com.google.gson.Gson;
import com.lzy.okgo.callback.AbsCallback;

import com.ckkj.docproject.bean.User;
import com.ckkj.docproject.callback.DialogCallBack;
import com.ckkj.docproject.callback.LoginCallBack;
import com.ckkj.docproject.contract.LoginContract;
import com.ckkj.docproject.http.Api;

import okhttp3.Response;

/**
 * Created by XISEVEN on 2017/05/22
 */

public class LoginModel implements LoginContract.Model {
    private Activity mActivity;

    public LoginModel(Activity activity) {
        mActivity = activity;
    }

    public void login(final String username, final String password, final LoginCallBack callBack) {
        Api.login(username, password, new DialogCallBack<User>(mActivity) {
            @Override
            public User convertResponse(Response response) throws Throwable {
                return new Gson().fromJson(response.body().string(), User.class);
            }

            @Override
            public void onSuccess(com.lzy.okgo.model.Response<User> response) {
                callBack.success(response.body());
            }

            @Override
            public void onError(com.lzy.okgo.model.Response<User> response) {
                super.onError(response);
                callBack.error((Exception) response.getException());

            }


        });

    }

    public void logout() {
        Api.logout(new AbsCallback<User>() {
            @Override
            public User convertResponse(Response response) throws Throwable {
                return new Gson().fromJson(response.body().string(), User.class);
            }

            @Override
            public void onSuccess(com.lzy.okgo.model.Response<User> response) {

            }
        });
    }

}