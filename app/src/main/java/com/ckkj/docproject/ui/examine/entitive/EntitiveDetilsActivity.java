package com.ckkj.docproject.ui.examine.entitive;

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
import com.ckkj.docproject.bean.examine.Entitive;
import com.ckkj.docproject.contract.EntitiveContract;
import com.ckkj.docproject.presenter.examine.EntitivePresenter;
import com.ckkj.docproject.ui.examine.entitive.EntitiveActivity;
import com.ckkj.docproject.ui.examine.entitive.NextCheckedActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Ting on 2017/9/13.
 */

public class EntitiveDetilsActivity extends BaseActivity<EntitiveContract.Presenter> implements EntitiveContract.View {
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

    public Entitive.DataBean dataBean;

    @Override
    public int getContentViewId() {
        return R.layout.entitive_detail;
    }

    public static void start(Context context, int position, Entitive.DataBean databean) {
        Intent intent = new Intent(context, EntitiveDetilsActivity.class);
        intent.putExtra("position",position);
        intent.putExtra("databean",databean);
        context.startActivity(intent);
    }
    @Override
    protected void initAllMembers(Bundle savedInstanceState) {
        initToolbar("实体档案服务详情");
        Intent intent = getIntent();
        dataBean = (Entitive.DataBean) intent.getSerializableExtra("databean");
        init(dataBean);
    }

    @Override
    protected EntitiveContract.Presenter getPresenter() {
        return new EntitivePresenter(this);
    }

    private void init(Entitive.DataBean dataBean) {
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
                startActivity(new Intent(EntitiveDetilsActivity.this,EntitiveActivity.class));
                finish();
            }
        });
        return toolbar;
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
    public void setEntitiveData(List<Entitive.DataBean> data, int total, int pageNum) {

    }

    @Override
    public void getEntitiveDataFail(String message) {

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
          NextCheckedActivity.start(EntitiveDetilsActivity.this,dataBean);
       }


    }
}
