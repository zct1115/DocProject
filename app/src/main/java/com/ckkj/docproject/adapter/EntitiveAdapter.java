package com.ckkj.docproject.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ckkj.docproject.R;
import com.ckkj.docproject.bean.examine.Entitive;


/**
 * Created by zct11 on 2017/8/9.
 */

public class EntitiveAdapter extends BaseQuickAdapter<Entitive.DataBean,BaseViewHolder> {

    public EntitiveAdapter() {
        super(R.layout.item_entitive);
    }

    @Override
    protected void convert(BaseViewHolder helper, Entitive.DataBean item) {
      helper.setText(R.id.taskName,item.getTaskName()).setText(R.id.dataCount,item.getDataCount()).setText(R.id.classifyName,item.getClassifyName()).setText(R.id.preTaskName,item.getPreTaskName());
    }
}
