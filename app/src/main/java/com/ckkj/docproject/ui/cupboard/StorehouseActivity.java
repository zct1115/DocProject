package com.ckkj.docproject.ui.cupboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.ckkj.docproject.R;
import com.ckkj.docproject.base.BaseActivity;
import com.ckkj.docproject.ui.cupboard.cup.CupBoardActivity;
import com.ckkj.docproject.ui.cupboard.humiture.HumitureActivity;
import com.ckkj.docproject.ui.home.MainActivity;
import com.ckkj.docproject.ui.cupboard.protect.ProtectActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StorehouseActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.toolbar)
    Toolbar  toolbar;
    @BindView(R.id.cupboard)
    LinearLayout cupboard;
    /*@BindView(R.id.check)
    LinearLayout check;*/
    @BindView(R.id.hemiture)
    LinearLayout hemiture;
    @BindView(R.id.protect)
    LinearLayout protect;


    @Override
    public int getContentViewId() {
        return R.layout.activity_storehouse;
    }

    @Override
    protected void initAllMembers(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        initToolbar("库房管理");
        cupboard.setOnClickListener(this);
        hemiture.setOnClickListener(this);
        protect.setOnClickListener(this);
        //check.setOnClickListener(this);
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
                startActivity(new Intent(StorehouseActivity.this,MainActivity.class));
                finish();
            }
        });
        return toolbar;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cupboard:
                startActivity(new Intent(StorehouseActivity.this, CupBoardActivity.class));
                finish();
                break;
            case R.id.hemiture:
                startActivity(new Intent(StorehouseActivity.this, HumitureActivity.class));
                finish();
                break;
          /*  case R.id.check:
                Toast.makeText(this, "功能在完善中。", Toast.LENGTH_SHORT).show();
                break;*/
            case R.id.protect:
                startActivity(new Intent(StorehouseActivity.this, ProtectActivity.class));
                finish();
                break;
            default:
                break;
        }
    }


}
