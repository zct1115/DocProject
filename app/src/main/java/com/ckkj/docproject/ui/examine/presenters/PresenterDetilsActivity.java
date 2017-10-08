package com.ckkj.docproject.ui.examine.presenters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ckkj.docproject.R;
import com.ckkj.docproject.base.BaseActivity;
import com.ckkj.docproject.bean.examine.Presernters;
import com.ckkj.docproject.contract.PresenterContract;
import com.ckkj.docproject.presenter.examine.PresentersPresenter;
import com.ckkj.docproject.ui.examine.presenters.PresenterActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Ting on 2017/9/13.
 */

public class PresenterDetilsActivity extends BaseActivity<PresenterContract.Presenter> implements PresenterContract.View {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.e_taskName)
    TextView eTaskName;
    @BindView(R.id.e_dataCount)
    TextView eDataCount;
    @BindView(R.id.e_updateUser)
    TextView eUpdateUser;
    @BindView(R.id.e_addTime)
    TextView eAddTime;
    @BindView(R.id.e_classifyName)
    TextView eClassifyName;
    @BindView(R.id.e_preTaskName)
    TextView ePreTaskName;
    @BindView(R.id.e_addUser)
    TextView eAddUser;
    @BindView(R.id.e_rejectReason)
    TextView eRejectReason;
    @BindView(R.id.examine)
    Button examine;
    @BindView(R.id.content_layout)
    LinearLayout contentLayout;

    public Presernters.DataBean dataBean;

    @Override
    public int getContentViewId() {
        return R.layout.entitive_detail;
    }

    public static void start(Context context, int position, Presernters.DataBean databean) {
        Intent intent = new Intent(context, PresenterDetilsActivity.class);
        intent.putExtra("position",position);
        intent.putExtra("databean",databean);
        context.startActivity(intent);
    }
    @Override
    protected void initAllMembers(Bundle savedInstanceState) {
        initToolbar("现行文件详情");
        Intent intent = getIntent();
        dataBean = (Presernters.DataBean) intent.getSerializableExtra("databean");
        init(dataBean);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detils, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.detils:

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected PresenterContract.Presenter getPresenter() {
        return new PresentersPresenter(this);
    }

    private void init(Presernters.DataBean dataBean) {
        if(dataBean.getTaskName().isEmpty()){
            eTaskName.setText("");
        }else {
            eTaskName.setText(dataBean.getTaskName());
        }
        if(dataBean.getAddTime().isEmpty()){
            eAddTime.setText("");
        }else {
            eAddTime.setText(dataBean.getAddTime());
        }
        if(dataBean.getAddUser().isEmpty()){
            eAddUser.setText("");
        }else {
            eAddUser.setText(dataBean.getAddUser());
        }
        if(dataBean.getDataCount().isEmpty()){
            eDataCount.setText("");
        }else {
            eDataCount.setText(dataBean.getDataCount());
        }
        if(dataBean.getClassifyName().isEmpty()){
            eClassifyName.setText("");
        }else {
            eClassifyName.setText(dataBean.getClassifyName());
        }
        if(dataBean.getRejectReason().isEmpty()){
            eRejectReason.setText("");
        }else {
            eRejectReason.setText(dataBean.getRejectReason());
        }
        if(dataBean.getUpdateUser().isEmpty()){
            eUpdateUser.setText("");
        }else {
            eUpdateUser.setText(dataBean.getUpdateUser());
        }
        if(dataBean.getPreTaskName().isEmpty()){
            ePreTaskName.setText("");
        }else {
            ePreTaskName.setText(dataBean.getPreTaskName());
        }




    }

    @OnClick(R.id.examine)
    public void onViewClicked() {
        mPresenter.checked(dataBean.getExamine_processId());
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
                startActivity(new Intent(PresenterDetilsActivity.this,PresenterActivity.class));
                finish();
            }
        });
        return toolbar;
    }

    @Override
    public void setPresenterData(List<Presernters.DataBean> data, int total, int pageNum) {

    }

    @Override
    public void getPresenterDataFail(String message) {

    }

    @Override
    public void sendMessage(String msg) {
        new android.support.v7.app.AlertDialog.Builder(this)
                .setTitle(msg)
                .setPositiveButton("确定", null)
                .show();
    }

    @Override
    public void getStatus(String status) {
       if(status.equals("1")){

       }


    }
}
