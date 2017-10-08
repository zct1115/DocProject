package com.ckkj.docproject.ui.home;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.ckkj.docproject.R;
import com.ckkj.docproject.base.BaseActivity;
import com.ckkj.docproject.contract.LoginContract;
import com.ckkj.docproject.presenter.LoginPresenter;
import com.ckkj.docproject.ui.archives.ArchivesActivity;
import com.ckkj.docproject.ui.download.DownloadManagerActivity;
import com.ckkj.docproject.ui.examine.ExamineActivity;
import com.ckkj.docproject.ui.login.LoginActivity;
import com.ckkj.docproject.ui.cupboard.StorehouseActivity;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;

import butterknife.BindView;

public class MainActivity extends BaseActivity <LoginContract.Presenter>implements LoginContract.View, View.OnClickListener {
    @BindView(R.id.search)
    ImageView search;
    @BindView(R.id.storehouse)
    ImageView storehouse;
    @BindView(R.id.save)
    ImageView save;
    @BindView(R.id.more)
    ImageView more;
    private Toolbar toolbar;
    private RollPagerView mViewPager;

    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public LoginContract.Presenter getPresenter() {
        return new LoginPresenter(this);
    }


    @Override
    protected void initAllMembers(Bundle savedInstanceState) {
        initToolbar("电子文档数据中心【移动服务端】");
        mViewPager = (RollPagerView) findViewById(R.id.rollPagerView);
        mViewPager.setAdapter(new ImageNormalAdapter());
        save.setOnClickListener(this);
        search.setOnClickListener(this);
        storehouse.setOnClickListener(this);
        more.setOnClickListener(this);
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
        toolbar.setNavigationIcon(R.drawable.ic_file);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // new LoginModel(MainActivity.this).logout();


            }
        });

        return toolbar;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /*getMenuInflater().inflate(R.menu.loginout, menu);
        return super.onCreateOptionsMenu(menu);*/
        SubMenu subMenu = menu.addSubMenu("");


        subMenu.add("我的下载").setIcon(R.drawable.ic_download_24dp).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            public boolean onMenuItemClick(MenuItem item) {
                startActivity(new Intent(MainActivity.this,DownloadManagerActivity.class));
                return false;
            }
        });
        subMenu.add("退出").setIcon(R.drawable.ic_black_24dp).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {


            public boolean onMenuItemClick(MenuItem item) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("确定要退出吗？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mPresenter.logout();
                                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                                intent.putExtra("autoLogin", false);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }).show();

                return false;
            }
        });

        MenuItem item = subMenu.getItem();
        item.setIcon(R.drawable.ic_setting);
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("确定要退出吗？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).show();

    }

    @Override
    public void success() {

    }

    @Override
    public void fail(String msg) {

    }


    private class ImageNormalAdapter extends StaticPagerAdapter {
        int[] imgs = new int[]{
                R.mipmap.img1,
                R.mipmap.img2,
                R.mipmap.img3,
                R.mipmap.img4,
                R.mipmap.img5,
        };

        @Override
        public View getView(ViewGroup container, int position) {
            ImageView view = new ImageView(container.getContext());
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            view.setImageResource(imgs[position]);
            return view;
        }


        @Override
        public int getCount() {
            return imgs.length;
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search:
                Intent intent = new Intent(MainActivity.this, ArchivesActivity.class);
                startActivity(intent);
                break;
            case R.id.storehouse:
                Intent intent1 = new Intent(MainActivity.this, StorehouseActivity.class);
                startActivity(intent1);
                break;
            case R.id.save:
                startActivity(new Intent(MainActivity.this,ExamineActivity.class));
                break;
            case R.id.more:
                Toast.makeText(this, "更多功能敬请期待", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;


        }


    }
}
