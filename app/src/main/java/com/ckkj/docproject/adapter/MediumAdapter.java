package com.ckkj.docproject.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ckkj.docproject.R;
import com.ckkj.docproject.bean.examine.Medium;


/**
 * Created by zct11 on 2017/8/9.
 */

public class MediumAdapter extends BaseQuickAdapter<Medium.DataBean,BaseViewHolder> {

    public MediumAdapter() {
        super(R.layout.item_entitive);
    }

    @Override
    protected void convert(BaseViewHolder helper, Medium.DataBean item) {
      helper.setText(R.id.taskName,item.getTaskName()).setText(R.id.dataCount,item.getDataCount()).setText(R.id.classifyName,item.getClassifyName()).setText(R.id.preTaskName,item.getPreTaskName());
    }
}
