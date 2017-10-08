package com.ckkj.docproject.view.treeview;

import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import  com.ckkj.docproject.R;
import  com.ckkj.docproject.bean.ArchivesCatalogue;
import  com.ckkj.docproject.event.MessageEvent;
import  com.ckkj.docproject.utils.DensityUtils;
import ckkj.mylibrary.treeview.TreeNode;
import ckkj.mylibrary.treeview.base.BaseNodeViewBinder;


/**
 * Created by zxy on 17/4/23.
 */

public class FirstLevelNodeViewBinder extends BaseNodeViewBinder {
    TextView textView;
    ImageView imageView;
    LinearLayout ll;
    private ArchivesCatalogue.DataBean mData;
    private int level;

    public FirstLevelNodeViewBinder(View itemView, int level) {
        super(itemView);
        this.level = level;
        textView = (TextView) itemView.findViewById(R.id.node_name_view);
        imageView = (ImageView) itemView.findViewById(R.id.arrow_img);
        ll = (LinearLayout) itemView.findViewById(R.id.node_container);
    }


    @Override
    public int getLayoutId() {
        return R.layout.item_first_level;
    }

    @Override
    public void bindView(final TreeNode treeNode) {
        mData = (ArchivesCatalogue.DataBean) treeNode.getValue();
        textView.setText(mData.getTableNameCH());
        imageView.setRotation(treeNode.isExpanded() ? 90 : 0);
        ll.setPadding(DensityUtils.dip2px(ll.getContext(), 24 * level), 0, 0, 0);
        ll.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    v.setAlpha(1.0f);
                    EventBus.getDefault().post(new MessageEvent(mData.getTableName(),mData.getTableNameCH(),mData.getId()));
                }else if (event.getAction() == MotionEvent.ACTION_UP) {
                    v.setAlpha(1.0f);

                }
                return false;
            }
        });
       /* ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new MessageEvent(mData.getTableName(),mData.getTableNameCH(),mData.getId()));
            }

        });*/

    }

    @Override
    public void onNodeToggled(TreeNode treeNode, boolean expand) {
        if (expand) {
            imageView.animate().rotation(90).setDuration(200).start();
        } else {
            imageView.animate().rotation(0).setDuration(200).start();
        }
    }
}
