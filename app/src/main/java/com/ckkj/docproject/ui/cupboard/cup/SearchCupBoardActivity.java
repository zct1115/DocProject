package com.ckkj.docproject.ui.cupboard.cup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.ckkj.docproject.R;
import com.ckkj.docproject.base.BaseActivity;
import com.ckkj.docproject.base.BasePresenter;
import com.ckkj.docproject.ui.cupboard.cup.CupBoardActivity;
import com.ckkj.docproject.ui.cupboard.cup.CupBoardResultActivity;

import butterknife.BindView;

/**
 * Created by XISEVEN on 2017/5/22.
 */

public class SearchCupBoardActivity extends BaseActivity<BasePresenter> implements View.OnClickListener {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rg)
    RadioGroup rg;
    @BindView(R.id.input)
    EditText inputs;
    @BindView(R.id.search)
    Button search;

    private String Dataname;
    private String DataValues;


    @Override
    public int getContentViewId() {
        return R.layout.cupboard_search;
    }

    @Override
    protected void initAllMembers(Bundle savedInstanceState) {
        initToolbar("柜架搜索");
        search.setOnClickListener(this);

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
                startActivity(new Intent(SearchCupBoardActivity.this, CupBoardActivity.class));
                finish();
            }
        });

        return toolbar;
    }

    public static void start(Context context, String Dataname, String Datavalues) {
        Intent intent = new Intent(context, CupBoardResultActivity.class);
        intent.putExtra("Dataname", Dataname);
        intent.putExtra("Datavalues", Datavalues);
        context.startActivity(intent);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search:
                for (int i = 0; i < rg.getChildCount(); i++) {
                    RadioButton radioButton = (RadioButton) rg.getChildAt(i);
                    if (radioButton.isChecked()) {
                        switch (radioButton.getText().toString()) {
                            case "柜架名称":
                                Dataname = "name";
                                break;
                            case "柜架编号":
                                Dataname = "code";
                                break;
                            case "库房位置":
                                Dataname = "location";
                                break;
                            case "所在库房名称":
                                Dataname = "storeroomName";
                                break;
                            case "所在库房号":
                                Dataname = "storeroomCode";
                                break;
                            case "备注":
                                Dataname = "remark";
                                break;
                            default:
                                break;
                        }
                    }
                }
                if(inputs.getText().toString().isEmpty()){
                    showToast("查询条件不能为空");
                }else {
                    DataValues=inputs.getText().toString();
                    start(this,Dataname,DataValues);
                    finish();
                }

                break;
        }
    }



}
