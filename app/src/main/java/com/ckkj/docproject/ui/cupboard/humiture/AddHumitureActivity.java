package com.ckkj.docproject.ui.cupboard.humiture;

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
import com.ckkj.docproject.bean.cupboard.HumitureData;
import com.ckkj.docproject.contract.HumitureContract;
import com.ckkj.docproject.database.HumitureDaoUtils;
import com.ckkj.docproject.presenter.cupboard.HumiturePresenter;
import com.ckkj.docproject.ui.cupboard.humiture.HumitureActivity;
import com.ckkj.docproject.utils.NetUtils;
import com.ckkj.docproject.utils.NoFastOnclick;
import com.ckkj.docproject.utils.Time;
import com.greendao.entity.Humiture;

import org.feezu.liuli.timeselector.Utils.DateUtil;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AddHumitureActivity extends BaseActivity<HumitureContract.Presenter> implements HumitureContract.View {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.cancel_add)
    Button cancelAdd;
    @BindView(R.id.add_humiture)
    Button addHumiture;
    @BindView(R.id.activity_add_humiture)
    LinearLayout activityAddHumiture;
    @BindView(R.id.humcheckData)
    EditText humcheckData;
    @BindView(R.id.hum_amTemp)
    EditText humAmTemp;
    @BindView(R.id.hum_amHumidity)
    EditText humAmHumidity;
    @BindView(R.id.hum_pmTemp)
    EditText humPmTemp;
    @BindView(R.id.hum_pmHumidity)
    EditText humPmHumidity;
    @BindView(R.id.hum_taken)
    EditText humTaken;
    @BindView(R.id.humcustodian)
    EditText humcustodian;
    @BindView(R.id.humremark)
    EditText humremark;
    @BindView(R.id.huminTemp)
    EditText huminTemp;
    @BindView(R.id.huminHumidity)
    EditText huminHumidity;
    @BindView(R.id.humoutTemp)
    EditText humoutTemp;
    @BindView(R.id.humoutHumidity)
    EditText humoutHumidity;

    private Time timeSelector;

    @Override
    public int getContentViewId() {
        return R.layout.activity_add_humiture;
    }

    @Override
    protected void initAllMembers(Bundle savedInstanceState) {
        initToolbar("登记温湿度");


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
                startActivity(new Intent(AddHumitureActivity.this, HumitureActivity.class));
                finish();
            }
        });
        return toolbar;
    }

    @Override
    protected HumitureContract.Presenter getPresenter() {
        return new HumiturePresenter(this);
    }


    @OnClick({R.id.add_humiture, R.id.cancel_add, R.id.humcheckData})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.add_humiture:
                String checkData = humcheckData.getText().toString();//测试时间
                String AmTemp = humAmTemp.getText().toString();//上午库房温度
                String AmHumidity = humAmHumidity.getText().toString();//上午库房湿度
                String PmTemp = humPmTemp.getText().toString();//下午库房温度
                String PmHumidity = humPmHumidity.getText().toString();//下午库房湿度
                String inTemp = huminTemp.getText().toString();//上午库外温度
                String inHumidity = huminHumidity.getText().toString();//上午库外湿度
                String outTemp = humoutTemp.getText().toString();//下午库外温度
                String outHumidity = humoutHumidity.getText().toString();//下午库外湿度
                String Taken = humTaken.getText().toString();//采取措施
                String custodian = humcustodian.getText().toString();//库房保管员
                String remark = humremark.getText().toString();//备注
                if(NoFastOnclick.isFastClick()) {
                    addHumiture.setEnabled(true);
                }else{
                    if (!checkData.isEmpty()) {
                        if (!NetUtils.hasNetWorkConection(this)) {
                            Toast.makeText(this, "当前无网络，保存成功", Toast.LENGTH_SHORT).show();
                            HumitureDaoUtils dao = new HumitureDaoUtils(AddHumitureActivity.this);
                            Humiture humiture = new Humiture();
                            humiture.setCheckData(checkData);
                            humiture.setAmTemp(AmTemp);
                            humiture.setAmHumidity(AmHumidity);
                            humiture.setInHumidity(inHumidity);
                            humiture.setPmHumidity(PmHumidity);
                            humiture.setPmTemp(PmTemp);
                            humiture.setCustodian(custodian);
                            humiture.setOutTemp(outTemp);
                            humiture.setRemark(remark);
                            humiture.setTaken(Taken);
                            humiture.setInTemp(inTemp);
                            humiture.setOutHumidity(outHumidity);
                            dao.insertHumiture(humiture);
                            dao.close();
                        } else {
                            mPresenter.addData(checkData, AmTemp, AmHumidity, PmTemp, PmHumidity, inTemp, inHumidity, outTemp, outHumidity, Taken, custodian, remark);
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
                startActivity(new Intent(AddHumitureActivity.this, HumitureActivity.class));
                finish();
                break;
            case R.id.humcheckData:
                Date curDate = new Date(System.currentTimeMillis());
                String date = DateUtil.format(curDate, "yyyy.MM.dd hh:mm");
                timeSelector = new Time(this, new Time.ResultHandler() {

                    @Override

                    public void handle(String time) {

                        humcheckData.setText(time);

                    }

                }, "1989.01.30 00:00", date);
                timeSelector.show();
                break;
        }
    }

    @Override
    public void setHumitureData(List<HumitureData.DataBean> data, int total, int pag) {

    }

    @Override
    public void getHumitureDataFail(String message) {

    }

    @Override
    public void sendMessage(String msg) {
        showSnackBar(activityAddHumiture, msg);
    }

}
