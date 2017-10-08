package com.ckkj.docproject.ui.cupboard.cup;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ckkj.docproject.R;
import com.ckkj.docproject.adapter.CupBoardAdapter;
import com.ckkj.docproject.base.BaseActivity;
import com.ckkj.docproject.bean.cupboard.CupBoardData;
import com.ckkj.docproject.contract.CupBoardContract;
import com.ckkj.docproject.database.CupBoardDaoUtils;
import com.ckkj.docproject.presenter.cupboard.CupBoardPresenter;
import com.ckkj.docproject.ui.cupboard.cup.AddCupBoardActivity;
import com.ckkj.docproject.ui.cupboard.cup.CupBoardDetailActivity;
import com.ckkj.docproject.ui.cupboard.cup.SearchCupBoardActivity;
import com.ckkj.docproject.ui.cupboard.StorehouseActivity;
import com.ckkj.docproject.utils.NetUtils;
import com.google.gson.Gson;

import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class CupBoardActivity extends BaseActivity<CupBoardContract.Presenter> implements CupBoardContract.View {

    private static final int FLAG_DATA_BY_TREE = 0x1001;
    private static final int FLAG_DATA_BY_SEARCH = 0x1002;
    @BindView(R.id.content)
    LinearLayout layout_content;
    private int mFlag;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.content_recview)
    RecyclerView mCupBoard;

    private CupBoardAdapter  cupBoardAdapter = new CupBoardAdapter();
    private int mTotal = 1;
    private int pageNum = 0;
    private final int mLimits = 10;

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
        initToolbar("柜架管理");
        initArchivesRec();
        mPresenter.getData(String.valueOf(pageNum+1), String.valueOf(mLimits));

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
                startActivity(new Intent(CupBoardActivity.this,StorehouseActivity.class));
                finish();
            }
        });
        return toolbar;
    }


    private void initArchivesRec() {
        //柜架条目点击事件，跳转到条目详情
        cupBoardAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Object data = adapter.getItem(position);
                CupBoardData.DataBean dataBean = data instanceof CupBoardData.DataBean ? ((CupBoardData.DataBean) data) : null;
                if (dataBean != null) {
                    CupBoardDetailActivity.start(CupBoardActivity.this,position,dataBean);
                }
            }
        });

        //上拉加载
        cupBoardAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (pag!=0&&pageNum < pag) {
                    pageNum += 1;
                    if (mFlag == FLAG_DATA_BY_TREE) {
                        mPresenter.getData(String.valueOf(pageNum), String.valueOf(mLimits));
                    }
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
                if (mFlag == FLAG_DATA_BY_TREE) {
                    mPresenter.getData(String.valueOf(pageNum), String.valueOf(mLimits));
                   // mPresenter.searchData(Dataname,Datavalues);
                }
                if (mSwipeRefreshLayout.isRefreshing()) {
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }
        });
        mCupBoard.setItemAnimator(new DefaultItemAnimator());
        mCupBoard.setLayoutManager(new LinearLayoutManager(this));
        mCupBoard.setAdapter(cupBoardAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        SubMenu subMenu = menu.addSubMenu("");

        subMenu.add("登记柜架").setIcon(R.drawable.ic_add).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            public boolean onMenuItemClick(MenuItem item) {
                startActivity(new Intent(CupBoardActivity.this, AddCupBoardActivity.class));
                return false;
            }
        });
        subMenu.add("搜索柜架").setIcon(R.drawable.ic_search).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            public boolean onMenuItemClick(MenuItem item) {
                startActivity(new Intent(CupBoardActivity.this, SearchCupBoardActivity.class));
                return false;
            }
        });
        subMenu.add("提交离线数据").setIcon(R.drawable.ic_off_line).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {


            public boolean onMenuItemClick(MenuItem item) {
                final CupBoardDaoUtils dao = new CupBoardDaoUtils(CupBoardActivity.this);
                //final List<CupBoard> list= dao.listAll();
                final List<Map<String, Object>> list = dao.MapAll();
                if (list.size() != 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(CupBoardActivity.this);
                    builder.setMessage("确定提交离线登记的"+dao.cupboardCount()+"条数据？")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (NetUtils.hasNetWorkConection(CupBoardActivity.this)) {

                                        mPresenter.offtaken(new Gson().toJson(list));
                                        Toast.makeText(CupBoardActivity.this, "提交成功，请刷新页面", Toast.LENGTH_SHORT).show();
                                        dao.deleteCupboard();
                                    }else {
                                        Toast.makeText(CupBoardActivity.this, "当前无网络，请检查当前网络是否连接。", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            })
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();

                                }
                            }).show();


                } else {
                    Toast.makeText(CupBoardActivity.this, "没有离线数据，无法提交", Toast.LENGTH_SHORT).show();
                }
                dao.close();


                return false;
            }
        });

        MenuItem item = subMenu.getItem();
        item.setIcon(R.drawable.ic_more);
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void setCupBoardData(List<CupBoardData.DataBean> data,final int total,int pagNume) {
        cupBoardAdapter.setEmptyView(R.layout.rec_empty_view);
        mFlag = FLAG_DATA_BY_TREE;
        mTotal=total;
        pag=pagNume;
        if(data!=null) {
            if(pageNum<=pagNume) {
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
