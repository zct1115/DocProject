package com.ckkj.docproject.ui.archives;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ckkj.docproject.R;
import com.ckkj.docproject.adapter.ArchivesRecAdapter;
import com.ckkj.docproject.base.BaseActivity;
import com.ckkj.docproject.bean.ArchivesData;
import com.ckkj.docproject.contract.ArchivesContract;
import com.ckkj.docproject.event.MessageEvent;
import com.ckkj.docproject.presenter.ArchivesPresenter;
import com.ckkj.docproject.ui.archives.ArchivesDetailActivity;
import com.ckkj.docproject.ui.home.MainActivity;
import com.ckkj.docproject.ui.archives.SearchActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;

public class ArchivesActivity extends BaseActivity<ArchivesContract.Presenter> implements ArchivesContract.View, View.OnClickListener {
    private static final int REQUEST_CODE_ARCHIVES = 0x0001;
    private static final int FLAG_DATA_BY_TREE = 0x1001;
    private static final int FLAG_DATA_BY_SEARCH = 0x1002;
    private int mFlag;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.navigation_layout)
    LinearLayout mNavigation;
    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.content_recview)
    RecyclerView mArchivesRec;
    @BindView(R.id.nav_logout)
    TextView mLogout;
    private ArchivesRecAdapter mArchivesRecAdapter;
    private String mTableName;
    private String mTableNameCH;
    private int mTotal = 1;
    private int pageNum = 0;
    private String catalogueId;
    private final int mLimits = 20;
    private String mSearchCondition;
    private int pag=0;


    @Override
    public int getContentViewId() {
        return R.layout.activity_archives;
    }

    @Override
    protected ArchivesContract.Presenter getPresenter() {
        return new ArchivesPresenter(this);
    }

    @Override
    protected void initAllMembers(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        setSupportActionBar(mToolbar);
        initDrawerLayout();
        initArchivesRec();
        mLogout.setOnClickListener(this);
        //获取档案门类树状视图数据
        mPresenter.getTreeViewData();
    }

    private void initDrawerLayout() {
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                mDrawerLayout,
                mToolbar,
                R.string.toggle_open, R.string.toggle_close);
        toggle.syncState();
        mDrawerLayout.addDrawerListener(toggle);
        mDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerStateChanged(int newState) {
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                View mContent = mDrawerLayout.getChildAt(0);
                View mMenu = drawerView;
                float scale = 1 - slideOffset;
                mContent.setTranslationX(mMenu.getMeasuredWidth() * (1 - scale));
                mContent.setPivotX(0);
                mContent.setPivotY(mContent.getMeasuredHeight() / 2);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                mDrawerLayout.setDrawerLockMode(
                        DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.RIGHT);
            }
        });
    }

    private void initArchivesRec() {
        mArchivesRecAdapter = new ArchivesRecAdapter();
        //档案条目点击事件，跳转到条目详情
        mArchivesRecAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Object data = adapter.getItem(position);

                ArchivesData.DataBean dataBean = data instanceof ArchivesData.DataBean ? ((ArchivesData.DataBean) data) : null;
                if (dataBean != null) {
                    ArchivesDetailActivity.start(ArchivesActivity.this, dataBean.getId(), dataBean.getTableName());
                }
            }
        });
        //上拉加载
        mArchivesRecAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {

                if (pag!=0&&pageNum < pag) {
                    pageNum += 1;
                    if (mFlag == FLAG_DATA_BY_TREE) {
                        mPresenter.getArchivesData(mTableName,catalogueId, String.valueOf(pageNum), String.valueOf(mLimits));
                    } else if (mFlag == FLAG_DATA_BY_SEARCH) {
                        mPresenter.submitQuery(mTableName, String.valueOf(pageNum), String.valueOf(mLimits), mSearchCondition);
                    }
                }
                mArchivesRecAdapter.loadMoreEnd();
               /* pageNum += mLimits;
                if (pageNum < mTotal) {
                    if (mFlag == FLAG_DATA_BY_TREE) {
                        mPresenter.getArchivesData(mTableName,catalogueId, String.valueOf(pageNum), String.valueOf(mLimits));
                    } else if (mFlag == FLAG_DATA_BY_SEARCH) {
                        mPresenter.submitQuery(mTableName, String.valueOf(pageNum), String.valueOf(mLimits), mSearchCondition);
                    }
                } else {
                    mArchivesRecAdapter.loadMoreEnd();
                }*/
            }
        }, mArchivesRec);
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
                    mPresenter.getArchivesData(mTableName,catalogueId, String.valueOf(pageNum), String.valueOf(mLimits));
                } else if (mFlag == FLAG_DATA_BY_SEARCH) {
                    mPresenter.submitQuery(mTableName, String.valueOf(pageNum), String.valueOf(mLimits), mSearchCondition);
                }
                if (mSwipeRefreshLayout.isRefreshing()) {
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }
        });


        mArchivesRec.setLayoutManager(new LinearLayoutManager(this));
        mArchivesRec.setAdapter(mArchivesRecAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                mFlag=0;
                Intent intent = new Intent(ArchivesActivity.this, SearchActivity.class);
                mArchivesRecAdapter.setEmptyView(R.layout.item_content_archive);
                intent.putExtra("tableName", mTableNameCH);
                startActivityForResult(intent, REQUEST_CODE_ARCHIVES);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //从搜索页面返回时拿到查询条件，并请求查询结果
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_ARCHIVES && resultCode == RESULT_OK) {
            pageNum = 1;
            mFlag = FLAG_DATA_BY_SEARCH;
            mSearchCondition = data.getStringExtra("searchCondition");
            mToolbar.setTitle(mTableNameCH + "--查询结果");
           // mArchivesRecAdapter.removeAllFooterView();
            initArchivesRec();
            //mArchivesRecAdapter.setEmptyView(R.layout.rec_empty_view);
            mPresenter.submitQuery(mTableName, String.valueOf(pageNum), String.valueOf(mLimits), mSearchCondition);
        }
    }

    //获取档案门类树状视图后显示
    @Override
    public void setTreeViewData(View view) {
        mNavigation.addView(view, 0);
    }

    //获取档案门类下的档案数据后显示
    @Override
    public void setArchivesData(final List<ArchivesData.DataBean> data, final int total,int pageNuma) {
        mArchivesRecAdapter.setEmptyView(R.layout.rec_empty_view);
        mTotal = total;
        Log.d("ArchivesActivity", "mTotal:" + mTotal);
        mToolbar.setTitle(mTableNameCH);
        pag=pageNuma;
        if(data!=null) {

            if(pageNum<=pageNuma) {
                if (pageNum == 1) {
                    //手动停止下拉刷新
                    if (mSwipeRefreshLayout.isRefreshing()) {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                    mArchivesRecAdapter.setNewData(data);
                } else {
                    mArchivesRecAdapter.addData(data);
                }
            }
        }else {
            initArchivesRec();
            //Toast.makeText(this, "没数据", Toast.LENGTH_SHORT).show();
            //mArchivesRecAdapter.notifyDataSetChanged();
        }
        mArchivesRecAdapter.loadMoreComplete();
    }

    //获取档案数据失败
    @Override
    public void getArchivesDataFail(String msg) {
        Log.d("ArchivesActivity", "mArchivesRecAdapter.getItemCount():" + mArchivesRecAdapter.getItemCount());

        /*for(int i=0;i<20;i++){
            mArchivesRecAdapter.remove(i);
        }*/

        mArchivesRecAdapter.loadMoreFail();
        showSnackBar(mDrawerLayout, msg);

    }



    //treeview点击条目后
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent messageEvent) {
        pageNum = 1;
        catalogueId=messageEvent.getCatalogueId();
        mFlag = FLAG_DATA_BY_TREE;
        mTableName = messageEvent.getTableName();
        mTableNameCH = messageEvent.getTableNameCH();
        mPresenter.getArchivesData(mTableName,catalogueId, String.valueOf(pageNum), String.valueOf(mLimits));

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.nav_logout:
              //  mPresenter.logout();
                Intent intent = new Intent(ArchivesActivity.this,  MainActivity.class);
              //  intent.putExtra("autoLogin", false);
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }
    }
}
