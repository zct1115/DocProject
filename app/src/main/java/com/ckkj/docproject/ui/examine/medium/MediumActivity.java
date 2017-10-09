package com.ckkj.docproject.ui.examine.medium;

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
import com.ckkj.docproject.adapter.MediumAdapter;
import com.ckkj.docproject.base.BaseActivity;
import com.ckkj.docproject.bean.examine.Medium;
import com.ckkj.docproject.contract.MediumContract;
import com.ckkj.docproject.presenter.examine.MediumPresenter;
import com.ckkj.docproject.ui.examine.ExamineActivity;
import com.ckkj.docproject.ui.examine.medium.MediumDetilsActivity;

import java.util.List;

import butterknife.BindView;

public class MediumActivity extends BaseActivity<MediumContract.Presenter> implements MediumContract.View {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.content_recview)
    RecyclerView contentRecview;
    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout swipeLayout;
    @BindView(R.id.e_content)
    LinearLayout eContent;

    private int pag = 0;
    private int mTotal = 1;
    private int pageNum = 1;
    private final int mLimits = 10;
    private MediumAdapter presenterAdapter = new MediumAdapter();

    @Override
    public int getContentViewId() {
        return R.layout.entitive_content;
    }

    @Override
    protected void initAllMembers(Bundle savedInstanceState) {
      initToolbar("现行文件审核");
        initPresenter();
    }

    private void initPresenter() {
        presenterAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Object object = adapter.getItem(position);
                Medium.DataBean databean = object instanceof Medium.DataBean ? ((Medium.DataBean) object) : null;
                if (databean != null) {
                    MediumDetilsActivity.start(MediumActivity.this, position, databean);
                }
            }
        });
        //设置 SwipeRefreshLayout 的尺寸
        swipeLayout.setSize(SwipeRefreshLayout.DEFAULT);
        //设置 SwipeRefreshLayout 刷新时的颜色切换（可以有无数种）
        swipeLayout.setColorSchemeColors(Color.BLUE);
        //设置 SwipeRefreshLayout 的下拉距离
        swipeLayout.setDistanceToTriggerSync(100);
        //设置 SwipeRefreshLayout 正在刷新监听
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageNum = 1;
                mPresenter.getData(String.valueOf(pageNum), String.valueOf(mLimits));
                if (swipeLayout.isRefreshing()) {
                    swipeLayout.setRefreshing(false);
                }
            }
        });
        //上拉加载
        presenterAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {

                if (pag != 0 && pageNum < pag) {
                    mPresenter.getData(String.valueOf(pageNum), String.valueOf(mLimits));
                    pageNum += 1;
                } else {
                    presenterAdapter.loadMoreEnd();
                }
            }
        }, contentRecview);

        contentRecview.setLayoutManager(new LinearLayoutManager(this));
        contentRecview.setAdapter(presenterAdapter);
    }

    @Override
    protected MediumContract.Presenter getPresenter() {
        return new MediumPresenter(this);
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
                startActivity(new Intent(MediumActivity.this, ExamineActivity.class));
                finish();
            }
        });

        return toolbar;
    }

    @Override
    public void setMediumData(List<Medium.DataBean> data, int total, int pagNum) {
        presenterAdapter.setEmptyView(R.layout.rec_empty_view);
        mTotal = total;
        pag = pagNum;
        if (data != null) {
            if (pageNum <= pagNum) {
                if (pageNum == 1) {
                    if (swipeLayout.isRefreshing()) {
                        swipeLayout.setRefreshing(false);
                    }
                    presenterAdapter.setNewData(data);
                } else {
                    presenterAdapter.addData(data);
                }

            }
            presenterAdapter.loadMoreComplete();
        }
    }

    @Override
    public void getMediumDataFail(String message) {

    }

    @Override
    public void sendMessage(String msg) {

    }

    @Override
    public void getStatus(String status) {

    }
}
