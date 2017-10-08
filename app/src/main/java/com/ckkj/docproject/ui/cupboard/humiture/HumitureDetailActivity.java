package com.ckkj.docproject.ui.cupboard.humiture;

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

import com.ckkj.docproject.R;
import com.ckkj.docproject.base.BaseActivity;
import com.ckkj.docproject.bean.cupboard.HumitureData;
import com.ckkj.docproject.contract.HumitureContract;
import com.ckkj.docproject.presenter.cupboard.HumiturePresenter;
import com.ckkj.docproject.ui.cupboard.humiture.HumitureActivity;
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

public class HumitureDetailActivity extends BaseActivity<HumitureContract.Presenter> implements HumitureContract.View {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.content_layout)
    LinearLayout content_layout;

    @BindView(R.id.position)
    TextView mposition;
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
    @BindView(R.id.update_humiture)
    Button updateHumiture;
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

    private int position;
    private HumitureData.DataBean dataBean;
    private Time timeSelector;

    @Override
    public int getContentViewId() {
        return R.layout.humiture_detail;
    }

    public static void start(Context context, int position, HumitureData.DataBean dataBean) {
        Intent intent = new Intent(context, HumitureDetailActivity.class);
        intent.putExtra("position", position);
        intent.putExtra("dataBean", dataBean);
        context.startActivity(intent);
    }

    @Override
    protected void initAllMembers(Bundle savedInstanceState) {
        initToolbar("温湿度详情");
        Intent intent = getIntent();
        position = intent.getIntExtra("position", 0);
        dataBean = (HumitureData.DataBean) intent.getSerializableExtra("dataBean");
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
                startActivity(new Intent(HumitureDetailActivity.this, HumitureActivity.class));
                finish();
            }
        });
        return toolbar;
    }

    private void init(int position, HumitureData.DataBean dataBean) {
        mposition.setText(position + 1 + "");
        humcheckData.setText(dataBean.getCheckData());
        humAmTemp.setText(dataBean.getAmTemp());
        humAmHumidity.setText(dataBean.getAmHumidty());
        humPmTemp.setText(dataBean.getPmTemp());
        humPmHumidity.setText(dataBean.getPmHumidty());

        huminTemp.setText(dataBean.getInTemp());
        huminHumidity.setText(dataBean.getInHumidity());
        humoutTemp.setText(dataBean.getOutTemp());
        humoutHumidity.setText(dataBean.getOutHumidity());

        humTaken.setText(dataBean.getTakeSteps());
        humcustodian.setText(dataBean.getCustodian());
        humremark.setText(dataBean.getRemark());


    }

    @Override
    protected HumitureContract.Presenter getPresenter() {
        return new HumiturePresenter(this);
    }

    @OnClick({R.id.update_humiture, R.id.humcheckData})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.update_humiture:
                if(NoFastOnclick.isFastClick()){
                    updateHumiture.setEnabled(true);
                }else {
                    String id = dataBean.getId();
                    String checkData = humcheckData.getText().toString();
                    String AmTemp = humAmTemp.getText().toString();
                    String AmHumidity = humAmHumidity.getText().toString();
                    String PmTemp = humPmTemp.getText().toString();
                    String PmHumidity = humPmHumidity.getText().toString();

                    String inTemp = huminTemp.getText().toString();
                    String inHumidity = huminHumidity.getText().toString();
                    String outTemp = humoutTemp.getText().toString();
                    String outHumidity = humoutHumidity.getText().toString();

                    String Taken = humTaken.getText().toString();
                    String custodian = humcustodian.getText().toString();
                    String remark = humremark.getText().toString();

                    mPresenter.updateData(id, checkData, AmTemp, AmHumidity, PmTemp, PmHumidity, inTemp, inHumidity, outTemp, outHumidity, Taken, custodian, remark);
                }

                break;
            case R.id.humcheckData:
                Date curDate = new Date(System.currentTimeMillis());
                String date = DateUtil.format(curDate, "yyyy.MM.dd HH:mm");
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
    public void setHumitureData(List<HumitureData.DataBean> data, int total, int page) {

    }

    @Override
    public void getHumitureDataFail(String message) {
        showSnackBar(content_layout, message);
    }


    @Override
    public void sendMessage(String msg) {
        showSnackBar(content_layout, msg);
    }

}
