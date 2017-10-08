package com.ckkj.docproject.view.treeview;

import android.view.View;

import ckkj.mylibrary.treeview.base.BaseNodeViewBinder;
import ckkj.mylibrary.treeview.base.BaseNodeViewFactory;


/**
 * Created by zxy on 17/4/23.
 */

public class MyNodeViewFactory extends BaseNodeViewFactory {

    @Override
    public BaseNodeViewBinder getNodeViewBinder(View view, int level) {
        return new FirstLevelNodeViewBinder(view, level);
    }
}

