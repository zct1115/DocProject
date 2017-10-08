package com.ckkj.docproject.ui.cupboard.cup;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ckkj.docproject.R;
import com.ckkj.docproject.base.BaseActivity;
import com.ckkj.docproject.bean.cupboard.CupBoardData;
import com.ckkj.docproject.contract.CupBoardContract;
import com.ckkj.docproject.presenter.cupboard.CupBoardPresenter;
import com.ckkj.docproject.ui.cupboard.cup.CupBoardActivity;
import com.ckkj.docproject.utils.NoFastOnclick;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zct11 on 2017/8/9.
 */

public class CupBoardDetailActivity extends BaseActivity<CupBoardContract.Presenter> implements CupBoardContract.View {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.position)
    TextView mposition;
    @BindView(R.id.code)
    EditText cupcode;
    @BindView(R.id.name)
    EditText cupname;
    @BindView(R.id.location)
    EditText cuplocation;
    @BindView(R.id.room)
    EditText cuproom;
    @BindView(R.id.roomcode)
    EditText cuproomcode;
    @BindView(R.id.more)
    EditText cupmore;
    @BindView(R.id.change)
    Button change;

    @BindView(R.id.content_layout)
    LinearLayout content_layout;

    private int position;
    private CupBoardData.DataBean dataBean;


    @Override
    public int getContentViewId() {
        return R.layout.cupboard_detail;
    }

    public static void start(Context context, int position, CupBoardData.DataBean dataBean) {
        Intent intent = new Intent(context, CupBoardDetailActivity.class);
        intent.putExtra("position", position);
        intent.putExtra("dataBean", dataBean);
        context.startActivity(intent);
    }

    @Override
    protected void initAllMembers(Bundle savedInstanceState) {
        initToolbar("柜架详情");
        Intent intent = getIntent();
        position = intent.getIntExtra("position", 0);
        dataBean = (CupBoardData.DataBean) intent.getSerializableExtra("dataBean");
        init(position, dataBean);
    }

    private void init(int position, CupBoardData.DataBean dataBean) {
        mposition.setText(position + 1 + "");
        cupname.setText(dataBean.getName());
        cupcode.setText(dataBean.getCode());
        cuplocation.setText(dataBean.getLocation());
        cuproom.setText(dataBean.getStoreroomName());
        cuproomcode.setText(dataBean.getStoreroomCode());
        cupmore.setText(dataBean.getRemark());
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
                startActivity(new Intent(CupBoardDetailActivity.this,CupBoardActivity.class));
                finish();
            }
        });
        return toolbar;
    }

    @Override
    protected CupBoardContract.Presenter getPresenter() {
        return new CupBoardPresenter(this);
    }

    @OnClick({R.id.change})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.change:

                    if(NoFastOnclick.isFastClick()){
                       final boolean chance=true;
                        change.setEnabled(chance);
                        change.setBackgroundResource(R.color.buttonNotClick);
                        change.setTextColor(Color.BLACK);

                    }else {
                        change.setBackgroundResource(R.color.buttonClick);
                        change.setTextColor(Color.WHITE);
                        String id = dataBean.getId();
                        String code = cupcode.getText().toString();
                        String name = cupname.getText().toString();
                        String location = cuplocation.getText().toString();
                        String storeroomName = cuproom.getText().toString();
                        String storeroomCode = cuproomcode.getText().toString();
                        String remark = cupmore.getText().toString();
                        mPresenter.updateData(id, name, code, location, storeroomName, storeroomCode, remark);
                    }


                break;
        }
    }

    @Override
    public void setCupBoardData(List<CupBoardData.DataBean> data,final int total,int pag) {

    }

    @Override
    public void getCupBoardDataFail(String message) {
        showSnackBar(content_layout,message );

    }
    @Override
    public void sendMessage(String msg) {
        showSnackBar(content_layout, msg);
    }
}
