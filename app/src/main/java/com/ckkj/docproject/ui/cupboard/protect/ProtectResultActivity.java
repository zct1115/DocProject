package com.ckkj.docproject.ui.cupboard.protect;

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
import com.ckkj.docproject.adapter.ProtectAdapter;
import com.ckkj.docproject.base.BaseActivity;
import com.ckkj.docproject.bean.cupboard.ProtectData;
import com.ckkj.docproject.contract.ProtectContract;
import com.ckkj.docproject.presenter.cupboard.ProtectPresenter;
import com.ckkj.docproject.ui.cupboard.protect.ProtectActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;

public class ProtectResultActivity extends BaseActivity<ProtectContract.Presenter> implements ProtectContract.View, View.OnClickListener {

    private static final int REQUEST_CODE_ARCHIVES = 0x0001;
    private static final int FLAG_DATA_BY_TREE = 0x1001;
    private static final int FLAG_DATA_BY_SEARCH = 0x1002;
    @BindView(R.id.content_recview)
    RecyclerView mprotect;
    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.content)
    LinearLayout content;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private int mFlag;
    private ProtectAdapter protectAdapter;
    private int mTotal = 1;
    private int pageNum = 1;
    private final int mLimits = 10;

    private String StoreroomCode;
    private String StoreroomName;
    private String ProtectType;
    private String ProtectTime;
    private String ProtectResult;
    private String OperUser;

    private int pag = 0;

    @Override
    public int getContentViewId() {
        return R.layout.protect_content;
    }

    @Override
    protected ProtectContract.Presenter getPresenter() {
        return new ProtectPresenter(this);
    }

    @Override
    protected void initAllMembers(Bundle savedInstanceState) {
        //EventBus.getDefault().register(this);
        initToolbar("登记结果如下");
        initArchivesRec();

        Intent intent = getIntent();
        StoreroomCode = intent.getStringExtra("StoreroomCode");
        StoreroomName = intent.getStringExtra("StoreroomName");
        ProtectType = intent.getStringExtra("ProtectType");
        ProtectTime = intent.getStringExtra("ProtectTime");
        ProtectResult = intent.getStringExtra("ProtectResult");
        OperUser = intent.getStringExtra("OperUser");
        mPresenter.searchData(String.valueOf(pageNum), String.valueOf(mLimits),StoreroomCode, StoreroomName, ProtectType, ProtectTime, ProtectResult, OperUser);


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
                startActivity(new Intent(ProtectResultActivity.this, ProtectActivity.class));
                finish();
            }
        });

        return toolbar;
    }


    private void initArchivesRec() {
        protectAdapter = new ProtectAdapter();
        //柜架条目点击事件，跳转到条目详情
        protectAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                Object data = adapter.getItem(position);
                ProtectData.DataBean dataBean = data instanceof ProtectData.DataBean ? ((ProtectData.DataBean) data) : null;
                if (dataBean != null) {
                    ProtectDetailActivity.start(ProtectResultActivity.this, position, dataBean);
                }
            }
        });
        //上拉加载
       /* protectAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (pag != 0 && pageNum < pag) {
                    pageNum += 1;
                        mPresenter.searchData(String.valueOf(pageNum), String.valueOf(mLimits),StoreroomCode, StoreroomName, ProtectType, ProtectTime, ProtectResult, OperUser);
                        // mPresenter.getArchivesData(mTableName,catalogueId, String.valueOf(pageNum), String.valueOf(mLimits));
                }
                protectAdapter.loadMoreEnd();
            }
        }, mprotect);*/
        //设置 SwipeRefreshLayout 的尺寸
        mSwipeRefreshLayout.setSize(SwipeRefreshLayout.DEFAULT);
        //设置 SwipeRefreshLayout 刷新时的颜色切换（可以有无数种）
        mSwipeRefreshLayout.setColorSchemeColors(Color.BLUE);
        //设置 SwipeRefreshLayout 的下拉距离
        mSwipeRefreshLayout.setDistanceToTriggerSync(100);
        //设置 SwipeRefreshLayout 正在刷新监听
        /*mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageNum = 1;
                //mPresenter.getData(String.valueOf(pageNum), String.valueOf(mLimits));
                if (mFlag == FLAG_DATA_BY_TREE) {
                    mPresenter.searchData(String.valueOf(pageNum), String.valueOf(mLimits),StoreroomCode, StoreroomName, ProtectType, ProtectTime, ProtectResult, OperUser);
                    //mPresenter.getArchivesData(mTableName,catalogueId, String.valueOf(pageNum), String.valueOf(mLimits));
                } else if (mFlag == FLAG_DATA_BY_SEARCH) {
                    // mPresenter.submitQuery(mTableName, String.valueOf(pageNum), String.valueOf(mLimits), mSearchCondition);
                }
                if (mSwipeRefreshLayout.isRefreshing()) {
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }
        });*/
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageNum = 1;
                mPresenter.searchData(String.valueOf(pageNum), String.valueOf(mLimits),StoreroomCode, StoreroomName, ProtectType, ProtectTime, ProtectResult, OperUser);
//                if (mSwipeRefreshLayout.isRefreshing()) {
//                    mSwipeRefreshLayout.setRefreshing(false);
//                }
            }
        });
        protectAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {

                if (pag != 0 && pageNum < pag) {
                    mPresenter.searchData(String.valueOf(pageNum), String.valueOf(mLimits),StoreroomCode, StoreroomName, ProtectType, ProtectTime, ProtectResult, OperUser);
                    pageNum += 1;
                } else {
                    protectAdapter.loadMoreEnd();
                }
            }
        }, mprotect);


        mprotect.setLayoutManager(new LinearLayoutManager(this));
        mprotect.setAdapter(protectAdapter);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void setProtectData(List<ProtectData.DataBean> data, final int total, int pagNum) {
        protectAdapter.setEmptyView(R.layout.rec_empty_view);
        mFlag = FLAG_DATA_BY_TREE;
        mTotal = total;
        pag = pagNum;
        if (data != null) {
            if (pageNum <= pagNum) {
                if (pageNum == 1) {
                    //手动停止下拉刷新
                    if (mSwipeRefreshLayout.isRefreshing()) {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                    protectAdapter.setNewData(data);
                } else {
                    protectAdapter.addData(data);
                }
            }
            protectAdapter.loadMoreComplete();
        }
    }

    @Override
    public void getProtectDataFail(String message) {
        protectAdapter.loadMoreFail();
        showSnackBar(content, message);
    }

    @Override
    public void sendMessage(String msg) {
        showSnackBar(content, msg);
    }


}
