package com.ckkj.docproject.adapter;

import android.support.annotation.LayoutRes;
import  com.ckkj.docproject.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ckkj.docproject.bean.cupboard.CupBoardData;

/**
 * Created by zct11 on 2017/8/9.
 */

public class CupBoardAdapter extends BaseQuickAdapter<CupBoardData.DataBean,BaseViewHolder> {

    public CupBoardAdapter() {
        super(R.layout.item_cupboard);
    }

    @Override
    protected void convert(BaseViewHolder helper, CupBoardData.DataBean item) {
      helper.setText(R.id.cup_id,item.getCode()).setText(R.id.cup_name,item.getName());
    }
}
