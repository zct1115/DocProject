package com.ckkj.docproject.ui.cupboard.cup;

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
import com.ckkj.docproject.adapter.CupBoardAdapter;
import com.ckkj.docproject.base.BaseActivity;
import com.ckkj.docproject.bean.cupboard.CupBoardData;
import com.ckkj.docproject.contract.CupBoardContract;
import com.ckkj.docproject.presenter.cupboard.CupBoardPresenter;
import com.ckkj.docproject.ui.cupboard.cup.CupBoardActivity;
import com.ckkj.docproject.ui.cupboard.cup.CupBoardDetailActivity;

import java.util.List;

import butterknife.BindView;

public class CupBoardResultActivity extends BaseActivity<CupBoardContract.Presenter> implements CupBoardContract.View {

    private static final int REQUEST_CODE_ARCHIVES = 0x0001;
    private static final int FLAG_DATA_BY_TREE = 0x1001;
    private static final int FLAG_DATA_BY_SEARCH = 0x1002;
    @BindView(R.id.content)
    LinearLayout layout_content;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.content_recview)
    RecyclerView mCupBoard;

    private CupBoardAdapter cupBoardAdapter;
    private int mTotal = 1;
    private int pageNum = 0;
    private final int mLimits = 10;

    private String Datavalues;
    private String Dataname;


    private int pag=0;

    @Override
    public int getContentViewId() {
        return R.layout.cupboard_content;
    }

    @Override
    protected CupBoardContract.Presenter getPresenter() {
        return new CupBoardPresenter(this);
    }

    @Override
    protected void initAllMembers(Bundle savedInstanceState) {
        initToolbar("柜架查询结果");
        initArchivesRec();

        Intent intent=getIntent();
        Dataname=intent.getStringExtra("Dataname");
        Datavalues=intent.getStringExtra("Datavalues");
        mPresenter.searchData(String.valueOf(pageNum+1), String.valueOf(mLimits),Dataname,Datavalues);
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
                startActivity(new Intent(CupBoardResultActivity.this,CupBoardActivity.class));
                finish();
            }
        });
        return toolbar;
    }


    private void initArchivesRec() {
        cupBoardAdapter = new CupBoardAdapter();
        //柜架条目点击事件，跳转到条目详情
        cupBoardAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                Object data = adapter.getItem(position);
                CupBoardData.DataBean dataBean = data instanceof CupBoardData.DataBean ? ((CupBoardData.DataBean) data) : null;
                if (dataBean != null) {
                    CupBoardDetailActivity.start(CupBoardResultActivity.this,position,dataBean);
                }
            }
        });
        //上拉加载
        cupBoardAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (pag!=0&&pageNum < pag) {
                    pageNum += 1;
                    mPresenter.searchData(String.valueOf(pageNum), String.valueOf(mLimits),Dataname,Datavalues);
                }
                cupBoardAdapter.loadMoreEnd();
            }
        }, mCupBoard);
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
                mPresenter.searchData(String.valueOf(pageNum), String.valueOf(mLimits),Dataname,Datavalues);
                if (mSwipeRefreshLayout.isRefreshing()) {
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }
        });


        mCupBoard.setLayoutManager(new LinearLayoutManager(this));
        mCupBoard.setAdapter(cupBoardAdapter);
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
    }



    @Override
    public void setCupBoardData(List<CupBoardData.DataBean> data,final int total,int pagName) {
        cupBoardAdapter.setEmptyView(R.layout.rec_empty_view);
        pag=pagName;
        mTotal=total;
        if(data!=null) {
            if(pageNum<=pagName) {
                if (pageNum == 1) {
                    //手动停止下拉刷新
                    if (mSwipeRefreshLayout.isRefreshing()) {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                    cupBoardAdapter.setNewData(data);
                } else {
                    cupBoardAdapter.addData(data);
                }
            }
            cupBoardAdapter.loadMoreComplete();
        }

    }

    @Override
    public void getCupBoardDataFail(String message) {
        cupBoardAdapter.loadMoreFail();
        showSnackBar(layout_content, message);
    }

    @Override
    public void sendMessage(String msg) {
        showSnackBar(layout_content, msg);
    }


}
