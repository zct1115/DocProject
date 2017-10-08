package com.ckkj.docproject.ui.cupboard.protect;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.ckkj.docproject.R;
import com.ckkj.docproject.base.BaseActivity;
import com.ckkj.docproject.base.BasePresenter;
import com.ckkj.docproject.ui.cupboard.protect.ProtectActivity;
import com.ckkj.docproject.ui.cupboard.protect.ProtectResultActivity;
import com.ckkj.docproject.utils.Time;

import org.feezu.liuli.timeselector.Utils.DateUtil;

import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zct11 on 2017/8/13.
 */

public class SearchProtectActivity extends BaseActivity<BasePresenter> {


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
    @BindView(R.id.search_protect)
    Button searchProtect;
    @BindView(R.id.activity_protect)
    LinearLayout activityProtect;


    private Time timeSelector;

    @Override
    public int getContentViewId() {
        return R.layout.search_protect;
    }

    @Override
    protected void initAllMembers(Bundle savedInstanceState) {
        initToolbar("保护登记查询");

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
                startActivity(new Intent(SearchProtectActivity.this, ProtectActivity.class));
                finish();
            }
        });

        return toolbar;
    }


    @OnClick({R.id.search_protect,R.id.pro_protectTime})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.search_protect:
            String StoreroomCode = proStoreroomCode.getText().toString();
            String StoreroomName = proStoreroomName.getText().toString();
            String ProtectType = proProtectType.getText().toString();
            String ProtectTime = proProtectTime.getText().toString();
            String ProtectResult = proProtectResult.getText().toString();
            String OperUser = proOperUser.getText().toString();

            Intent intent = new Intent(this, ProtectResultActivity.class);
            intent.putExtra("StoreroomCode", StoreroomCode);
            intent.putExtra("StoreroomName", StoreroomName);
            intent.putExtra("ProtectType", ProtectType);
            intent.putExtra("ProtectTime", ProtectTime);
            intent.putExtra("ProtectResult", ProtectResult);
            intent.putExtra("OperUser", OperUser);
            startActivity(intent);
            finish();
            break;
            case R.id.pro_protectTime:
                Date curDate = new Date(System.currentTimeMillis());
                String date= DateUtil.format(curDate,"yyyy.MM.dd hh:mm");
                timeSelector = new Time(this, new Time.ResultHandler() {
                    @Override
                    public void handle(String time) {
                        proProtectTime.setText(time);
                    }
                },"1989.01.30 00:00", date);
                timeSelector.show();
                break;
        }


    }
}
