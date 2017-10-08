package com.ckkj.docproject.base;

import android.animation.TimeInterpolator;
import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import  com.ckkj.docproject.R;

/**
 * Created by XISEVEN on 2017/5/22.
 */

public abstract class  BaseActivity<T extends BasePresenter> extends AppCompatActivity {
    private Toolbar toolbar;
    private Unbinder mBind;

    /**
     * 子类重写该方法，实现设置布局
     *
     * @return
     */
    public abstract int getContentViewId();

    /**
     * 子类重写该方法，初始化view
     *
     * @param savedInstanceState
     */
    protected abstract void initAllMembers(Bundle savedInstanceState);

    public Activity mActivity;
    protected T mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        mActivity = this;
        mBind = ButterKnife.bind(mActivity);
        mPresenter = getPresenter();
        initAllMembers(savedInstanceState);
    }


    /**
     * 初始化Toolbar
     *
     * @param title toolbar标题
     * @return
     */
    public Toolbar initToolbar(String title) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationIcon(R.drawable.ic_back);
        //toolbar点击返回
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        return toolbar;
    }

    protected T getPresenter() {
        return null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBind.unbind();
    }

    public void showSnackBar(View view, CharSequence text) {
        Snackbar.make(view, text, Snackbar.LENGTH_LONG).show();
    }
    public void showSnackBar(View view, CharSequence text,String actionText) {
        Snackbar.make(view, text, Snackbar.LENGTH_LONG).setAction(actionText, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }).show();
    }

    public void showToast(CharSequence text) {
        Toast.makeText(mActivity, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

    }
}

