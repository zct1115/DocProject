package com.ckkj.docproject.ui.archives;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.ckkj.docproject.R;
import com.ckkj.docproject.base.BaseActivity;
import com.ckkj.docproject.contract.ArchivesDetailContract;
import com.ckkj.docproject.presenter.ArchivesDetailPresenter;
import com.ckkj.docproject.view.MyPopupWindow;

import butterknife.BindView;

/**
 * 档案详情页面
 * Created by XISEVEN on 2017/5/24.
 */

public class ArchivesDetailActivity extends BaseActivity<ArchivesDetailContract.Presenter> implements ArchivesDetailContract.View {

    @BindView(R.id.archivesdetail_scroll)
    NestedScrollView mScrollView;
//    @BindView(R.id.refresh_layout)
//    SwipeRefreshLayout mSwipeRefreshLayout;
    private String mId;
    private String mTableName;
    private MyPopupWindow mPopupWindow;

    public static void start(Context context, String id, String tableName) {
        Intent intent = new Intent(context, ArchivesDetailActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("tableName", tableName);
        context.startActivity(intent);
    }

    @Override
    protected ArchivesDetailContract.Presenter getPresenter() {
        return new ArchivesDetailPresenter(this);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_archivesdetail;
    }

    @Override
    protected void initAllMembers(Bundle savedInstanceState) {
        initToolbar("档案详情");
        Intent intent = getIntent();
        mId = intent.getStringExtra("id");
        mTableName = intent.getStringExtra("tableName");
//        initSwipRefreshLayout();
        mPresenter.getArchivesDetail(mId, mTableName);



    }


    @Override
    public void setArchivesDetail(View view) {
//        if (mSwipeRefreshLayout.isRefreshing()) {
//            mSwipeRefreshLayout.setRefreshing(false);
//        }
        mScrollView.addView(view);
    }

    @Override
    public void setAttachment(View view) {
        mPopupWindow = new MyPopupWindow(this, view);
    }

    @Override
    public void getDataFail(String msg) {
//        if (mSwipeRefreshLayout.isRefreshing()) {
//            mSwipeRefreshLayout.setRefreshing(false);
//        }
       // showSnackBar(mScrollView, msg);
        AlertDialog.Builder dlg = new AlertDialog.Builder(this);
        dlg.setTitle("温馨提示");
        dlg.setMessage("文件缺失");
        dlg.setPositiveButton("确定",null);
        dlg.show();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.download, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_download:
                if (mPopupWindow == null) {
                    showSnackBar(mScrollView, "无电子文件可下载", "OK");
                } else {
                    mPopupWindow.showAtLocation(mScrollView, Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
