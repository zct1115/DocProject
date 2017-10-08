package com.ckkj.docproject.ui.cupboard.humiture;

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
import com.ckkj.docproject.ui.cupboard.humiture.HumitureActivity;
import com.ckkj.docproject.ui.cupboard.humiture.HumitureResultActivity;
import com.ckkj.docproject.utils.Time;

import org.feezu.liuli.timeselector.Utils.DateUtil;

import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zct11 on 2017/8/13.
 */

public class SearchHumitureActivity extends BaseActivity<BasePresenter> {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
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
    @BindView(R.id.huminTemp)
    EditText huminTemp;
    @BindView(R.id.huminHumidity)
    EditText huminHumidity;
    @BindView(R.id.humoutTemp)
    EditText humoutTemp;
    @BindView(R.id.humoutHumidity)
    EditText humoutHumidity;
    @BindView(R.id.hum_taken)
    EditText humTaken;
    @BindView(R.id.humcustodian)
    EditText humcustodian;
    @BindView(R.id.humremark)
    EditText humremark;
    @BindView(R.id.search_humiture)
    Button searchHumiture;
    @BindView(R.id.activity_humiture)
    LinearLayout activityHumiture;

    private Time timeSelector;

    @Override
    public int getContentViewId() {
        return R.layout.search_humiture;
    }

    @Override
    protected void initAllMembers(Bundle savedInstanceState) {
        initToolbar("温湿度搜索");
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
                startActivity(new Intent(SearchHumitureActivity.this, HumitureActivity.class));
                finish();
            }
        });

        return toolbar;
    }

    @OnClick({R.id.search_humiture,R.id.humcheckData})
    public void onViewClicked(View v) {
        switch (v.getId()){
            case R.id.search_humiture:
                String checkData = humcheckData.getText().toString();
                String AmTemp = humAmTemp.getText().toString();
                String AmHumidity = humAmHumidity.getText().toString();
                String PmTemp = humPmTemp.getText().toString();
                String PmHumidity = humPmHumidity.getText().toString();
                String inTemp = huminTemp.getText().toString();
                String inHumidity = huminHumidity.getText().toString();
                String outTemp = humoutTemp.getText().toString();
                String outHumidity = humoutHumidity.getText().toString();
                String custodian = humcustodian.getText().toString();
                String Taken = humTaken.getText().toString();
                String remark = humremark.getText().toString();

                Intent intent = new Intent(this, HumitureResultActivity.class);
                intent.putExtra("checkData", checkData);
                intent.putExtra("AmTemp", AmTemp);
                intent.putExtra("AmHumidity", AmHumidity);
                intent.putExtra("PmTemp", PmTemp);
                intent.putExtra("PmHumidity", PmHumidity);
                intent.putExtra("inTemp", inTemp);
                intent.putExtra("inHumidity", inHumidity);
                intent.putExtra("outTemp", outTemp);
                intent.putExtra("outHumidity", outHumidity);
                intent.putExtra("custodian", custodian);
                intent.putExtra("Taken", Taken);
                intent.putExtra("remark", remark);
                startActivity(intent);
                finish();
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

}
