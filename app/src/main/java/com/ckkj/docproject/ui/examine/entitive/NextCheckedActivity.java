package com.ckkj.docproject.ui.examine.entitive;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.ckkj.docproject.R;
import com.ckkj.docproject.base.BaseActivity;
import com.ckkj.docproject.bean.examine.Entitive;
import com.ckkj.docproject.contract.EntitiveContract;
import com.ckkj.docproject.presenter.examine.EntitivePresenter;
import com.ckkj.docproject.ui.examine.entitive.EntitiveActivity;

import org.feezu.liuli.timeselector.TimeSelector;
import org.feezu.liuli.timeselector.Utils.DateUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class NextCheckedActivity extends BaseActivity<EntitiveContract.Presenter> implements EntitiveContract.View {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.e_preTaskName)
    TextView ePreTaskName;
    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.e_replicable)
    CheckBox eReplicable;
    @BindView(R.id.e_accessTo)
    CheckBox eAccessTo;
    @BindView(R.id.e_rejectReason)
    EditText eRejectReason;
    @BindView(R.id.yes)
    Button yes;
    @BindView(R.id.no)
    Button no;

    public Entitive.DataBean dataBean;
    @BindView(R.id.e_time)
    EditText eTime;

    private List<String> data;
    private ArrayAdapter adapter;
    private String auditState="AGREE";
    private String replicable;
    private String accessTo;
    private String time;
    private TimeSelector timeSelector;


    @Override
    public int getContentViewId() {
        return R.layout.nextchecked_layout;
    }

    @Override
    protected void initAllMembers(Bundle savedInstanceState) {
        initToolbar("逐级审核");
        Intent intent = getIntent();
        dataBean = (Entitive.DataBean) intent.getSerializableExtra("databean");
        init(dataBean);
    }

    @Override
    protected EntitiveContract.Presenter getPresenter() {
        return new EntitivePresenter(this);
    }

    private void init(Entitive.DataBean dataBean) {
        ePreTaskName.setText(dataBean.getUpdateUser());
        data = new ArrayList<String>();
        data.add("同意");
        data.add("驳回");
        //适配器
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
        //设置样式
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        spinner.setAdapter(adapter);


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
                startActivity(new Intent(NextCheckedActivity.this, EntitiveActivity.class));
                finish();
            }
        });

        return toolbar;
    }

    @Override
    public void setEntitiveData(List<Entitive.DataBean> data, int total, int pageNum) {

    }

    @Override
    public void getEntitiveDataFail(String message) {
      Show(message);
    }
    public void Show(String msg){
        new AlertDialog.Builder(this)
                .setTitle(msg)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(NextCheckedActivity.this,EntitiveActivity.class));
                        finish();
                    }
                })
                .show();
    }


    @Override
    public void sendMessage(String msg) {
        Show(msg);
    }

    @Override
    public void getStatus(String status) {
       Show(status);
    }

    @OnClick({R.id.yes, R.id.no,R.id.e_time})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.yes:
                String updateUser = ePreTaskName.getText().toString();
               /* if (spinner.getSelectedItem() == 0) {
                    auditState = "AGREE";
                } else {
                    auditState = "REJECT";
                }*/
               spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
                   @Override
                   public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                       if(position==0){
                           auditState = "AGREE";
                       }else {
                           auditState = "REJECT";
                       }
                   }

                   @Override
                   public void onNothingSelected(AdapterView<?> parent) {
                   }
               });
                eReplicable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (eReplicable.isSelected()) {
                            replicable = "1";
                        } else {
                            replicable = "0";
                        }
                    }
                });

                eAccessTo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (!eAccessTo.isSelected()) {
                            accessTo = "1";
                        } else {
                            accessTo = "0";
                        }
                    }
                });

                time=eTime.getText().toString();
                String rejectReason=eRejectReason.getText().toString();
                mPresenter.nextchecked(dataBean.getExamine_id(),dataBean.getExamine_processId(),dataBean.getCurrentTaskId(),auditState,rejectReason,replicable,accessTo);

                break;
            case R.id.no:
                break;

            case R.id.e_time:
                Date curDate = new Date(System.currentTimeMillis());
                String date= DateUtil.format(curDate,"yyyy-MM-dd HH:mm");
                timeSelector = new TimeSelector(NextCheckedActivity.this, new TimeSelector.ResultHandler() {

                    @Override

                    public void handle(String time) {

                        eTime.setText(time);

                    }

                }, "1989-01-30 00:00", date);
                timeSelector.show();
                break;
        }
    }

    public static void start(Context context, Entitive.DataBean dataBean) {
        Intent intent = new Intent(context, NextCheckedActivity.class);
        intent.putExtra("databean", dataBean);
        context.startActivity(intent);
    }


}
