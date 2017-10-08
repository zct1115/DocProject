package com.ckkj.docproject.ui.cupboard.cup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ckkj.docproject.R;
import com.ckkj.docproject.base.BaseActivity;
import com.ckkj.docproject.bean.cupboard.CupBoardData;
import com.ckkj.docproject.contract.CupBoardContract;
import com.ckkj.docproject.database.CupBoardDaoUtils;
import com.ckkj.docproject.presenter.cupboard.CupBoardPresenter;
import com.ckkj.docproject.ui.cupboard.cup.CupBoardActivity;
import com.ckkj.docproject.utils.NetUtils;
import com.ckkj.docproject.utils.NoFastOnclick;
import com.greendao.entity.Cupboard;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AddCupBoardActivity extends BaseActivity <CupBoardContract.Presenter> implements CupBoardContract.View {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.cupcode)
    EditText cupcode;
    @BindView(R.id.cupname)
    EditText cupname;
    @BindView(R.id.cuplocation)
    EditText cuplocation;
    @BindView(R.id.cuproom)
    EditText cuproom;
    @BindView(R.id.cuproomcode)
    EditText cuproomcode;
    @BindView(R.id.cupmore)
    EditText cupmore;
    @BindView(R.id.add_cupboard)
    Button addCupboard;
    @BindView(R.id.cancel_add)
    Button cancelAdd;
    @BindView(R.id.activity_add_cup_board)
    LinearLayout activityAddCupBoard;

    @Override
    public int getContentViewId() {
        return R.layout.activity_add_cup_board;
    }

    @Override
    protected void initAllMembers(Bundle savedInstanceState) {
        initToolbar("登记柜架");
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
                startActivity(new Intent(AddCupBoardActivity.this,CupBoardActivity.class));
                finish();
            }
        });
        return toolbar;
    }

    @Override
    protected CupBoardContract.Presenter getPresenter() {
        return new CupBoardPresenter(this);
    }

    @OnClick({R.id.add_cupboard, R.id.cancel_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.add_cupboard:
                String code=cupcode.getText().toString();
                String name=cupname.getText().toString();
                String location=cuplocation.getText().toString();
                String storeroomName=cuproom.getText().toString();
                String storeroomCode=cuproomcode.getText().toString();
                String remark=cupmore.getText().toString();
                if(NoFastOnclick.isFastClick()){
                    addCupboard.setEnabled(true);
                }
                else {
                    if (!code.isEmpty() && !name.isEmpty() && !location.isEmpty() && !storeroomName.isEmpty() && !storeroomCode.isEmpty()) {
                        if (!NetUtils.hasNetWorkConection(this)) {
                            Toast.makeText(this, "当前无网络，正在保存", Toast.LENGTH_SHORT).show();
                            CupBoardDaoUtils dao = new CupBoardDaoUtils(this);
                            Cupboard cupBoard = new Cupboard();
                            cupBoard.setCode(code);
                            cupBoard.setName(name);
                            cupBoard.setLocation(location);
                            cupBoard.setStoreroomName(storeroomName);
                            cupBoard.setStoreroomCode(storeroomCode);
                            cupBoard.setRemark(remark);
                            if (dao.samecupboard(cupBoard)) {
                                dao.insertCupboard(cupBoard);
                            } else {
                                new AlertDialog.Builder(this)
                                        .setTitle("柜架编号重复，请重新输入")
                                        .setPositiveButton("确定", null)
                                        .show();
                            }

                            dao.close();
                            Log.d("AddCupBoardActivity", "成功添加");
                        } else {
                            mPresenter.addData(name, code, location, storeroomName, storeroomCode, remark);
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
                startActivity(new Intent(AddCupBoardActivity.this,CupBoardActivity.class));
                finish();
                break;
        }
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        *//*getMenuInflater().inflate(R.menu.loginout, menu);
        return super.onCreateOptionsMenu(menu);*//*
        SubMenu subMenu = menu.addSubMenu("");


        subMenu.add("提交离线数据").setIcon(R.drawable.ic_off_line).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            public boolean onMenuItemClick(MenuItem item) {
                if(NetUtils.hasNetWorkConection(AddCupBoardActivity.this)){
                    DaoUtils dao=new DaoUtils(AddCupBoardActivity.this);
                    List<CupBoard> list= dao.listAll();
                    if(list.size()!=0) {
                        for (CupBoard cup : list) {
                            mPresenter.addData(cup.getName(), cup.getCode(), cup.getLocation(), cup.getStoreroomName(), cup.getStoreroomCode(), cup.getRemark());
                            dao.deleteCupboard(cup);
                        }

                    }else {
                        Toast.makeText(AddCupBoardActivity.this, "没有离线数据，无法提交", Toast.LENGTH_SHORT).show();
                    }
                    dao.close();
                }else {
                    Toast.makeText(AddCupBoardActivity.this, "无网络，无法提交离线数据", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });


        MenuItem item = subMenu.getItem();
        item.setIcon(R.drawable.ic_more);
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
        return super.onCreateOptionsMenu(menu);
    }*/


    @Override
    public void setCupBoardData(List<CupBoardData.DataBean> data,final int total,int pag) {

    }

    @Override
    public void getCupBoardDataFail(String message) {

    }

    @Override
    public void sendMessage(String msg) {
         showSnackBar(activityAddCupBoard,msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
