package com.ckkj.docproject.ui.examine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.ckkj.docproject.R;
import com.ckkj.docproject.base.BaseActivity;
import com.ckkj.docproject.ui.examine.entitive.EntitiveActivity;
import com.ckkj.docproject.ui.examine.preprocess.PreprocessActivity;
import com.ckkj.docproject.ui.examine.presenters.PresenterActivity;
import com.ckkj.docproject.ui.home.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExamineActivity extends BaseActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.activity_examine)
    LinearLayout activityExamine;
    @BindView(R.id.collect)
    LinearLayout collect;
    @BindView(R.id.accept)
    LinearLayout accept;
    @BindView(R.id.identity)
    LinearLayout identity;
    @BindView(R.id.deletefile)
    LinearLayout deletefile;
    @BindView(R.id.border)
    LinearLayout border;
    @BindView(R.id.present)
    LinearLayout present;
    @BindView(R.id.entity)
    LinearLayout entity;
    @BindView(R.id.preprocess)
    LinearLayout preprocess;


    @Override
    public int getContentViewId() {
        return R.layout.activity_examine;
    }

    @Override
    protected void initAllMembers(Bundle savedInstanceState) {
        initToolbar("电子档案审批");

    }

    @Override
    public Toolbar initToolbar(String title) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ExamineActivity.this, MainActivity.class));
                finish();
            }
        });
        return toolbar;
    }


    @OnClick({R.id.collect, R.id.accept, R.id.identity, R.id.deletefile, R.id.border, R.id.present, R.id.entity,R.id.preprocess})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.entity:
                startActivity(new Intent(this, EntitiveActivity.class));
                finish();
                break;
            case R.id.collect:
                break;
            case R.id.accept:
                break;
            case R.id.identity:
                break;
            case R.id.deletefile:
                break;
            case R.id.border:
                break;
            case R.id.preprocess:
                startActivity(new Intent(this, PreprocessActivity.class));
                finish();
                break;
            case R.id.present:
                startActivity(new Intent(this, PresenterActivity.class));
                finish();
                break;
        }
    }



}
