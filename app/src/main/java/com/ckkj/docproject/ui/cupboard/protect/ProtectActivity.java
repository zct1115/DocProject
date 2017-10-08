package com.ckkj.docproject.ui.cupboard.protect;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
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
import com.ckkj.docproject.adapter.ProtectAdapter;
import com.ckkj.docproject.base.BaseActivity;
import com.ckkj.docproject.bean.cupboard.ProtectData;
import com.ckkj.docproject.contract.ProtectContract;
import com.ckkj.docproject.database.ProtectDaoUtils;
import com.ckkj.docproject.presenter.cupboard.ProtectPresenter;
import com.ckkj.docproject.ui.cupboard.protect.AddProtectActivity;
import com.ckkj.docproject.ui.cupboard.protect.ProtectDetailActivity;
import com.ckkj.docproject.ui.cupboard.protect.SearchProtectActivity;
import com.ckkj.docproject.ui.cupboard.StorehouseActivity;
import com.ckkj.docproject.utils.NetUtils;
import com.greendao.entity.Protect;

import java.util.List;

import butterknife.BindView;

public class ProtectActivity extends BaseActivity<ProtectContract.Presenter> implements ProtectContract.View {

    private static final int FLAG_DATA_BY_TREE = 0x1001;
    @BindView(R.id.hum_content)
    LinearLayout hum_content;
    private int mFlag;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.content_recview)
    RecyclerView mprotect;
    private int pag = 0;

    private ProtectAdapter protectAdapter = new ProtectAdapter();
    private int mTotal = 1;
    private int pageNum = 1;
    private final int mLimits = 10;

    @Override
    public int getContentViewId() {
        return R.layout.humiture_content;
    }

    @Override
    protected ProtectContract.Presenter getPresenter() {
        return new ProtectPresenter(this);
    }

    @Override
    protected void initAllMembers(Bundle savedInstanceState) {
        refresh();
        initToolbar("保护登记");
        initArchivesRec();
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
                startActivity(new Intent(ProtectActivity.this, StorehouseActivity.class));
                finish();
            }
        });

        return toolbar;
    }


    private void initArchivesRec() {

        //柜架条目点击事件，跳转到条目详情
        protectAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                Object data = adapter.getItem(position);
                ProtectData.DataBean dataBean = data instanceof ProtectData.DataBean ? ((ProtectData.DataBean) data) : null;
                if (dataBean != null) {
                    ProtectDetailActivity.start(ProtectActivity.this, position, dataBean);
                }
            }
        });
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
                refresh();
//                if (mSwipeRefreshLayout.isRefreshing()) {
//                    mSwipeRefreshLayout.setRefreshing(false);
//                }
            }
        });
        //上拉加载
        protectAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {

                if (pag != 0 && pageNum < pag) {
                    refresh();
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
    public boolean onCreateOptionsMenu(Menu menu) {

        SubMenu subMenu = menu.addSubMenu("");

        subMenu.add("登记保护").setIcon(R.drawable.ic_add).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            public boolean onMenuItemClick(MenuItem item) {
                startActivity(new Intent(ProtectActivity.this, AddProtectActivity.class));
                return false;
            }
        });
        subMenu.add("搜索保护").setIcon(R.drawable.ic_search).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            public boolean onMenuItemClick(MenuItem item) {
                startActivity(new Intent(ProtectActivity.this, SearchProtectActivity.class));
                return false;
            }
        });
        subMenu.add("提交离线数据").setIcon(R.drawable.ic_off_line).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {


            public boolean onMenuItemClick(MenuItem item) {
                final ProtectDaoUtils dao = new ProtectDaoUtils(ProtectActivity.this);
                final List<Protect> list = dao.protectlistAll();
                //final List<Map<String, Object>> list = dao.MapAll();
                if (list.size() != 0) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(ProtectActivity.this);
                    builder.setMessage("确定提交离线登记的"+dao.protectCount()+"条数据？")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (NetUtils.hasNetWorkConection(ProtectActivity.this)) {
                                        for (Protect protect : list) {
                                            mPresenter.addData(protect.getStoreroomCode(), protect.getStoreroomName(), protect.getProtectType(), protect.getProtectTime(), protect.getProtectResult(), protect.getOperUser());
                                        }
                                        Toast.makeText(ProtectActivity.this, "提交成功，请刷新页面", Toast.LENGTH_SHORT).show();
                                        dao.deleteProtect();
                                    }else {
                                        Toast.makeText(ProtectActivity.this, "当前无网络，请检查当前网络是否连接。", Toast.LENGTH_SHORT).show();
                                    }

                                    //mPresenter.offtaken(new Gson().toJson(list));
                                }
                            })
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            }).show();


                } else {
                    Toast.makeText(ProtectActivity.this, "没有离线数据，无法提交", Toast.LENGTH_SHORT).show();
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
    public void setProtectData(List<ProtectData.DataBean> data, final int total, int pagNum) {
        protectAdapter.setEmptyView(R.layout.rec_empty_view);
        mFlag = FLAG_DATA_BY_TREE;
        mTotal = total;
        pag = pagNum;
        if (data != null) {
            if (pageNum <= pagNum) {
                if (pageNum == 1) {
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
        showSnackBar(hum_content, message);
    }

    @Override
    public void sendMessage(String msg) {

    }

    private void refresh() {
        mPresenter.getData(String.valueOf(pageNum), String.valueOf(mLimits));
    }
}
