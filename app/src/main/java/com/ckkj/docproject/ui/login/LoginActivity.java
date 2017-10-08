package com.ckkj.docproject.ui.login;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v8.renderscript.Allocation;
import android.support.v8.renderscript.Element;
import android.support.v8.renderscript.RenderScript;
import android.support.v8.renderscript.ScriptIntrinsicBlur;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.ckkj.docproject.R;
import com.ckkj.docproject.base.BaseActivity;
import com.ckkj.docproject.contract.LoginContract;
import com.ckkj.docproject.http.Urls;
import com.ckkj.docproject.presenter.LoginPresenter;
import com.ckkj.docproject.ui.home.MainActivity;
import com.ckkj.docproject.utils.SPUtils;
import com.zhy.m.permission.MPermissions;
import com.zhy.m.permission.PermissionDenied;
import com.zhy.m.permission.PermissionGrant;

import butterknife.BindView;

/**
 * Created by XISEVEN on 2017/5/22.
 */

public class LoginActivity extends BaseActivity<LoginContract.Presenter> implements LoginContract.View {
    private static final int REQUECT_CODE_SDCARD = 2;
    @BindView(R.id.username)
    EditText usernameText;
    @BindView(R.id.password)
    EditText passwordText;
    @BindView(R.id.usernameLayout)
    TextInputLayout usernameLayout;
    @BindView(R.id.passwordLayout)
    TextInputLayout passwordLayout;
    @BindView(R.id.login_button)
    Button loginButton;
    @BindView(R.id.login_auto)
    CheckBox loginAutoCB;
    @BindView(R.id.login_remember_psd)
    CheckBox loginRememberPsdCB;
    @BindView(R.id.login_form)
    ScrollView mScrollView;
    private boolean mAutoState = true;
    private EditText baseUrlEt;
    private EditText passwordEt;
    private AlertDialog.Builder mBuilder;
    private int radius = 25;

    private Context mContext;

