package com.ckkj.docproject.ui.cupboard.humiture;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ckkj.docproject.R;
import com.ckkj.docproject.adapter.HumitureAdapter;
import com.ckkj.docproject.base.BaseActivity;
import com.ckkj.docproject.bean.cupboard.HumitureData;
import com.ckkj.docproject.contract.HumitureContract;
import com.ckkj.docproject.presenter.cupboard.HumiturePresenter;
import com.ckkj.docproject.ui.cupboard.humiture.HumitureActivity;
import com.ckkj.docproject.ui.cupboard.humiture.HumitureDetailActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;

public class HumitureResultActivity extends BaseActivity<HumitureContract.Presenter> implements HumitureContract.View {

    private static final int REQUEST_CODE_ARCHIVES = 0x0001;
    private static final int FLAG_DATA_BY_TREE = 0x1001;
    private static final int FLAG_DATA_BY_SEARCH = 0x1002;
    @BindView(R.id.hum_content)
    LinearLayout layout_content;
    private int mFlag;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.content_recview)
    RecyclerView mhumiture;

    private int pag=0;

    private HumitureAdapter humitureAdapter;
    private int mTotal = 1;
    private int pageNum = 1;
    private final int mLimits = 10;


    private String checkData;
    private String AmTemp;
    private String AmHumidity;
    private String PmTemp;
    private String PmHumidity;
    private String inTemp;
    private String inHumidity;
    private String outTemp;
    private String outHumidity;
    private String Taken;
    private String custodian;
    private String remark;

    @Override
    public int getContentViewId() {
        return R.layout.humiture_content;
    }

    @Override
    protected HumitureContract.Presenter getPresenter() {
        return new HumiturePresenter(this);
    }

    @Override
    protected void initAllMembers(Bundle savedInstanceState) {
        initToolbar("温湿度查询结果");
        initArchivesRec();
        Intent intent=getIntent();
        checkData=intent.getStringExtra("checkData");
        AmTemp=intent.getStringExtra("AmTemp");
        AmHumidity=intent.getStringExtra("AmHumidity");
        PmTemp=intent.getStringExtra("PmTemp");
        PmHumidity=intent.getStringExtra("PmHumidity");
        inTemp=intent.getStringExtra("inTemp");
        inHumidity=intent.getStringExtra("inHumidity");
        outTemp=intent.getStringExtra("outTemp");
        outHumidity=intent.getStringExtra("outHumidity");
        custodian=intent.getStringExtra("custodian");
        Taken=intent.getStringExtra("Taken");
        remark=intent.getStringExtra("remark");
        mPresenter.searchData(checkData, AmTemp, AmHumidity, PmTemp, PmHumidity, inTemp, inHumidity, outTemp, outHumidity, Taken, custodian, remark);
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
                startActivity(new Intent(HumitureResultActivity.this, HumitureActivity.class));
                finish();
            }
        });

        return toolbar;
    }


    private void initArchivesRec() {
        humitureAdapter = new HumitureAdapter();
        //柜架条目点击事件，跳转到条目详情
        humitureAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                Object data = adapter.getItem(position);
                HumitureData.DataBean dataBean = data instanceof HumitureData.DataBean ? ((HumitureData.DataBean) data) : null;
                if (dataBean != null) {
                    HumitureDetailActivity.start(HumitureResultActivity.this, position, dataBean);
                }
            }
        });
        //上拉加载
        humitureAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (pag!=0&&pageNum < pag) {
                    pageNum += 1;
                    if (mFlag == FLAG_DATA_BY_TREE) {
                        mPresenter.searchData(checkData, AmTemp, AmHumidity, PmTemp, PmHumidity, inTemp, inHumidity, outTemp, outHumidity, Taken, custodian, remark);
                        // mPresenter.getArchivesData(mTableName,catalogueId, String.valueOf(pageNum), String.valueOf(mLimits));
                    }
                }
                humitureAdapter.loadMoreEnd();
            }
        }, mhumiture);
        //设置 SwipeRefreshLayout 的尺寸
        mSwipeRefreshLayout.setSize(SwipeRefreshLayout.DEFAULT);
        //设置 SwipeRefreshLayout 刷新时的颜色切换（可以有无数种）
        mSwipeRefreshLayout.setColorSchemeColors(Color.BLUE);
        //设置 SwipeRefreshLayout 的下拉距离
        mSwipeRefreshLayout.setDistanceToTriggerSync(100);
        //设置 SwipeRefreshLayout 正在刷新监听
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageNum = 1;
                if (mFlag == FLAG_DATA_BY_TREE) {
                    mPresenter.searchData(checkData, AmTemp, AmHumidity, PmTemp, PmHumidity, inTemp, inHumidity, outTemp, outHumidity, Taken, custodian, remark);
                    //mPresenter.getArchivesData(mTableName,catalogueId, String.valueOf(pageNum), String.valueOf(mLimits));
                } else if (mFlag == FLAG_DATA_BY_SEARCH) {
                    // mPresenter.submitQuery(mTableName, String.valueOf(pageNum), String.valueOf(mLimits), mSearchCondition);
                }
                if (mSwipeRefreshLayout.isRefreshing()) {
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }
        });


        mhumiture.setLayoutManager(new LinearLayoutManager(this));
        mhumiture.setAdapter(humitureAdapter);
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }


    @Override
    public void setHumitureData(List<HumitureData.DataBean> data, final int total,int pagNuma) {
        humitureAdapter.setEmptyView(R.layout.rec_empty_view);
        mFlag = FLAG_DATA_BY_TREE;
        mTotal = total;
        pag=pagNuma;
        if (data != null) {
            if(pageNum<=pagNuma) {
                if (pageNum == 1) {
                    //手动停止下拉刷新
                    if (mSwipeRefreshLayout.isRefreshing()) {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                    humitureAdapter.setNewData(data);
                } else {
                    humitureAdapter.addData(data);
                }
            }
            humitureAdapter.loadMoreComplete();
        }

    }

    @Override
    public void getHumitureDataFail(String message) {
        humitureAdapter.loadMoreFail();
        showSnackBar(layout_content, message);
    }

    @Override
    public void sendMessage(String msg) {
        showSnackBar(layout_content, msg);
    }


}
