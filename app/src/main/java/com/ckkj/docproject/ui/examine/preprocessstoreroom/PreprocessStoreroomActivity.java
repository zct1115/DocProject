package com.ckkj.docproject.ui.examine.preprocessstoreroom;

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
import com.ckkj.docproject.adapter.PreprocessAdapter;
import com.ckkj.docproject.base.BaseActivity;
import com.ckkj.docproject.bean.examine.Preprocess;
import com.ckkj.docproject.contract.PreprocessContract;
import com.ckkj.docproject.contract.PreprocessStoreroomContract;
import com.ckkj.docproject.presenter.examine.PreprocessPresenter;
import com.ckkj.docproject.presenter.examine.PreprocessStoreroomPresenter;
import com.ckkj.docproject.ui.examine.ExamineActivity;
import com.ckkj.docproject.ui.examine.preprocess.PreprocessDetilsActivity;

import java.util.List;

import butterknife.BindView;

public class PreprocessStoreroomActivity extends BaseActivity<PreprocessStoreroomContract.Presenter> implements PreprocessStoreroomContract.View {


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
    private PreprocessAdapter preprocessAdapter = new PreprocessAdapter();

    @Override
    public int getContentViewId() {
        return R.layout.entitive_content;
    }

    @Override
    protected void initAllMembers(Bundle savedInstanceState) {
      initToolbar("预归库审核");
        initPresenter();
    }

    private void initPresenter() {
        preprocessAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Object object = adapter.getItem(position);
                Preprocess.DataBean databean = object instanceof Preprocess.DataBean ? ((Preprocess.DataBean) object) : null;
                if (databean != null) {
                    PreprocessDetilsActivity.start(PreprocessStoreroomActivity.this, position, databean);
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
        preprocessAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {

                if (pag != 0 && pageNum < pag) {
                    mPresenter.getData(String.valueOf(pageNum), String.valueOf(mLimits));
                    pageNum += 1;
                } else {
                    preprocessAdapter.loadMoreEnd();
                }
                preprocessAdapter.loadMoreEnd();
            }
        }, contentRecview);

        contentRecview.setLayoutManager(new LinearLayoutManager(this));
        contentRecview.setAdapter(preprocessAdapter);
    }

    @Override
    protected PreprocessStoreroomContract.Presenter getPresenter() {
        return new PreprocessStoreroomPresenter(this);
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
                startActivity(new Intent(PreprocessStoreroomActivity.this, ExamineActivity.class));
                finish();
            }
        });

        return toolbar;
    }

    @Override
    public void setPreprocessStoreroomData(List<Preprocess.DataBean> data, int total, int pagNum) {
        preprocessAdapter.setEmptyView(R.layout.rec_empty_view);
        mTotal = total;
        pag = pagNum;
        if (data != null) {
            if (pageNum <= pagNum) {
                if (pageNum == 1) {
                    if (swipeLayout.isRefreshing()) {
                        swipeLayout.setRefreshing(false);
                    }
                    preprocessAdapter.setNewData(data);
                } else {
                    preprocessAdapter.addData(data);
                }
            }
            preprocessAdapter.loadMoreComplete();
        }
    }



    @Override
    public void getPreprocessStoreroomDataFail(String message) {

    }

    @Override
    public void sendMessage(String msg) {

    }

    @Override
    public void getStatus(String status) {

    }
}