    @Override
    public LoginContract.Presenter getPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initAllMembers(Bundle savedInstanceState) {
        mContext=this;
        Intent intent = getIntent();
        mAutoState = intent.getBooleanExtra("autoLogin", true);//activity接收boolean值
        Log.d("LoginActivity", "mAutoState:" + mAutoState);
        initAutoLogin();
        initRememberPsd();
        initCheckListener();
        initLoginListener();
        initPermission();
        //applyBlur();
    }
    private void applyBlur() {
        Drawable db = getResources().getDrawable(R.drawable.login);
        BitmapDrawable drawable = (BitmapDrawable) db;
        Bitmap bgBitmap = drawable.getBitmap();
        //处理得到模糊效果的图
        RenderScript renderScript = RenderScript.create(mContext);
        // Allocate memory for Renderscript to work with
        final Allocation input = Allocation.createFromBitmap(renderScript, bgBitmap);
        final Allocation output = Allocation.createTyped(renderScript, input.getType());
        // Load up an instance of the specific script that we want to use.
        ScriptIntrinsicBlur scriptIntrinsicBlur = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript));
        scriptIntrinsicBlur.setInput(input);
        // Set the blur radius
        scriptIntrinsicBlur.setRadius(radius);
        // Start the ScriptIntrinisicBlur
        scriptIntrinsicBlur.forEach(output);
        // Copy the output to the blurred bitmap
        output.copyTo(bgBitmap);
        renderScript.destroy();
        BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bgBitmap);
        mScrollView.setBackground(bitmapDrawable);

    }


    private void initPermission() {
        MPermissions.requestPermissions(LoginActivity.this, REQUECT_CODE_SDCARD, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    private void initAutoLogin() {
        boolean autoLogin = (boolean) SPUtils.getParam(this, "autoLogin", false);
        Log.d("LoginActivity", "autoLogin:" + autoLogin);
        loginAutoCB.setChecked(autoLogin);
        if (mAutoState && autoLogin) {
            String username = (String) SPUtils.getParam(this, "username", "");
            String password = (String) SPUtils.getParam(this, "password", "");
            mPresenter.login(username, password);
            mPresenter.autoLogin(loginAutoCB.isChecked());
            mPresenter.rememberPsd(loginRememberPsdCB.isChecked());
        }
    }

    private void initRememberPsd() {
        boolean rememberPsd = (boolean) SPUtils.getParam(this, "rememberPsd", false);
        loginRememberPsdCB.setChecked(rememberPsd);
        if (rememberPsd) {
            String username = (String) SPUtils.getParam(this, "username", "");
            String password = (String) SPUtils.getParam(this, "password", "");
            usernameText.setText(username);
            passwordText.setText(password);
        }
    }


    private void initLoginListener() {

        /*
        * 设置软键盘
        * */
        passwordText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login_button || id == EditorInfo.IME_ACTION_DONE) {
                    login();
                    return true;
                }
                return false;
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    private void initCheckListener() {
        loginAutoCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    loginRememberPsdCB.setChecked(true);
                }
            }
        });
        loginRememberPsdCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    loginAutoCB.setChecked(false);
                }
            }
        });
    }

    private void login() {
        usernameLayout.setErrorEnabled(false);
        passwordLayout.setErrorEnabled(false);
        String username = usernameText.getText().toString();
        String password = passwordText.getText().toString();

        if (setBaseUrl(username, password))
            return;

        if (TextUtils.isEmpty(username)) {
            usernameLayout.setError("请输入用户名");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            passwordLayout.setError("请输入密码");
            return;
        }
        mPresenter.login(username, password);
        Log.d("LoginActivity===usename", username);
        Log.d("LoginActivity===passwor", password);
        mPresenter.autoLogin(loginAutoCB.isChecked());
        mPresenter.rememberPsd(loginRememberPsdCB.isChecked());
    }

    private boolean setBaseUrl(final String username, String password) {
        if ("seturl".equals(username) && "".equals(password)) {
            View view = LayoutInflater.from(LoginActivity.this).inflate(R.layout.view_set_url, null);
            baseUrlEt = (EditText) view.findViewById(R.id.baseurl_et);
            passwordEt = (EditText) view.findViewById(R.id.password_et);
            baseUrlEt.setText(Urls.sBase_url);
            mBuilder = new AlertDialog.Builder(LoginActivity.this);
            mBuilder.setTitle("修改网络请求地址")
                    .setView(view)
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (passwordEt.getText().toString().equals("gdjl123456789")) {
                                SPUtils.setParam(LoginActivity.this, "BASE_URL", baseUrlEt.getText().toString());
                                Urls.sBase_url = baseUrlEt.getText().toString();
                                showSnackBar((View) mScrollView.getParent(),"修改成功");
                                usernameText.setText("");
                                usernameText.hasFocus();
                            } else {
                                showSnackBar((View) mScrollView.getParent(),"密钥错误，请重试！");
                            }
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    }).show();
            return true;
        }
        return false;
    }


    @Override
    public void success() {
        startActivity(new Intent(mActivity, MainActivity.class));
        showToast("登录成功");
        finish();
    }

    @Override
    public void fail(String msg) {
        showSnackBar(loginButton, msg, "OK");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        MPermissions.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    @PermissionGrant(REQUECT_CODE_SDCARD)
    public void requestSdcardSuccess() {
//        Toast.makeText(this, "GRANT ACCESS SDCARD!", Toast.LENGTH_SHORT).show();
    }

    @PermissionDenied(REQUECT_CODE_SDCARD)
    public void requestSdcardFailed() {
//        Toast.makeText(this, "DENY ACCESS SDCARD!", Toast.LENGTH_SHORT).show();
        new AlertDialog.Builder(this).setTitle("获取" + "存储空间" + "权限被禁用")
                .setMessage("请在 设置-应用管理-" + this.getString(R.string.app_name) + "-权限管理 (将" + "存储空间" + "权限打开)")
                .setNegativeButton("取消", null)
                .setPositiveButton("去设置", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        intent.setData(Uri.parse("package:" + LoginActivity.this.getPackageName()));
                        LoginActivity.this.startActivity(intent);
                    }
                })
                .show();
    }

}
