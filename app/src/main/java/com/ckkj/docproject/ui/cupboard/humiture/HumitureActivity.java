package com.ckkj.docproject.ui.cupboard.humiture;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
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
import com.ckkj.docproject.adapter.HumitureAdapter;
import com.ckkj.docproject.base.BaseActivity;
import com.ckkj.docproject.bean.cupboard.HumitureData;
import com.ckkj.docproject.contract.HumitureContract;
import com.ckkj.docproject.database.HumitureDaoUtils;
import com.ckkj.docproject.presenter.cupboard.HumiturePresenter;
import com.ckkj.docproject.ui.cupboard.humiture.AddHumitureActivity;
import com.ckkj.docproject.ui.cupboard.humiture.HumitureDetailActivity;
import com.ckkj.docproject.ui.cupboard.humiture.SearchHumitureActivity;
import com.ckkj.docproject.ui.cupboard.StorehouseActivity;
import com.ckkj.docproject.utils.NetUtils;
import com.greendao.entity.Humiture;

import java.util.List;

import butterknife.BindView;

public class HumitureActivity extends BaseActivity<HumitureContract.Presenter> implements HumitureContract.View {

    private static final int FLAG_DATA_BY_TREE = 0x1001;
    @BindView(R.id.hum_content)
    LinearLayout layout_content;
    private int mFlag;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.content_recview)
    RecyclerView mhumiture;

    private HumitureAdapter humitureAdapter = new HumitureAdapter();
    private int mTotal = 1;
    private int pageNum = 0;
    private final int mLimits = 10;
    private int pag=0;

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
        initToolbar("温湿度管理");
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
                startActivity(new Intent(HumitureActivity.this,StorehouseActivity.class));
                finish();
            }
        });
        return toolbar;
    }

    private void initArchivesRec() {
        //柜架条目点击事件，跳转到条目详情
        humitureAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Object data = adapter.getItem(position);
                HumitureData.DataBean dataBean = data instanceof HumitureData.DataBean ? ((HumitureData.DataBean) data) : null;
                if (dataBean != null) {
                    HumitureDetailActivity.start(HumitureActivity.this,position,dataBean);
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
                        mPresenter.getData( String.valueOf(pageNum), String.valueOf(mLimits));
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
                    mPresenter.getData( String.valueOf(pageNum), String.valueOf(mLimits));
                    //mPresenter.getArchivesData(mTableName,catalogueId, String.valueOf(pageNum), String.valueOf(mLimits));
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
    public boolean onCreateOptionsMenu(Menu menu) {

        SubMenu subMenu = menu.addSubMenu("");

        subMenu.add("温湿度登记").setIcon(R.drawable.ic_add).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            public boolean onMenuItemClick(MenuItem item) {
                startActivity(new Intent(HumitureActivity.this, AddHumitureActivity.class));
                return false;
            }
        });
        subMenu.add("搜索温湿度").setIcon(R.drawable.ic_search).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            public boolean onMenuItemClick(MenuItem item) {
               startActivity(new Intent(HumitureActivity.this, SearchHumitureActivity.class));
                return false;
            }
        });
        subMenu.add("提交离线数据").setIcon(R.drawable.ic_off_line).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {


            public boolean onMenuItemClick(MenuItem item) {
                final HumitureDaoUtils dao = new HumitureDaoUtils(HumitureActivity.this);
                final List<Humiture> list = dao.humiturelistAll();
                if (list.size() != 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(HumitureActivity.this);
                    builder.setMessage("确定提交离线登记的" + dao.humitureCount() + "条数据？")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (NetUtils.hasNetWorkConection(HumitureActivity.this)) {
                                        for (Humiture humiture : list) {
                                            mPresenter.addData(humiture.getCheckData(), humiture.getAmTemp(), humiture.getAmHumidity(), humiture.getPmTemp(), humiture.getPmHumidity(), humiture.getInTemp(), humiture.getInHumidity(), humiture.getOutTemp(), humiture.getOutHumidity(), humiture.getTaken(), humiture.getCustodian(), humiture.getRemark());
                                        }
                                        Toast.makeText(HumitureActivity.this, "提交成功，请刷新页面", Toast.LENGTH_SHORT).show();
                                        dao.deleteHumiture();
                                    } else {
                                        Toast.makeText(HumitureActivity.this, "当前无网络，请检查当前网络是否连接。", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            })
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            }).show();
                }else {
                    Toast.makeText(HumitureActivity.this, "没有离线数据，无法提交", Toast.LENGTH_SHORT).show();
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
    public void setHumitureData(List<HumitureData.DataBean> data,final int total,int pagNum) {
        humitureAdapter.setEmptyView(R.layout.rec_empty_view);
        mFlag = FLAG_DATA_BY_TREE;
        mTotal=total;
        pag=pagNum;
        if(data!=null) {
            if(pageNum<=pageNum) {
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

        }
        humitureAdapter.loadMoreComplete();

    }

    @Override
    public void getHumitureDataFail(String message) {
        humitureAdapter.loadMoreFail();
        showSnackBar(layout_content, message);
    }

    @Override
    public void sendMessage(String msg) {
        showSnackBar(layout_content,msg);
    }






}
