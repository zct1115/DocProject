package com.ckkj.docproject.ui.cupboard.protect;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ckkj.docproject.R;
import com.ckkj.docproject.base.BaseActivity;
import com.ckkj.docproject.bean.cupboard.ProtectData;
import com.ckkj.docproject.contract.ProtectContract;
import com.ckkj.docproject.presenter.cupboard.ProtectPresenter;
import com.ckkj.docproject.ui.cupboard.protect.ProtectActivity;
import com.ckkj.docproject.utils.NoFastOnclick;
import com.ckkj.docproject.utils.Time;

import org.feezu.liuli.timeselector.Utils.DateUtil;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zct11 on 2017/8/9.
 */

public class ProtectDetailActivity extends BaseActivity<ProtectContract.Presenter> implements ProtectContract.View {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.change)
    Button change;

    @BindView(R.id.content_layout)
    LinearLayout content_layout;
    @BindView(R.id.position)
    TextView mposition;
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


    private int position;
    private ProtectData.DataBean dataBean;
    private Time timeSelector;

    @Override
    public int getContentViewId() {
        return R.layout.protect_detail;
    }

    public static void start(Context context, int position, ProtectData.DataBean dataBean) {
        Intent intent = new Intent(context, ProtectDetailActivity.class);
        intent.putExtra("position", position);
        intent.putExtra("dataBean",dataBean);
        context.startActivity(intent);
    }

    @Override
    protected void initAllMembers(Bundle savedInstanceState) {
        initToolbar("保护登记详情");
        Intent intent = getIntent();
        position = intent.getIntExtra("position", 0);
        dataBean = (ProtectData.DataBean) intent.getSerializableExtra("dataBean");
        init(position, dataBean);
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
                startActivity(new Intent(ProtectDetailActivity.this,ProtectActivity.class));
                finish();
            }
        });
        return toolbar;
    }


    private void init(int position, ProtectData.DataBean dataBean) {
        mposition.setText(position + 1 + "");
        proStoreroomCode.setText(dataBean.getStoreroomCode());
        proStoreroomName.setText(dataBean.getStoreroomName());
        proProtectType.setText(dataBean.getProtectType());
        proProtectTime.setText(dataBean.getProtectTime());
        proProtectResult.setText(dataBean.getProtectResult());
        proOperUser.setText(dataBean.getOperUser());

    }

    @Override
    protected ProtectContract.Presenter getPresenter() {
        return new ProtectPresenter(this);
    }

    @OnClick({R.id.change,R.id.pro_protectTime})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.change:
                if(NoFastOnclick.isFastClick()){
                    change.setEnabled(true);
                }else {
                    String id = dataBean.getId();
                    String StoreroomCode = proStoreroomCode.getText().toString();
                    String StoreroomName = proStoreroomName.getText().toString();
                    String ProtectType = proProtectType.getText().toString();
                    String ProtectTime = proProtectTime.getText().toString();
                    String ProtectResult = proProtectResult.getText().toString();
                    String OperUser = proOperUser.getText().toString();
                    mPresenter.updateData(id, StoreroomCode, StoreroomName, ProtectType, ProtectTime, ProtectResult, OperUser);
                }
                    break;
            case R.id.pro_protectTime:
                Date curDate = new Date(System.currentTimeMillis());
                String date= DateUtil.format(curDate,"yyyy.MM.dd HH:mm");
                timeSelector = new Time(this, new Time.ResultHandler() {

                    @Override

                    public void handle(String time) {

                        proProtectTime.setText(time);

                    }

                }, "1989.01.30 00:00", date);
                timeSelector.show();
                break;

        }
    }

    @Override
    public void setProtectData(List<ProtectData.DataBean> data,int total,int pag) {

    }

    @Override
    public void getProtectDataFail(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void sendMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


}
