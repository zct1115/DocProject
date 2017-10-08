package com.ckkj.docproject.ui.cupboard.protect;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ckkj.docproject.R;
import com.ckkj.docproject.base.BaseActivity;
import com.ckkj.docproject.bean.cupboard.ProtectData;
import com.ckkj.docproject.contract.ProtectContract;
import com.ckkj.docproject.database.ProtectDaoUtils;
import com.ckkj.docproject.presenter.cupboard.ProtectPresenter;
import com.ckkj.docproject.ui.cupboard.protect.ProtectActivity;
import com.ckkj.docproject.utils.NetUtils;
import com.ckkj.docproject.utils.NoFastOnclick;
import com.greendao.entity.Protect;

import org.feezu.liuli.timeselector.TimeSelector;
import org.feezu.liuli.timeselector.Utils.DateUtil;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AddProtectActivity extends BaseActivity<ProtectContract.Presenter> implements ProtectContract.View {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.pro_storeroomCode)
    EditText proStoreroomCode;
    @BindView(R.id.pro_storeroomName)
    EditText proStoreroomName;
    @BindView(R.id.pro_protectType)
    EditText proProtectType;
    @BindView(R.id.pro_protectTime)
    EditText proProtectTime;
    @BindView(R.id.pro_protectResult)
    EditText proProtectResult;
    @BindView(R.id.pro_operUser)
    EditText proOperUser;
    @BindView(R.id.add_protect)
    Button addProtect;
    @BindView(R.id.cancel_add)
    Button cancelAdd;
    @BindView(R.id.activity_add_protect)
    LinearLayout activityAddProtect;


    private TimeSelector timeSelector;

    @Override
    public int getContentViewId() {
        return R.layout.activity_add_protect;
    }

    @Override
    protected void initAllMembers(Bundle savedInstanceState) {
        initToolbar("添加保护登记");


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
                startActivity(new Intent(AddProtectActivity.this,ProtectActivity.class));
                finish();
            }
        });
        return toolbar;
    }

    @Override
    protected ProtectContract.Presenter getPresenter() {
        return new ProtectPresenter(this);
    }


    @OnClick({R.id.add_protect, R.id.cancel_add,R.id.pro_protectTime})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.add_protect:
                String StoreroomCode = proStoreroomCode.getText().toString();
                String StoreroomName = proStoreroomName.getText().toString();
                String ProtectType = proProtectType.getText().toString();
                String ProtectTime = proProtectTime.getText().toString();
                String ProtectResult = proProtectResult.getText().toString();
                String OperUser = proOperUser.getText().toString();
               if(NoFastOnclick.isFastClick()) {
                    addProtect.setEnabled(true);
                }
                else {
                    if (!StoreroomCode.isEmpty() || !StoreroomName.isEmpty() || !ProtectType.isEmpty() || !ProtectTime.isEmpty() || !ProtectResult.isEmpty() || !OperUser.isEmpty()) {
                        if (!NetUtils.hasNetWorkConection(this)) {
                            Toast.makeText(this, "当前无网络，保存成功", Toast.LENGTH_SHORT).show();
                            ProtectDaoUtils dao = new ProtectDaoUtils(this);
                            Protect protect = new Protect();
                            protect.setStoreroomCode(StoreroomCode);
                            protect.setStoreroomName(StoreroomName);
                            protect.setProtectType(ProtectType);
                            protect.setProtectResult(ProtectResult);
                            protect.setProtectTime(ProtectTime);
                            protect.setOperUser(OperUser);

                            dao.insertProtect(protect);
                            dao.close();

                        } else {
                            mPresenter.addData(StoreroomCode, StoreroomName, ProtectType, ProtectTime, ProtectResult, OperUser);
                        }
                    } else {
                        new AlertDialog.Builder(this)
                                .setTitle("请检查是否输入为空")
                                .setMessage("确定吗？")
                                .setPositiveButton("是", null)
                                .setNegativeButton("否", null)
                                .show();
                    }
                }



        break;
            case R.id.cancel_add:
                startActivity(new Intent(AddProtectActivity.this, ProtectActivity.class));
                finish();
                break;
            case R.id.pro_protectTime:
                Date curDate = new Date(System.currentTimeMillis());
                String date=DateUtil.format(curDate,"yyyy-MM-dd HH:mm");
                timeSelector = new TimeSelector(this, new TimeSelector.ResultHandler() {

                    @Override

                    public void handle(String time) {

                        proProtectTime.setText(time);

                    }

                }, "1989-01-30 00:00", date);
                timeSelector.show();
                break;
        }
    }

    @Override
    public void setProtectData(List<ProtectData.DataBean> data,int total,int pag) {

    }

    @Override
    public void getProtectDataFail(String message) {

    }

    @Override
    public void sendMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


}
