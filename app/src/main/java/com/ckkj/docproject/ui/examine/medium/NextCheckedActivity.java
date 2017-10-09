package com.ckkj.docproject.ui.examine.medium;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
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
import com.ckkj.docproject.bean.examine.Medium;
import com.ckkj.docproject.contract.MediumContract;
import com.ckkj.docproject.presenter.examine.MediumPresenter;
import com.ckkj.docproject.ui.examine.medium.MediumActivity;

import org.feezu.liuli.timeselector.TimeSelector;
import org.feezu.liuli.timeselector.Utils.DateUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class NextCheckedActivity extends BaseActivity<MediumContract.Presenter> implements MediumContract.View {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.e_preTaskName)
    TextView ePreTaskName;
    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.e_rejectReason)
    EditText eRejectReason;
    @BindView(R.id.yes)
    Button yes;
    @BindView(R.id.no)
    Button no;
    @BindView(R.id.e_time)
    EditText eTime;
    @BindView(R.id.e_download)
    CheckBox eDownload;

    public Medium.DataBean dataBean;
    private List<String> data;
    private ArrayAdapter adapter;
    private String auditState = "AGREE";
    private String download;
    private String time;
    private TimeSelector timeSelector;


    @Override
    public int getContentViewId() {
        return R.layout.presenter_nextchecked_layout;
    }

    @Override
    protected void initAllMembers(Bundle savedInstanceState) {
        initToolbar("逐级审核");
        Intent intent = getIntent();
        dataBean = (Medium.DataBean) intent.getSerializableExtra("databean");
        init(dataBean);
    }

    @Override
    protected MediumContract.Presenter getPresenter() {
        return new MediumPresenter(this);
    }

    private void init(Medium.DataBean dataBean) {
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
                startActivity(new Intent(NextCheckedActivity.this, MediumActivity.class));
                finish();
            }
        });

        return toolbar;
    }

    @Override
    public void setMediumData(List<Medium.DataBean> data, int total, int pageNum) {

    }

    @Override
    public void getMediumDataFail(String message) {

    }

    @Override
    public void sendMessage(String msg) {

    }

    @Override
    public void getStatus(String status) {

    }

    @OnClick({R.id.yes, R.id.no, R.id.e_time})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.yes:
                String updateUser = ePreTaskName.getText().toString();
                spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if (position == 0) {
                            auditState = "AGREE";
                        } else {
                            auditState = "REJECT";
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
                eDownload.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (eDownload.isSelected()) {
                            download = "1";
                        } else {
                            download = "0";
                        }
                    }
                });


                time = eTime.getText().toString();
                String rejectReason = eRejectReason.getText().toString();
                mPresenter.nextchecked(dataBean.getExamine_id(), dataBean.getExamine_processId(), dataBean.getCurrentTaskId(), auditState, rejectReason);
                break;
            case R.id.no:
                break;

            case R.id.e_time:
                Date curDate = new Date(System.currentTimeMillis());
                String date = DateUtil.format(curDate, "yyyy-MM-dd HH:mm");
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

    public static void start(Context context, Medium.DataBean dataBean) {
        Intent intent = new Intent(context, NextCheckedActivity.class);
        intent.putExtra("databean", dataBean);
        context.startActivity(intent);
    }


}
