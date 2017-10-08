package com.ckkj.docproject.ui.archives;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.ckkj.docproject.R;
import com.ckkj.docproject.base.BaseActivity;
import com.ckkj.docproject.base.BasePresenter;

import butterknife.BindView;

/**
 * Created by XISEVEN on 2017/5/22.
 */

public class SearchActivity extends BaseActivity<BasePresenter> implements View.OnClickListener {


    @BindView(R.id.search_add_condition)
    TextView searchAddCondition;
    @BindView(R.id.search_layout)
    LinearLayout searchLayout;
    @BindView(R.id.search_scroll)
    ScrollView searchScroll;


    @Override
    public int getContentViewId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initAllMembers(Bundle savedInstanceState) {
        Intent intent = getIntent();
        String tableName = intent.getStringExtra("tableName");
        Toolbar toolbar = initToolbar(tableName+"-搜索");
        searchAddCondition.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        View queryView = LayoutInflater.from(this).inflate(R.layout.layout_query_item, searchLayout, false);
        searchLayout.addView(queryView);
        scrollToDown(queryView);
    }

    //删除查询条件条目的点击事件
    public void deleteQueryItem(View view) {
        searchLayout.removeView((View) view.getParent());
    }

    //添加view后ScrollView滑到最底部
    //需要等布局加载完之后滑动ScrollView，并让添加的EditText获得焦点
    private void scrollToDown(final View view) {
        searchScroll.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                searchScroll.post(new Runnable() {
                    public void run() {
                        searchScroll.fullScroll(View.FOCUS_DOWN);
                        EditText et = (EditText) view.findViewById(R.id.query_item_et);
                        et.setFocusable(true);
                        et.setFocusableInTouchMode(true);
                        et.requestFocus();
                    }
                });
            }
        });
    }

    // 提交查询
    private void submitQuery() {
        Log.d("test", "submitQuery: test");
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < searchLayout.getChildCount(); i++) {
            View view = searchLayout.getChildAt(i);
            EditText et = (EditText) view.findViewById(R.id.query_item_et);
            if (et != null && !et.getText().toString().isEmpty()) {
                String s = et.getText().toString();
                if (!TextUtils.isEmpty(stringBuffer)) {
                    stringBuffer.append(',');
                }
                stringBuffer.append(s);
            }
        }
        if (stringBuffer.toString().isEmpty()) {
            showSnackBar(searchScroll, "请正确输入查询条件！", "OK");
        } else {
            int count=0;
            for(int i=0;i<searchLayout.getChildCount(); i++){
                View view = searchLayout.getChildAt(i);
                EditText et = (EditText) view.findViewById(R.id.query_item_et);
                if(et.getText().toString().isEmpty()){
                    showSnackBar(searchScroll, "请正确输入查询条件！", "OK");
                    count++;
                }
            }
            if(count==0){
                Intent intent = new Intent();
//            intent.putExtra("tableName", mTableName);
                intent.putExtra("searchCondition", stringBuffer.toString());
                setResult(RESULT_OK, intent);
                finish();
            }

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.query, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_query) {
            submitQuery();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
